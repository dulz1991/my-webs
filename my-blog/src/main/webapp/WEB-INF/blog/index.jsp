<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<title>技术文档</title>
</head>

<body>
	<!-- head -->
	<jsp:include page="include/header.jsp"></jsp:include>

	<div class="container">
		
		<!-- 左侧导航 -->
		<div class="menu">
			<c:forEach items="${menuList}" var="menu" varStatus="st">
				<h3 class="<c:if test="${not empty blogMenu and blogMenu.id == menu.id}">active</c:if>">
					<i class="fa fa-sitemap"></i> ${menu.name}(${menuCountMap[menu.id]})
					<c:if test="${isLogin }">
						<i class="fa fa-plus fa-lg fa-edit-icon" title="创建" 
						onclick="javascript:self.location='/auth/blog/edit?menuId=${menu.id}'" style="color:#00f"></i>
					</c:if>
				</h3>
				<ul class="">
					<c:forEach items="${menuBlogMap[menu.id]}" var="item" varStatus="indx">
						<li><a class="<c:if test="${not empty blogId and blogId == item.id}">selected</c:if>" href="/blog?blogId=${item.id}">${item.title}</a></li>	
					</c:forEach>		
				</ul>
			</c:forEach>
		</div>
		
		<!-- 内容 -->
		<div class="content">
		
			<!-- 内容头部 -->
			<c:if test="${not empty entity }">
				<div class="get-menu">
					<i class="fa fa-location-arrow"></i> 
					<a href="#">${blogMenu.name }</a>  > <a href="/blog?blogId=${entity.id }">${entity.title }</a> 
					<c:if test="${isLogin }">
						<a href="/auth/blog/edit?blogId=${entity.id }&menuId=${entity.menuId}"><i class="fa fa-edit fa-lg fa-edit-icon" title="编辑" style="color:#00f"></i></a>
						<a href="/auth/blog/edit?menuId=${entity.menuId}"><i class="fa fa-plus fa-lg fa-edit-icon" title="创建" style="color:#00f"></i></a>
						<a href="javascript:if(confirm('确实要删除吗?'))location='/auth/blog/delete?id=${entity.id }'"><i class="fa fa-trash-o fa-lg fa-edit-icon" title="删除" style="color:#f00"></i></a>
					</c:if>
				</div>
			</c:if>
			<!-- 内容结束 -->

			<!-- 内容主体 -->
			<div class="menu1 menu_tab">
				<div id="tab1" class="tab active">
					<c:if test="${not empty entity }">
						<p>${entity.content }</p>
					</c:if>
					
					<c:if test="${empty entity }">
						<p class="tt">1.概要说明</p>
						<p>用于知识或文档的在线查看和编辑</p>
						<p class="tt">2.使用说明</p>
						<p>2.1 登录后可在当前文档页新增、修改或删除文档</p>
						<p>2.2 登录后，在左侧菜单栏后的"<i class="fa fa-plus fa-lg" style="color:#00f"></i>"号即可新增文档</p>					
					</c:if>
				</div>
			</div>
			<!-- 内容结束 -->

		</div>
		<!-- 内容区结束 -->
		
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
	}
})
</script>
</body>
</html>
