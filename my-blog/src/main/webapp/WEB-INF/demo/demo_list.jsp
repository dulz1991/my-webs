<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<link href="/css/demo_left.css" rel="stylesheet">
	<script type="text/javascript">var addthis_config = {"data_track_addressbar":true};</script>
	<title>前端Demo</title>
</head>
<body data-spy="scroll docs" class="docs" id="top">
	<!-- head -->
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<nav class="demo-menu">
      	<div class="settings"></div>
       	<ul>
       		<c:forEach var="item" items="${demoMenus }">
       			<li>
                  	<a href="/demo/index?menuId=${item.id }">
            			<i class="fa fa-demo fa-lg ${item.icon }"></i>
            			<span class="nav-text">${item.name }</span>
					</a>
                </li>
       		</c:forEach>
           </ul>
           <ul class="logout">
               <li>
               	<c:if test="${isLogin}">
	    			<a href="#" onclick="$.common.toLogout()">
	    				<i class="fa fa-demo fa-sign-out"></i>
	    				<span class="nav-text">退出 </span>
	    			</a>
		        </c:if>
		        <c:if test="${!isLogin}">	
		        	<a href="#" onclick="$.common.toLogin()">
		        		<i class="fa fa-sign-in fa-demo"></i>
		        		<span class="nav-text">登陆</span>
		        	</a>
		        </c:if>
           	</li>  
       	</ul>
   	</nav>
	<div style="text-align:center;clear:both"></div>
	
	<div id="container" style="margin-left:8%;margin-top:15px;z-index:0">
		<c:forEach var="item" items="${demos }">
			<div class="demo-item">
				<img alt="" src="/api_img${item.picPath }">
				<div class="desc" title="${item.description }">【${item.title }】${item.description }</div>
				<div class="btns">
					<a href="#" class="viewBtn" demoId="${item.id }"><i class="fa fa-search"></i> 预览</a>
					<a href="/api_img/${item.resourcePath }" target="_blank" class="downloadBtn"><i class="fa fa-download"></i> 下载</a>
				</div>
			</div>
		</c:forEach>
	</div>
		
	<script type="text/javascript">
	$(function(){
		$('.viewBtn').click(function(){
			swal({
				title: "",
				html: "<div id='viewDetail'><i class='fa fa-spinner'></i>内容加载中...</div>",    //html内容
				confirmButtonText: '关闭'
			})
			$('#viewDetail').load('/demo/viewDetail?id='+$(this).attr('demoId'));
		});
	})
	</script>
	<style>
		.demo-item{
			display: inline-block;
			width:260px;
			height:300px;
			border:1px solid #ccc;
			margin-right:20px;
			margin-bottom:20px;
		}
		.demo-item img{
			height:160px;
			width:100%;
		}
		.demo-item .desc{
			height:90px;
			border-bottom:1px dashed #f00;
			overflow: hidden;
		}
		.demo-item .btns{
			line-height:50px;
		}
		.demo-item .btns .viewBtn{
			background-color:#0595d5 ;
		}
		.demo-item .btns .viewBtn:hover{
			background-color:#0482b7;
		}
		.demo-item .btns .downloadBtn{
			background-color:#5aba1f;
		}
		.demo-item .btns .downloadBtn:hover{
			background-color:#4a9b1a;
		}
		.demo-item .btns a{
			margin-left:10px;
			padding:5px 10px;
			color:#fff;		
		}
		#container .demo-item:hover{
			background-color:#ffe;
		}
	</style>
	
</body>
</html>