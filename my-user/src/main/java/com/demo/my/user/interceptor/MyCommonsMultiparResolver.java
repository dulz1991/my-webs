package com.demo.my.user.interceptor;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyCommonsMultiparResolver extends CommonsMultipartResolver {
	
	@Override  
    
	public boolean isMultipart(javax.servlet.http.HttpServletRequest request) {  
		String uri = request.getRequestURI();  
		System.out.println(uri);
		//����ʹ�ðٶ�UEditor��URI  
		if (uri.equals("/upload/doImgUpload")) {     //�˴�����·����Ϊ�����д��controller·��
			System.out.println("commonsMultipartResolver ����");
			return true;  
		}  
		System.out.println("commonsMultipartResolver ����");
		return super.isMultipart(request);  
	}
}
