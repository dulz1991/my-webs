package com.demo.my.base.service.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.demo.my.base.util.DateUtil;
import com.demo.my.base.util.PropertiesUtil;

@Component
public class ImgUploadService extends BaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(ImgUploadService.class);

	//��ͼ�ϴ�
	public Map<String, Object> uploadImage(HttpServletRequest request, 
			String uploadPath, String savePath) throws IOException, FileUploadException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		// �����ļ�·��
		// �ж�·���Ƿ���ڣ��������򴴽�
		File dir = new File(uploadPath);
		this.checkDir(dir);

		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			dff.setRepository(dir);
			dff.setSizeThreshold(1024000);
			ServletFileUpload sfu = new ServletFileUpload(dff);
			FileItemIterator fii = sfu.getItemIterator(request);
			String title = ""; // ͼƬ����
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
						fileName = new Date().getTime() + ftype;
						uploadPath = uploadPath + fileName;
						BufferedInputStream in = new BufferedInputStream(fis.openStream());
						FileOutputStream a = new FileOutputStream(new File(uploadPath));
						BufferedOutputStream output = new BufferedOutputStream(a);
						Streams.copy(in, output, true);
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
					+ "','url':'" + savePath + fileName + "','title':'"
					+ title + "','state':'" + state + "'}";
			/*response.getWriter().print(outputPat);*/
			// response.getWriter().print("{'original':'"+originalName+"','url':'http://127.0.0.1:8095/upload/imageUpload/'"+fileName+"','title':'"+title+"','state':'"+state+"'}");
			
			resMap.put("result", outputPat);
			resMap.put("fileName", fileName);
			resMap.put("url", savePath + fileName);
		}
		return resMap;
	}
	
	//codeͼƬ�ϴ�
	public Map<String, Object> uploadCodeImage(HttpServletRequest request) throws IOException, FileUploadException {
		String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
		String uploadPath = PropertiesUtil.get("pic_code_path_upload");
		uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
		String savePath = PropertiesUtil.get("pic_code_path_save");
		savePath = savePath.replace("{{yyyymmdd}}", dateStr);
		return this.uploadImage(request, uploadPath, savePath);
	}
	
	//blogͼƬ�ϴ�
	public Map<String, Object> uploadBlogImage(HttpServletRequest request) throws IOException, FileUploadException {
		 String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
         String uploadPath = PropertiesUtil.get("pic_blog_path_upload");
         uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
         String savePath = PropertiesUtil.get("pic_blog_path_save");
         savePath = savePath.replace("{{yyyymmdd}}", dateStr);
         return this.uploadImage(request, uploadPath, savePath);
	}
	
	//vueͼƬ�ϴ�
	public Map<String, Object> uploadVueImage(HttpServletRequest request) throws IOException, FileUploadException {
		 String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
         String uploadPath = PropertiesUtil.get("pic_vue_path_upload");
         uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
         String savePath = PropertiesUtil.get("pic_vue_path_save");
         savePath = savePath.replace("{{yyyymmdd}}", dateStr);
         return this.uploadImage(request, uploadPath, savePath);
	}

	//userͼƬ�ϴ�
	public Map<String, Object> uploadUserImage(HttpServletRequest request) throws IOException, FileUploadException {
		 String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
         String uploadPath = PropertiesUtil.get("pic_user_path_upload");
         uploadPath = uploadPath.replace("{{yyyymmdd}}", dateStr);
         String savePath = PropertiesUtil.get("pic_user_path_save");
         savePath = savePath.replace("{{yyyymmdd}}", dateStr);
         return this.uploadImage(request, uploadPath, savePath);
	}
}
