package com.demo.my.user.interceptor;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyCommonsMultiparResolver extends CommonsMultipartResolver {
	
	@Override  
    
	public boolean isMultipart(javax.servlet.http.HttpServletRequest request) {  
		String uri = request.getRequestURI();  
		System.out.println(uri);
		//过滤使用百度UEditor的URI  
		if (uri.equals("/upload/doImgUpload")) {     //此处拦截路径即为上面编写的controller路径
			System.out.println("commonsMultipartResolver 放行");
			return true;  
		}  
		System.out.println("commonsMultipartResolver 拦截");
		return super.isMultipart(request);  
	}
}
