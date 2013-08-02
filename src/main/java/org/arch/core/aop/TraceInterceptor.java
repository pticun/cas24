package org.arch.core.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * Extends {@link CustomizableTraceInterceptor} to provide custom logging levels
 */
public class TraceInterceptor extends CustomizableTraceInterceptor {

	private static final long serialVersionUID = 1L;

	protected static Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);

	@Override
	protected void writeToLog(final Log logger, final String message, final Throwable ex) {
		if (ex != null) {
			if (logger.isDebugEnabled()) {
				logger.debug(message, ex);
			}
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug(message);
			}
		}
	}

	@Override
	protected boolean isInterceptorEnabled(final MethodInvocation invocation, final Log logger) {
		return true;
	}
}