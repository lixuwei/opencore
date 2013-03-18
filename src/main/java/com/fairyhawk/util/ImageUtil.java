package com.fairyhawk.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	/**
	 * 针对于回单附件的图片
     * 打印文字水印图片
     * 
     * @param oldImg
     *            --原始图片
     * @param newImg --新图片
     *            
     * @param fontSize --
     *            字体大小
     * @param jiesuandanhao--结算单号
     * @param fukuanmingc--付款人名称      
     * @param fukuanzhanghao --付款账号
     * @param jine--金额
     * @param shoukuanmingc--收款人名称
     * @param shoukuanzhanghao--收款账号
     * @param yinhang--银行
     * @param time--时间
     */
	 public static void makeImageText(String oldImg,String newImg,int fontSize,String jiesuandanhao,String fukuanname,String fukuanzhanghao, String jine,String shoukuanmingcheng,String shoukuanzhanghao,String yinhang,String time) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
        try {
        	fis = new FileInputStream(oldImg);
	   		byte[] b = new byte[1024];
	   		fos = new FileOutputStream(newImg);
	   		while(fis.read(b)!=-1){
	   		  fos.write(b);
	   		}
            File _file = new File(newImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            g.setColor(Color.black);
            //设置字体，风格，大小
            g.setFont(new Font("宋体", Font.PLAIN, fontSize));
            //g.drawString(fukuanname, 200, 130);
            g.drawString(fukuanname, 430, 78);
            //g.drawString(fukuanzhanghao, 200, 160);
            g.drawString(fukuanzhanghao, 430, 103);
            //g.drawString(jine, 200, 230);
            g.drawString(jine, 430, 128);

            g.drawString(shoukuanmingcheng, 180, 105);
            //g.drawString(shoukuanmingcheng, 410, 130);
            //g.drawString(shoukuanzhanghao, 410, 160);
            g.drawString(shoukuanzhanghao, 180, 130);
            //g.drawString(yinhang, 410, 190);
            g.drawString(yinhang, 180, 155);
            //g.drawString(time, 410, 230);
            g.drawString(time, 180, 80);
            g.drawString(jiesuandanhao,180,55);
            
            g.dispose();
            FileOutputStream out = new FileOutputStream(newImg);
            //创建一个和指定输出流关联的JPEGImageEncoder对象
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
        	 e.printStackTrace();
   		}finally{
   			
   			try{
   				if(fis!=null) fis.close();
   				if(fos!=null) fos.close();
   			}catch(Exception e){
   				e.printStackTrace();
   			}
   		}

    }
	 
	 public static void main(String[] args) {
			String trxorderName="feilong123";
			String oldImg="C:/Users/Lenovo/Desktop/123.jpg";
			String newImg="C:/Users/Lenovo/Desktop/"+trxorderName+".jpg";
		    String jiesuandanhao="qqqqqqqqqqqqq";
		    String fukuanname="dddddddddd";
		    String fukuanzhanghao="rrrrrrrrr";
		    String jine="20";
		    String shoukuanmingcheng="dddddddd";
		    String shoukuanzhanghao="ddddddddd";
		    String yinhang="hfuidkshk";
		    String time="2013-02-03";
		    makeImageText(oldImg,newImg,14,jiesuandanhao,fukuanname,fukuanzhanghao,jine,shoukuanmingcheng,shoukuanzhanghao,yinhang,time);
	    }
}
