<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	
	<script type="text/javascript" src="/ue/third-party/SyntaxHighlighter/shCore.js"></script>
	<link rel="stylesheet" type="text/css" href="/ue/third-party/SyntaxHighlighter/shCoreDefault.css">
	<script type="text/javascript">
		SyntaxHighlighter.all();
	</script>
	
	<title>${blog.title}</title>
</head>
<body>

<div class="container-fluid container" id="top">
	<div class="row-fluid">
		<div class="title">
			<h3>${blog.title}</h3>
			<fmt:formatDate var="dt" type="date" pattern="yyyy-MM-dd HH:mm:ss" value="${blog.createTime}" />
			<span style="color:#aaa;"><a href="#">${username}</a> · 阅读(${blog.click}) · ${dt}</span>
		</div>
		<div class="content">
			${blog.content}	
		</div>
	</div>
</div>

<div class="right-bottom-btns"> 
        <a class="to-btn to-top-btn sscroll" href="#top" id="backtotop" title="置顶" style="padding-top:10px;"><i class="fa fa-angle-up"></i></a>
        <a class="to-btn" href="/blog" style="padding-top:10px;" title="返回博客"><i class="fa fa-fire"></i></a>
        <c:if test="${isLogin}">
        	<a class="to-btn" href="/logout" style="padding-top:10px;" title="退出"><i class="fa fa-sign-out"></i></a>
    		<a class="to-btn" href="/user" style="padding-top:10px;" title="个人中心"><i class="fa fa-user"></i></a>
        </c:if>
        <c:if test="${!isLogin}">	
			<a class="to-btn" href="/login" style="padding-top:10px;" title="登陆"><i class="fa fa-sign-in"></i></a>        
        </c:if>
    </div>
<%-- <jsp:include page="include/footer.jsp" /> --%>

<style>
body{background:#f0f0f0;}
.container{min-height:600px;background-color:#fff;padding:10px;width:60%;}
.row-fluid{padding:10px;}
.title{border-bottom:1px dashed #aaa;text-align:center;}
.content{margin:20px 0 0 20px;}
</style>
</body>
</html>
