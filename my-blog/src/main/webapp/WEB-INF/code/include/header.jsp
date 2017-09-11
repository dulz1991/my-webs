<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="header">
	<a id="logo" href="/code" style="color:#42b983;">
		<i class="fa fa-leaf"></i>
		<span><b>代码笔记</b></span>
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
		<li><a href="/blog" class="nav-link">技术文档</a></li>
		<li><a href="/code" class="nav-link current">代码笔记</a></li>
		<c:if test="${isLogin}">
    		<li><a href="/auth/userCenter/index" class="nav-link"><i class="fa fa-user"></i>个人中心</a></li>
        </c:if>
        <c:if test="${!isLogin}">	
        	<li><a href="/login" class="nav-link"><i class="fa fa-sign-in"></i>登陆</a></li>
        </c:if>
	</ul>
</div>
