package com.fairyhawk.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName  Constant
 * @package com.fairyhawk.common.util
 * @description 统一常量定义
 * @author  liuqinggang
 * @Create Date: 2013-3-1 上午11:06:11
 *
 */
public class Constant {
	public static final String TRIGGERNAME = "triggerName";
	public static final String TRIGGERGROUP = "triggerGroup";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	public static final String REPEATCOUNT = "repeatCount";
	public static final String REPEATINTERVEL = "repeatInterval";
	public static final String PLAN_TASK = "plan";
	public static final String FEED_TASK = "feed";
	public static final String RETURN_URL = "feed";
	// 用户唯一ukey
	public static final String COOKIE_REMEMBERME_KEY = "fairy.cookie.ukey";
	//验证码
	public static String RAND_CODE = "user_rand_code";
	//系统用户存session key
	public static String SYS_USER_SESSION_NAME="sys_user";
	public static final Map<String, String> status = new HashMap<String, String>();
	static {
		status.put("ACQUIRED", "运行中");
		status.put("PAUSED", "暂停中");
		status.put("WAITING", "等待中");
	}
}
