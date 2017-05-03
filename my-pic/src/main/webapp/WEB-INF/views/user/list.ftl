<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>我的旅行日志</title>
	<#include "../share/meta.ftl">
</head>
    
<body>
	<!-- 头部导航 -->
	<#include "../share/fix_nav.ftl">
	
	<div class="container">
				
		<div class="row grey lighten-5" style="margin-bottom:0px;">
			<!-- 左边部分 -->
			<div class="col s3 teal lighten-4">
	      		<#include "include/left.ftl">
	      	</div>
	      	<!-- 右边部分 -->
	      	<div class="col s9">
	        	<#include "include/list.ftl"> 
		    </div>
	    </div>
	    
	</div>
      
	<!-- 网站尾部 -->
	<#include "../share/footer.ftl">
</body>
</html>