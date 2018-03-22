<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>技术文档</title>
</head>

<body data-spy="scroll docs" class="docs" id="top">
	<!-- head -->
	<jsp:include page="../include/header.jsp"></jsp:include>

	<div class="container">
		
		<!-- 左侧折叠菜单 -->        
		<div class="menu">
			<c:forEach items="${menuList}" var="menu" varStatus="st">
				<h3 class="<c:if test="${not empty blogMenu and blogMenu.id == menu.id}">active</c:if>">
					<!-- <i class="fa fa-sitemap"></i> -->
					<span style="background-color:#ff0055;color:#fff;padding:2px 8px;border-radius:30px;"> ${fn:substring(fn:toUpperCase(menu.name), 0, 1)}</span> ${menu.name}(${menuCountMap[menu.id]})
					<c:if test="${isLogin }">
						<i class="fa fa-plus fa-lg fa-edit-icon" title="创建" 
						onclick="javascript:self.location='/auth/blog/edit?menuId=${menu.id}'" style="color:#00f"></i>
					</c:if>
				</h3>
				<ul class="">
					<c:forEach items="${menuBlogMap[menu.id]}" var="item" varStatus="indx">
						<li title="${item.title}"><a class="<c:if test="${not empty blogId and blogId == item.id}">selected</c:if>" href="/blog?blogId=${item.id}">${item.title}</a></li>	
					</c:forEach>		
				</ul>
			</c:forEach>
		</div>
		<!-- 左侧折叠菜单 结束 -->
		
		<!-- 右侧内容 -->
		<div class="content">
			<!-- 内容头部 -->
			<c:if test="${not empty entity }">
				<div class="get-menu">
					<i class="fa fa-location-arrow"></i> 
					<a href="#"><b>${blogMenu.name }</b></a>  > <a href="/blog?blogId=${entity.id }"><b>${entity.title }</b></a> 
					<c:if test="${isLogin }">
						<a href="/auth/blog/edit?blogId=${entity.id }&menuId=${entity.menuId}"><i class="fa fa-edit fa-lg fa-edit-icon" title="编辑" style="color:#00f"></i></a>
						<a href="/auth/blog/edit?menuId=${entity.menuId}"><i class="fa fa-plus fa-lg fa-edit-icon" title="创建" style="color:#00f"></i></a>
						<a href="javascript:if(confirm('确实要删除吗?'))location='/auth/blog/delete?id=${entity.id }'"><i class="fa fa-trash-o fa-lg fa-edit-icon" title="删除" style="color:#f00"></i></a>
					</c:if>
				</div>
			</c:if>
			<!-- 内容结束 -->

			<!-- 内容主体 -->
			<div class="menu_tab">
				<div id="tab1" class="tab active">
					<c:if test="${not empty entity }">
						<p>${entity.content }</p>
					</c:if>
					
					<c:if test="${empty entity }">
						<p class="tt">1.概要说明</p>
						<p>用于知识或文档的在线查看和编辑</p>
						<p class="tt">2.使用说明</p>
						<p>2.1 登录后可在当前文档页新增"<i class="fa fa-plus fa-lg" style="color:#00f"></i>"、修改"<i class="fa fa-edit fa-lg" style="color:#00f"></i>"或删除"<i class="fa fa-trash-o fa-lg" style="color:#f00"></i>"文档</p>
						<p>2.2 登录后，在左侧菜单栏后的"<i class="fa fa-plus fa-lg" style="color:#00f"></i>"号即可新增文档</p>					
					</c:if>
				</div>
			</div>
			<!-- 内容结束 -->
		</div>
		<!-- 右侧内容 结束 -->
		
	</div>
	
	<script>
	$(function(){
		var menuId = '${entity.menuId}';
		if(menuId!=''){
			$('.menu h3').each(function (i){
				if($(this).hasClass('active')){
					$(".menu > ul").eq(i).show();			
				}
			});
		} else {
			$(".menu > ul").eq(0).show();
		}
	})
	</script>
	<style>
		.container{
			font-family: 微软雅黑;
			background: #fff;
			padding: 30px;
			margin:20px auto;
	        overflow: hidden;
		}
		.container .menu{
			width: 22%;
			margin-left: 30px;
			float: left;
			border-right: 1px solid #aaa;
		}
		.container .menu h3{
			font-size: 14px;
			cursor: pointer;
		}
	    .container .menu ul{
	        display: none;
	        padding-left:20px;
	    }
		.container .menu ul li{
			height: 28px;
			line-height: 28px;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		.container .menu ul li a:hover{
			padding:5px 10px;
			background: #9FB6CD;
			border-radius: 14px;
			-webkit-border-radius: 14px;
			-moz-border-radius: 14px;
			-moz-border-radius:14px;
			color:#fff;
		}
		ul{
			list-style: none;
		}
		.container .menu .selected{
			background: #ccc;
			border-radius: 14px;
			padding:5px 10px;
		}
		.container .content{
			margin-top:-30px;
			width: 100%;
			margin-left: 26%;
		}
		.container .content .get-menu{
			margin-left: -10px;
		}
		.container .content .get-menu a{
			display: inline-block;
			font-size: 15px;
		    height: 20px;
		}
		.container .content .get-menu a:last-child{
			margin-left: -8px;
			color: #7D9EC0;
		}
		.container .content .get-menu span{
			display: inline-block;
		    height: 40px;
		    margin-left: -110px;
		}
		.fa-edit-icon{
			margin-left:20px;
			cursor: pointer;
		}
	</style>
</body>
</html>
