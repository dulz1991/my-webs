<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="cd-main-header">
	<a class="cd-logo" href="/blog" style="color:#42b983;margin-top:-10px;">
		<h3><i class="fa fa-leaf"></i> <span><b>技术文档</b></span></h3>
	</a>

	<nav class="cd-nav">

		<ul id="cd-primary-nav" class="cd-primary-nav">
			<li class=""><a href="/"><i class="fa fa-home"></i> 首页</a></li>
			<li><a href="/blog" class="selected">技术文档</a></li>
			<li><a href="/code">代码笔记</a></li>
			<c:if test="${isLogin}">
	    		<li><a href="/auth/userCenter/index" class="nav-link"><i class="fa fa-user"></i>个人中心</a></li>
	        </c:if>
	        <c:if test="${!isLogin}">	
	        	<li><a href="/login" class="nav-link"><i class="fa fa-sign-in"></i>登陆</a></li>
	        </c:if>

			<li class="has-children">
				<a href="http://www.jq22.com/demo/jquery-nav-150519214407/#">Services</a>
				<ul class="cd-nav-icons is-hidden">
					<li class="go-back"><a href="http://www.jq22.com/demo/jquery-nav-150519214407/#0">Menu</a></li>
					<li class="see-all"><a href="http://www.jq22.com/demo/jquery-nav-150519214407/#">Browse Services</a></li>
					<li>
						<a class="cd-nav-item item-1" href="http://www.jq22.com/demo/jquery-nav-150519214407/#">
							<h3>Service #1</h3>
							<p>This is the item description</p>
						</a>
					</li>
					<li>
						<a class="cd-nav-item item-2" href="http://www.jq22.com/demo/jquery-nav-150519214407/#">
							<h3>Service #2</h3>
							<p>This is the item description</p>
						</a>
					</li>
					<li>
						<a class="cd-nav-item item-3" href="http://www.jq22.com/demo/jquery-nav-150519214407/#">
							<h3>Service #3</h3>
							<p>This is the item description</p>
						</a>
					</li>
					<li>
						<a class="cd-nav-item item-4" href="http://www.jq22.com/demo/jquery-nav-150519214407/#">
							<h3>Service #4</h3>
							<p>This is the item description</p>
						</a>
					</li>
					<li>
						<a class="cd-nav-item item-5" href="http://www.jq22.com/demo/jquery-nav-150519214407/#">
							<h3>Service #5</h3>
							<p>This is the item description</p>
						</a>
					</li>
					<li>
						<a class="cd-nav-item item-6" href="http://www.jq22.com/demo/jquery-nav-150519214407/#">
							<h3>Service #6</h3>
							<p>This is the item description</p>
						</a>
					</li>
				</ul>
			</li>

		</ul>
		<!-- primary-nav -->
	
	</nav>
			
	<ul class="cd-header-buttons">
		<li><a class="cd-search-trigger" href="http://www.jq22.com/demo/jquery-nav-150519214407/#cd-search">Search<span></span></a></li>
		<li><a class="cd-nav-trigger" href="http://www.jq22.com/demo/jquery-nav-150519214407/#cd-primary-nav">Menu<span></span></a></li>
	</ul>
	</header>
	
	
<div id="cd-search" class="cd-search">
	<form>
		<input type="search" placeholder="Search...">
	</form>
</div>