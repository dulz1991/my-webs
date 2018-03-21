<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 页面预加载动画 -->
<div id="preloader">
    <div id="status"></div>
</div>

<!-- 页面头部导航栏 -->
<div id="header">
	<a id="logo" href="/" style="color:#42b983;">
		<i class="fa fa-leaf"></i>
		<span><b>My Site Blog</b></span>
	</a>
	<ul id="nav">
		<li>
			<form id="search-form">
		    	<span class="algolia-autocomplete" style="position: relative; display: inline-block; direction: ltr;">
		    		<input type="text" id="search-query-nav" class="search-query st-default-search-input aa-input" autocomplete="off" spellcheck="false" role="combobox" aria-autocomplete="list" aria-expanded="false" aria-owns="algolia-autocomplete-listbox-0" dir="auto" style="position: relative; vertical-align: top;" />
		    		<pre aria-hidden="true" style="position: absolute; visibility: hidden; white-space: pre; font-family: Arial; font-size: 13.3333px; font-style: normal; font-variant: normal; font-weight: normal; word-spacing: 0px; letter-spacing: normal; text-indent: 0px; text-rendering: auto; text-transform: none;"></pre>
		    		<span class="aa-dropdown-menu" role="listbox" id="algolia-autocomplete-listbox-0" style="position: absolute; top: 100%; z-index: 100; display: none; left: 0px; right: auto;">
		    			<div class="aa-dataset-1"></div>
		    		</span>
		    	</span>
		  	</form>
		</li>
		<li><a href="/" class="nav-link"><i class="fa fa-home"></i> 首页</a></li>
		<li><a href="/blog" class="nav-link">Blog文档</a></li>
		<li><a href="/code" class="nav-link">Code笔记</a></li>
		<li><a href="/code" class="nav-link">Demo前端</a></li>
		<c:if test="${isLogin}">
    		<!-- <li><a href="/user" class="nav-link"><i class="fa fa-user"></i>个人中心</a></li> -->
    		<li><a href="/logout" class="nav-link"><i class="fa fa-sign-out"></i> 退出</a></li>
        </c:if>
        <c:if test="${!isLogin}">	
        	<li><a href="/login" class="nav-link"><i class="fa fa-sign-in"></i>登陆</a></li>
        </c:if>
	</ul>
</div>

<!-- 右下角 -->
<div class="right-bottom-btns" style="z-index:10"> 
     <a class="to-btn to-top-btn sscroll" href="#top" id="backtotop" title="置顶"><i class="fa fa-angle-up"></i></a>
     <c:if test="${isLogin}">
     	<!-- <a class="to-btn" href="/logout" title="退出"><i class="fa fa-sign-out"></i></a> -->
 		<!-- <a class="to-btn" href="/auth/userCenter/index" title="个人中心"><i class="fa fa-user"></i></a> -->
     </c:if>
     <c:if test="${!isLogin}">	
		<!-- <a class="to-btn" href="/login" title="登陆"><i class="fa fa-sign-in"></i></a> -->        
     </c:if>
</div>
