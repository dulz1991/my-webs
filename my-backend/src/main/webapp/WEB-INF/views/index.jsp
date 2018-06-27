<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="basePage/base_meta.jsp"></jsp:include>
    <title>后台管理系统</title>
</head>
<body id="tablePos"> 
	<!-- 头部导航 -->
	<jsp:include page="basePage/base_head.jsp"></jsp:include>
    
    <div class="main-container container-fluid">
        <div class="page-container">
        	<!-- 左侧导航 -->
    		<jsp:include page="basePage/base_left.jsp"></jsp:include>    	
            <!-- 右侧内容 -->
            <div class="page-content">
            	<div class="page-header position-relative" style="background-color:rgba(255,255,255,0)">	
            		<div class="header-title"><h1></h1></div>
            		<div class="header-buttons">		
            			<a class="sidebar-toggler" href="#"><i class="fa fa-arrows-h"></i></a>		
            			<a class="refresh" id="refresh-toggler" href="#" onclick="location.reload()"><i class="glyphicon glyphicon-refresh"></i></a>		
            			<a class="fullscreen" id="fullscreen-toggler" href="#"><i class="glyphicon glyphicon-fullscreen"></i></a>
            		</div>
            	</div>
                
                <div class="page-body">
                	
	                <c:if test="${not empty toUrl }"> 
	                	<c:import url="${toUrl }"></c:import>
	                </c:if>
	                <c:if test="${empty toUrl }">
	                	<c:import url="/index_blank"></c:import>
	                </c:if>
                
                </div>
            </div>
            <!-- 右侧内容结束 -->
        </div>
    </div>

</body>
</html>
