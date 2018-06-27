<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="basePage/base_meta.jsp"></jsp:include>
    <title>鑫药盟后台管理系统</title>
</head>
<body>
	<!-- 头部导航 -->
	<jsp:include page="basePage/base_head.jsp"></jsp:include>
    
    <div class="main-container container-fluid">
        <div class="page-container">
        	
        	<!-- 左侧导航 -->
    		<jsp:include page="basePage/base_left.jsp"></jsp:include>    	
    		
            <!-- 右侧内容 -->
            <div class="page-content">
                <div class="page-body">
                
                <c:import url="/flowDisposal/getProcessListPage.do"></c:import>
                
                </div>
            </div>
            <!-- 右侧内容结束 -->
        
        </div>
    </div>
    
<script type="text/javascript">

</script>
    
</body>
</html>
