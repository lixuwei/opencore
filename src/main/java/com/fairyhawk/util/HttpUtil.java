package com.fairyhawk.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Title : HttpUtil.java <br/>
 * Description : HTTP 功能类，分为get和post 两个方法<br/>
 * Company : Sinobo <br/>
 * Copyright : Copyright (c) 2010-2013 All rights reserved.<br/>
 * Created : 2013-1-29 下午5:38:47 <br/>
 * @author Wenzhong Gu
 * @version 1.0
 */
public class HttpUtil {

	private static final String AMP = "&amp;";
	private static final int SIZE = 1024 * 1024;

	private static final Log log = LogFactory.getLog(HttpUtil.class);

	private HttpUtil() {
	}

	/**
	 * @param strUrl
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static List<String> get(String strUrl, Map<String, Object> map) throws Exception {
		String strtTotalURL = "";
		List<String> result = new ArrayList<String>();
		if (strtTotalURL.indexOf("?") == -1) {
			strtTotalURL = strUrl + "?" + getUrl(map);
		} else {
			strtTotalURL = strUrl + "&" + getUrl(map);
		}
		URL url = new URL(strtTotalURL);
		BufferedReader in = null;
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			HttpURLConnection.setFollowRedirects(true);
			in = new BufferedReader(new InputStreamReader(con.getInputStream()), SIZE);
			while (true) {
				String line = in.readLine();
				if (line == null) {
					break;
				} else {
					result.add(line);
				}
			}
		} catch (Exception e) {
			log.info("excetpin,strUrl" + strUrl + ",Map=" + map);
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
		}
		return result;
	}

	/**
	 * @param strUrl
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static List<String> post(String strUrl, Map<String, Object> map) throws IOException {

		String content = getUrl(map);
		String totalURL = null;
		if (strUrl.indexOf("?") == -1) {
			totalURL = strUrl + "?" + content;
		} else {
			totalURL = strUrl + "&" + content;
		}
		URL url = new URL(totalURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setAllowUserInteraction(false);
		con.setUseCaches(false);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		bout.write(content);
		bout.flush();
		bout.close();
		BufferedReader bin = new BufferedReader(new InputStreamReader(con.getInputStream()), SIZE);
		List<String> result = new ArrayList<String>();
		while (true) {
			String line = bin.readLine();
			if (line == null) {
				break;
			} else {
				result.add(line);
			}
		}
		return result;
	}

	/**
	 * @param map
	 * @return
	 */
	private static String getUrl(Map<String, Object> map) {
		if (null == map || map.keySet().size() == 0) {
			return "";
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();) {
			String key = i.next();
			if (map.containsKey(key)) {
				Object val = map.get(key);
				String str = val != null ? val.toString() : "";
				url.append(key).append("=").append(str).append(AMP);
			}
		}
		String strURL = "";
		strURL = url.toString();
		if (AMP.equals("" + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}
		return strURL;
	}

}