package com.fairyhawk.util;

/**
 * Title : EnumUtil.java <br/>
 * Description : TODO<br/>
 * Company : Sinobo <br/>
 * Copyright : Copyright (c) 2010-2013 All rights reserved.<br/>
 * Created : 2013-1-29 下午5:38:02 <br/>
 * 
 * @author Wenzhong Gu
 * @version 1.0
 */
public class EnumUtil {

	public static <T extends Enum<T>> T transStringToEnum(Class<T> c, String enumString) {
		if (c != null && !"".equals(enumString)) {
			try {
				return Enum.valueOf(c, enumString.trim());
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static String transEnumToString(Enum<?> enumValue) {
		if ("".equals(enumValue) || enumValue == null) {
			return "";
		}
		return enumValue.toString();
	}

}