package com.easy.logging.provider.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.easy.logging.trace.Trace;

public class TraceThrowableProxyConverter extends ThrowableProxyConverter {

    @Override
    public String convert(ILoggingEvent event) {
        if (event.getLevel().equals(Level.ERROR)) {
            String message = super.convert(event);
            Trace trace = Trace.getConcurrentTrace();
            String msgWithTraceId = message.replaceAll("\n", "\n" + trace.getTraceId());
            return msgWithTraceId;
        }
        return super.convert(event);
    }
}