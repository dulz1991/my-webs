package com.demo.my.base.service.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.stereotype.Component;


@Component
public class ImgUploadService {

	public void imageUpload(String imgPath, HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			FileUploadException {
		// 保存文件路径
		String realPath = imgPath;
		// 判断路径是否存在，不存在则创建
		File dir = new File(realPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			dff.setRepository(dir);
			dff.setSizeThreshold(1024000);
			ServletFileUpload sfu = new ServletFileUpload(dff);
			FileItemIterator fii = sfu.getItemIterator(request);
			String title = ""; // 图片标题
			String url = ""; // 图片地址
			String fileName = "";
			String originalName = "";
			String state = "SUCCESS";
			String ftype = "";
			try {
				while (fii.hasNext()) {
					FileItemStream fis = fii.next();
					if (!fis.isFormField() && fis.getName().length() > 0) {
						fileName = fis.getName();
						Pattern reg = Pattern.compile("[.]jpg|png|jpeg|gif$");
						Matcher matcher = reg.matcher(fileName);
						if (!matcher.find()) {
							state = "File type are not allowed!";
							break;
						}
						ftype = matcher.group();
						fileName = new Date().getTime() + "." + ftype;
						url = realPath + "/" + fileName;
						BufferedInputStream in = new BufferedInputStream(
								fis.openStream());// 获得文件输入流
						FileOutputStream a = new FileOutputStream(new File(url));
						BufferedOutputStream output = new BufferedOutputStream(
								a);
						Streams.copy(in, output, true);// 开始把文件写到你指定的上传文件夹
					} else {
						String fname = fis.getFieldName();
						if (fname.indexOf("fileName") != -1) {
							BufferedInputStream in = new BufferedInputStream(
									fis.openStream());
							byte c[] = new byte[10];
							int n = 0;
							while ((n = in.read(c)) != -1) {
								originalName = new String(c, 0, n);
								break;
							}
							in.close();

						}
						if (fname.indexOf("pictitle") != -1) {
							BufferedInputStream in = new BufferedInputStream(
									fis.openStream());
							byte c[] = new byte[10];
							int n = 0;
							while ((n = in.read(c)) != -1) {
								title = new String(c, 0, n);
								break;
							}
							in.close();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			title = title.replace("&", "&amp;").replace("'", "&qpos;")
					.replace("\"", "&quot;").replace("<", "&lt;")
					.replace(">", "&gt;");
			String outputPat = "{'original':'"
					+ originalName
					+ "','url':'" + realPath.substring(realPath.lastIndexOf("\\") + 1,
							realPath.length()) + "/" + fileName + "','title':'"
					+ title + "','state':'" + state + "'}";
			response.getWriter().print(outputPat);
			// response.getWriter().print("{'original':'"+originalName+"','url':'http://127.0.0.1:8095/upload/imageUpload/'"+fileName+"','title':'"+title+"','state':'"+state+"'}");
		}
	}
}
