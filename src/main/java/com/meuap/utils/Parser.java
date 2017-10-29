package com.meuap.utils;

public class Parser {

	public static String exceptionRootCauseMessage(Exception e) {
		e.printStackTrace();
		Throwable t = e.getCause();
		String errorMessage = "";

		if (t != null) {
			while (t.getCause() != null) {
				t = t.getCause();
			}
			errorMessage = t.getLocalizedMessage();
		} else {
			errorMessage = e.getLocalizedMessage();
		}

		return errorMessage;
	}
}
