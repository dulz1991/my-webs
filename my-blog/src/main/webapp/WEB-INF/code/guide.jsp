<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
    <script type="text/javascript" src="/ue/third-party/SyntaxHighlighter/shCore.js"></script>
	<link rel="stylesheet" type="text/css" href="/ue/third-party/SyntaxHighlighter/shCoreDefault.css">
	<script type="text/javascript">
		SyntaxHighlighter.all();
	</script>
    
    <title>${code.item} -- ${subMenu.name}</title>
</head>

<body class="docs">

<div id="top"></div>

<!-- 导航栏 -->
<jsp:include page="include/header.jsp"></jsp:include>
<!-- 导航栏结束 -->

<!-- main -->
<div id="main" class="fix-sidebar">
	<!-- 左边菜单 -->
	<div class="sidebar">
		<jsp:include page="include/left.jsp"></jsp:include>
	</div>
	<!-- 左边菜单结束 -->
	
	<!-- 右边内容 -->
	<div class="content guide with-sidebar index-guide">
		<h2 style="padding:0px;margin:0px;"><blockquote style="margin:10px;">${code.item}</blockquote></h2>
    	${code.content}
   	</div>
	<!-- 右边内容结束 -->
</div>

<jsp:include page="../include/right_bottom.jsp"></jsp:include>

<style>
.content p{padding-bottom:0;}
</style>
</body>
</html>
