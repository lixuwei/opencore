package com.fairyhawk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * <p>
 * Title:文件工具类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: Sinobo
 * </p>
 * 
 * @date Jun 29, 2011
 * @author ye.tian
 * @version 1.0
 */

public class FileUtils {

	public static String getFileContent(String path) {
		File myFile = new File(path);
		if (!myFile.exists()) {
			System.err.println("Can't Find " + path);
		}
		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					myFile), "UTF-8");
			in = new BufferedReader(read);
			String str;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}

		} catch (IOException e) {
			e.getStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 重命名文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static String renameFileName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		StringBuilder file = new StringBuilder();
		file.append(DateUtils.formatDate(new Date(), "yyyyMMddHHmmssSSS")).append("_").append(IdGen.genId()).append(suffix.toLowerCase());
		return file.toString();
	}


	public static File writeFileContent(String content, String path) {
		File file = new File(path);
		FileOutputStream fileout = null;
		try {
			file.mkdirs();
			if (!file.exists()) {
				file.createNewFile();
			}
			fileout = new FileOutputStream(file);
			fileout.write(content.getBytes("utf-8"));
			fileout.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileout != null) {
				try {
					fileout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	/**
	 * 文件内容写入
	 * 目录不存在，先创建目录；文件不存在，先创建文件
	 * @param content
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File writeFileContent(String content, String path, String fileName){
		File filePath = new File(path);
		File file = new File(path + System.getProperties().getProperty("file.separator") + fileName);
		FileOutputStream fileout = null;
		try {
			//创建目录
			filePath.mkdirs();
			//写入文件
			if (!file.exists()) {
				file.createNewFile();
			}
			fileout = new FileOutputStream(file);
			fileout.write(content.getBytes("utf-8"));
			fileout.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileout != null) {
				try {
					fileout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	/**
	 * 获取文件路径
	 * 
	 * @param dir
	 * @param fileName
	 *            add by wenhua.cheng
	 * @return
	 */
	public static String getFilePath(String dir, String fileName) {

		String fileSeparator = System.getProperty("file.separator");
		if (!dir.endsWith(fileSeparator)) {
			dir += fileSeparator;
		}
		return dir + fileName;

	}
}
