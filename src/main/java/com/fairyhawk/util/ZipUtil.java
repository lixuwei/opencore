package com.fairyhawk.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Title : ZipUtil.java <br/>
 * Description : 压缩，解压文件工具类<br/>
 * Company : Sinobo <br/>
 * Copyright : Copyright (c) 2010-2013 All rights reserved.<br/>
 * Created : 2013-2-4 下午2:14:36 <br/>
 * 
 * @author Wenzhong Gu
 * @version 1.0
 */
public class ZipUtil {

	private static final Log log = LogFactory.getLog(ZipUtil.class);
	private static final int BUFFER = 4096;

	/**
	 * 解压文件
	 * 
	 * @param fileName
	 *            压缩文件名
	 * @param targetPath
	 *            解压的目的路径
	 */
	public static void unzip(String fileName, String targetPath) {
		File file = null;
		File parent = null;
		ZipFile zipFile = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			zipFile = new ZipFile(fileName);
			Enumeration<?> emu = zipFile.entries();
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				if (entry.isDirectory()) {
					new File(targetPath + "/" + entry.getName()).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				file = new File(targetPath + "/" + entry.getName());
				parent = file.getParentFile();
				if (parent != null && (!parent.exists()))
					parent.mkdirs();
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, BUFFER);
				int count;
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
			}
			//new File(fileName).delete();
		} catch (Exception e) {
			log.error("===method : unzip ERROR ===" + e.getMessage());
		} finally {
			file = null;
			parent = null;
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					log.error("===method : unzip BufferedOutputStream close ERROR ===" + e.getMessage());
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					log.error("===method : unzip FileOutputStream close ERROR ===" + e.getMessage());
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					log.error("===method : unzip BufferedInputStream close ERROR ===" + e.getMessage());
				}
			}
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					log.error("===method : unzip ZipFile close ERROR ===" + e.getMessage());
				}
			}
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @param fileDirectory
	 *            压缩的目录
	 * @param zipFileName
	 *            要压缩的压缩包名称
	 */
	public static void zip(String fileDirectory, String targetFileName) {
		ZipEntry entry = null;
		FileInputStream fis = null;
		ZipOutputStream out = null;
		FileOutputStream dest = null;
		BufferedInputStream origin = null;
		try {
			dest = new FileOutputStream(targetFileName);
			out = new ZipOutputStream(new BufferedOutputStream(dest));
			byte data[] = new byte[BUFFER];
			File f = new File(fileDirectory);
			File files[] = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().endsWith(".zip") || files[i].isDirectory())
					continue;
				fis = new FileInputStream(files[i]);
				origin = new BufferedInputStream(fis, BUFFER);
				entry = new ZipEntry(files[i].getName());
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}
		} catch (Exception e) {
			log.error("===method : zip ERROR ===" + e.getMessage());
		} finally {
			entry = null;
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					log.error("===method : zip ZipInputStream close ERROR ===" + e.getMessage());
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("===method : zip ZipOutputStream close ERROR ===" + e.getMessage());
				}
			}
			if (dest != null) {
				try {
					dest.close();
				} catch (IOException e) {
					log.error("===method : zip FileOutputStream close ERROR ===" + e.getMessage());
				}
			}
		}
	}

	public static boolean downloadFile(byte[] data,String fileName) {
		File file = new File(fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {

		String fileName = "D:\\test\\download\\1.zip";
		String filePath = "D:\\test\\download\\unzip\\";
		ZipUtil.unzip(fileName, filePath);
		System.out.println("===end===");

	}

}