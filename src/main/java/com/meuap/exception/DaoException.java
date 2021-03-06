package com.meuap.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public DaoException(Exception throwable) {
		super(throwable);
	}
}
