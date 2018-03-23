<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>代码笔记</title>
</head>

<body data-spy="scroll docs" class="docs" id="top">

	<!-- 导航栏 -->
	<jsp:include page="../include/header.jsp"></jsp:include>
	<!-- 导航栏结束 -->

    <div id="main-wrapper">
        
        <div id="menu">
	        <div class="sidebar" style="margin:0px;top:10px;">
	        	<div class="list">
	        		<ul id="side-menu" class="menu-root">
	        			<c:forEach items="${subMenuList}" var="item" varStatus="status">
	       					<li>
	       						<a class='sscroll sidebar-link <c:if test='${status.index==0}'>current</c:if>' href="#link_${fn:split(item.key, ',')[0]}">${fn:split(item.key, ',')[1]} (${fn:split(item.key, ',')[2]})</a>
	       					</li>
	       				</c:forEach>
					</ul>
	        	</div>
	        </div>
        </div>
        
        <div id="container">
        	<c:forEach items="${subMenuList}" var="item" varStatus="status">
        		<section id="link_${fn:split(item.key, ',')[0]}" class="<c:if test='${status.count%2==1}'>gray</c:if>">
	                <div class="row">
	                    <div class="col-md-12">
	                        <div class="header-content">
	                            <h2>${fn:split(item.key, ',')[1]}</h2>
	                            <h3></h3>
	                        </div>
	                    </div>
	                    <div class="col-md-12">
	                        <c:forEach items="${item.value}" var="sub" varStatus="subIndex">
	                        	<c:if test="${sub.status == 0}">
		            				<a href="javascript:;" class="button hover-shadow" style="background-color:#ddd;color:#fff;border-color:#ddd;">${sub.name}</a>
		            			</c:if>
		            			<c:if test="${sub.status == 1}">
		            				<a href="/code/guide?subMenuId=${sub.id}" class="button hover-shadow" style="background-color:#9999ff;border-color:#9999ff;color:#fff;">${sub.name}</a>
		            			</c:if>
		            			<c:if test="${sub.status == 2}">
		            				<a href="/code/guide?subMenuId=${sub.id }" class="button hover-shadow" style="background-color:#00cc66;color:#fff;">${sub.name}</a>
		            			</c:if>
	                        </c:forEach>
	                    </div>
	                </div>
	            </section>
        	</c:forEach>
        </div>
        
    </div>

<script type="text/javascript">
$(function(){
	$('#side-menu li a').click(function(){
		$('#side-menu li a').removeClass('current');
		$(this).addClass('current');
	});
});
</script>

</body>
</html>
