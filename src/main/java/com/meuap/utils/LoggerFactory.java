package com.meuap.utils;

import org.slf4j.Logger;

public final class LoggerFactory {

	private Logger	logger;

	private LoggerFactory(Class<?> classe) {
		this.logger = org.slf4j.LoggerFactory.getLogger(classe);		
	}

	public LoggerFactory() {

	}

	public static LoggerFactory getInstance(Class<?> classe) {
		try {
			LoggerFactory loggerFactory;
			loggerFactory = new LoggerFactory(classe);
			return loggerFactory;
		} catch (Exception e) {
			return null;
		}
	}

	public void warn(String message) {
		if (message != null) {
			logger.warn(message);
		} else {
			logger.warn("");
		}
		System.out.println(message);
	}

	public void warn(String message, Exception e) {
		if (message != null) {
			logger.warn(message, e);
		} else {
			logger.warn("", e);
		}
	}

	public void error(String message) {
		if (message != null) {
			logger.error(message);
		} else {
			logger.error("");
		}
		System.out.println(message);

	}

	public void error(String message, Exception e) {
		if (message != null) {
			logger.error(message, e);
			System.out.println(message + " " + e);
		} else {
			logger.error("", e);
		}
	}

	public void trace(String message) {
		if (message != null) {
			logger.trace(message);

		} else {
			logger.trace("");
		}
		System.out.println(message);
	}

	public void trace(String message, Exception e) {
		if (message != null) {
			logger.trace(message, e);
		} else {
			logger.trace("", e);
		}
	}

	public void info(String message) {
		if (message != null) {
			logger.info(message);
		} else {
			logger.info("");
		}
		System.out.println(message);
	}

	public void info(String message, Exception e) {
		if (message != null) {
			logger.info(message, e);
		} else {
			logger.info("", e);
		}
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public void debug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
		System.out.println(message);
	}

	public void debug(String message, Exception e) {
		if (logger.isDebugEnabled()) {
			if (message != null) {
				logger.debug(message, e);
			} else {
				logger.debug("", e);
			}

		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
