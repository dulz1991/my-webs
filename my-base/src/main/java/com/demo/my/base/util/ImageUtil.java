package com.demo.my.base.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil {

	/**
	  * 图片裁切
	  * @param x1 选择区域左上角的x坐标
	  * @param y1 选择区域左上角的y坐标
	  * @param width 选择区域的宽度
	  * @param height 选择区域的高度
	  * @param sourcePath 源图片路径
	  * @param descpath 裁切后图片的保存路径
	  */
	public static void cut(int x, int y, int width, int height, String sourcePath) {
		cut(x, y, width, height, sourcePath, sourcePath);
	}
	public static void cut(int x, int y, int width, int height, String sourcePath, String descpath) {
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
			is = new FileInputStream(sourcePath);
			String fileSuffix = sourcePath.substring(sourcePath.lastIndexOf(".") + 1);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileSuffix);
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, fileSuffix, new File(descpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				is = null;
			}
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				iis = null;
			}
		}
	}
	
	/** 
     * 旋转照片 
     * @return 
     */  
    public static String rotate(String fullPath, int angel){  
        BufferedImage src;  
        try {  
            src = ImageIO.read(new File(fullPath));  
            int src_width = src.getWidth(null);  
            int src_height = src.getHeight(null);  
            int swidth=src_width;  
            int sheight=src_height;  
            if(angel==90||angel==270){  
                swidth = src_height;  
                sheight= src_width;  
            }  
            Rectangle rect_des = new Rectangle(new Dimension(swidth,sheight));  
            BufferedImage res = new BufferedImage(rect_des.width, rect_des.height,BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = res.createGraphics();  
            g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);  
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);  
            g2.drawImage(src, null, null);  
            ImageIO.write(res, "jpg", new File(fullPath));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }    
        return fullPath;  
    }  
	 
    public static void cutAndRoate(int x, int y, int width, int height, String sourcePath, int angel) {
		cut(x, y, width, height, sourcePath, sourcePath);
		rotate(sourcePath, angel);
	}
}
