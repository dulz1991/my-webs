<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="right-bottom-btns" style="z-index:10"> 
        <a class="to-btn to-top-btn sscroll" href="#top" id="backtotop" title="置顶"><i class="fa fa-angle-up"></i></a>
        <c:if test="${isLogin}">
        	<a class="to-btn" href="/logout" title="退出"><i class="fa fa-sign-out"></i></a>
    		<!-- <a class="to-btn" href="/auth/userCenter/index" title="个人中心"><i class="fa fa-user"></i></a> -->
        </c:if>
        <c:if test="${!isLogin}">	
			<a class="to-btn" href="/login" title="登陆"><i class="fa fa-sign-in"></i></a>        
        </c:if>
    </div>
