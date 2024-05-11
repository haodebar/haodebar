package com.chaoyue.haodebar.Interceptor;

import com.chaoyue.common.annotation.access.IpAccessPermission;
import com.chaoyue.common.constant.ResultCodeEnum;
import com.chaoyue.common.exception.BusinessException;
import com.chaoyue.common.exception.SystemException;
import com.chaoyue.haodebar.tool.IpAccessConfigUtils;
import com.chaoyue.haodebar.tool.IpUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * IP访问权限控制
 */
@Component
public class IpAccessInterceptor implements HandlerInterceptor {

    @Resource
    private IpAccessConfigUtils ipAccessConfigUtils;

    @Resource
    private IpUtils ipUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean preHandleFlag = true;

        HandlerMethod handlerMethod = (HandlerMethod)handler;

        IpAccessPermission ipAccessPermission = handlerMethod.getMethodAnnotation(IpAccessPermission.class);

        if(Objects.nonNull(ipAccessPermission)){
            //获取注解key值
            String whiteIpsKey =ipAccessPermission.whiteIpsKey();
            String blackIpsKey =ipAccessPermission.blackIpsKey();

            List<String> whiteIpsList =ipAccessConfigUtils.getIpAccessListMap().get(whiteIpsKey);
            List<String> blackIpsList =ipAccessConfigUtils.getIpAccessListMap().get(blackIpsKey);

            String ip =ipUtils.getIp();


            List<String> oldWhiteIpsList = new ArrayList<>(whiteIpsList);
            //如果配置了白名单,只有白名单能访问（去掉黑名单数据）
            if(!CollectionUtils.isEmpty(oldWhiteIpsList)){
                if(!CollectionUtils.isEmpty(blackIpsList)){
                    whiteIpsList.removeAll(blackIpsList);
                }

                //剩余白名单不为空，只有白名单能访问
                if(!whiteIpsList.isEmpty()){
                    if(!whiteIpsList.contains(ip)){
                       throw new SystemException(ResultCodeEnum.NO_PERMISSION_ONE.getCode(),ResultCodeEnum.NO_PERMISSION_ONE.getMsg());
                    }
                }else {
                    throw new SystemException(ResultCodeEnum.NO_PERMISSION_TWO.getCode(),ResultCodeEnum.NO_PERMISSION_TWO.getMsg());
                }
            }else{
                //ip在黑名单中，禁止访问
                if(blackIpsList.contains(ip)){
                    throw new SystemException(ResultCodeEnum.NO_PERMISSION_TREE.getCode(),ResultCodeEnum.NO_PERMISSION_TREE.getMsg());
                }
            }
        }

        return preHandleFlag;
    }
}
