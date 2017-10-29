package com.meuap.utils;

public final class ConstantsSqls {

	// AdminController
	public final static String	SELECT_ALL_BUILDINGS			= "SELECT u FROM building u";
	public final static String	SELECT_ALL_APPARTMENTS			= "SELECT u FROM appartment u";
//	public final static String	SELECT_NAME_USERS_FOR_LOGIN			= "SELECT u FROM UserObject u WHERE u.nomeCompleto LIKE :nome";
//	public final static String	SELECT_EMAIL_PASSWORD_FOR_LOGIN		= "SELECT u FROM UserObject u WHERE u.email = :email AND u.password = :password";
	
	
	// HomeConfiguration
	public static final String SELECT_ALL_HOME_CONFIGS_BY_SEGMENT = "SELECT u FROM homeConfig u WHERE u.enabled = true";

//	// ARCHIVES
//	public final static String	SELECT_ALL_ARCHIVES					= "SELECT u FROM ArchiveObject u ORDER BY id_category_type, name";
//	public final static String	SELECT_ALL_CATEGORIES				= "SELECT u FROM ArchiveCategoryObject u ORDER BY u.name";
//	public final static String	SELECT_ALL_TYPES					= "SELECT u FROM ArchiveTypeObject u ORDER BY u.type";
//	public final static String	DELETE_ARCHIVE_BY_ID				= "DELETE FROM ArchiveObject u WHERE u.id = :id";
//
//	// SUPPORT
//	public final static String	SELECT_ALL_USERS_SUPPORT			= "SELECT u FROM SupportUserObject u ORDER BY u.name";
//	public final static String	SELECT_USERS_SUPPORT_BY_NAME		= "SELECT u FROM SupportUserObject u WHERE name= :name";
//	public final static String	SELECT_ALL_EQUIPMENTS_SUPPORT		= "SELECT u FROM SupportEquipmentObject u ORDER BY u.equipment";
//	public final static String	SELECT_ALL_ORDERS_SUPPORT			= "SELECT u FROM SupportOrderObject u ORDER BY date asc";
//	public final static String	SELECT_ALL_ORDERS_SUPPORT_BY_USER	= "SELECT u FROM SupportOrderObject u WHERE u.supportUserObject =:supportUserObject ORDER BY u.date asc";
}
