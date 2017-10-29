package com.meuap.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

public class CustomBeanUtils extends BeanUtilsBean {

	public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
		if (value == null) {
			return;
		}
		super.copyProperty(dest, name, value);
	}

}
