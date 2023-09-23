package com.chaoyue.haodebar.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import com.chaoyue.haodebar.utils.BizConfigUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.slf4j.Marker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/20 20:20
 * @version: version 1.0
 * @dec: 全局traceId
 */
@Data
public class TraceIdTurboFilter extends TurboFilter {
    private static final String TRACE_ID = "traceId";

    private BizConfigUtils bizConfigUtils;

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {

        if (bizConfigUtils != null) {
            String logLevelDemotion = bizConfigUtils.getStringValue("logLevelDemotion");
            if ("true".equals(logLevelDemotion)) {
                return FilterReply.DENY;
            }
        }
        String traceId = Context.getParameter(TRACE_ID, null);
        if (StringUtils.isEmpty(traceId)) {
            traceId = MDC.get(TRACE_ID);
            if (StringUtils.isEmpty(traceId)) {
                MDC.put(TRACE_ID, UUID.randomUUID().toString());
            }
        } else {
            MDC.put(TRACE_ID, traceId);
        }
        return FilterReply.NEUTRAL;
    }

    public static class Context {
        private static final ThreadLocal<Context> CONTEXT = new ThreadLocal<Context>() {
            @Override
            protected Context initialValue() {
                return new Context();
            }
        };

        private final Map<String, String> parameters = new HashMap<>();
        private final Map<String, String> localValues = new HashMap<>();

        public Context() {

        }

        public static void setContext(Context context) {
            CONTEXT.set(context);
        }

        public static Context getContext() {
            return CONTEXT.get();
        }

        public static void setParameter(String key, String value) {
            CONTEXT.get().parameters.put(key, value);
        }

        public static String getParameter(String key, String defaultValue) {
            return CONTEXT.get().parameters.getOrDefault(key, defaultValue);
        }

        public static void set(String key, String value) {
            CONTEXT.get().localValues.put(key, value);
        }

        public static String get(String key, String defaultValue) {
            return CONTEXT.get().localValues.getOrDefault(key, defaultValue);
        }

        public static String remove(String key) {
            return CONTEXT.get().localValues.remove(key);
        }

        public static void clear() {
            CONTEXT.remove();
        }

    }
}
