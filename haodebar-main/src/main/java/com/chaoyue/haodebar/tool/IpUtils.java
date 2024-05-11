package com.chaoyue.haodebar.tool;

import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class IpUtils {
    private final String UNKNOWN = "unknown";
    private final String X_FORWARDED_FOR = "X-Forwarded-For";
    private final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private final Pattern COMMA_SEPARATED_VALUES_PATTERN = Pattern.compile("\\s*,\\s*");

    /**
     * 默认情况下内网代理的子网可以是（后面有需要可以进行配置）：
     * 1. 10/8
     * 2. 192.168/16
     * 3. 169.254/16
     * 4. 127/8
     * 5. 172.16/12
     * 6. ::1
     */
    private final Pattern INTERNAL_PROXIES = Pattern.compile(
            "10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|" +
                    "192\\.168\\.\\d{1,3}\\.\\d{1,3}|" +
                    "169\\.254\\.\\d{1,3}\\.\\d{1,3}|" +
                    "127\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|" +
                    "172\\.1[6-9]\\.\\d{1,3}\\.\\d{1,3}|" +
                    "172\\.2[0-9]\\.\\d{1,3}\\.\\d{1,3}|" +
                    "172\\.3[0-1]\\.\\d{1,3}\\.\\d{1,3}|" +
                    "0:0:0:0:0:0:0:1|::1"
    );

    /**
     * 获取请求的IP
     *
     * @return 请求的IP
     */
    public String getIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String ip = getRemoteIp(request);
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取客户端真实IP地址，防止使用X-Forwarded-For进行IP伪造攻击，防御思路见类注释
     *
     * @return 真实IP地址
     */
    private String getRemoteIp(HttpServletRequest request) {
        String remoteIp = request.getRemoteAddr();
        boolean isInternal = INTERNAL_PROXIES.matcher(remoteIp).matches();

        if (isInternal) {
            StringBuilder concatRemoteIpHeaderValue = new StringBuilder();

            for (var e = request.getHeaders(X_FORWARDED_FOR); e.hasMoreElements(); ) {
                if (concatRemoteIpHeaderValue.length() > 0) {
                    concatRemoteIpHeaderValue.append(", ");
                }
                concatRemoteIpHeaderValue.append(e.nextElement());
            }

            var remoteIpHeaderValue = commaDelimitedListToArray(concatRemoteIpHeaderValue.toString());
            for (var i = remoteIpHeaderValue.length - 1; i >= 0; i--) {
                var currentRemoteIp = remoteIpHeaderValue[i];
                if (!INTERNAL_PROXIES.matcher(currentRemoteIp).matches()) {
                    return currentRemoteIp;
                }
            }
            return null;
        } else {
            return remoteIp;
        }
    }

    private String[] commaDelimitedListToArray(String commaDelimitedStrings) {
        return (commaDelimitedStrings == null || commaDelimitedStrings.isEmpty())
                ? new String[0]
                : COMMA_SEPARATED_VALUES_PATTERN.split(commaDelimitedStrings);
    }
}
