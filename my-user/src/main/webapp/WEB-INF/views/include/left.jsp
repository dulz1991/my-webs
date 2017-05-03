<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar-menu toggle-others fixed">
	<div class="sidebar-menu-inner">

		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a href="javascript:void(0);" class="logo-expanded"> <!-- <img src="/admin/images/logo@2x.png" width="80" alt="" /> -->
					<h2 style="color: #fff; padding: 0; margin: 0;">
						<i class="fa fa-cog"></i> Admin
					</h2>
				</a>
			</div>
			<div class="mobile-menu-toggle visible-xs">
				<a href="#" data-toggle="user-info-menu"> <i class="fa-bell-o"></i>
					<span class="badge badge-success">7</span>
				</a> <a href="#" data-toggle="mobile-menu"> <i class="fa-bars"></i>
				</a>
			</div>
			<div class="settings-icon">
				<a href="#" data-toggle="settings-pane" data-animate="true"> <i
					class="linecons-cog"></i>
				</a>
			</div>
		</header>

		<ul id="main-menu" class="main-menu">
			<li class="li opened">
				<a href="/">
					<i class="linecons-globe"></i>
					<span class="title">主页</span>
				</a>
			</li>
			<li class="li">
				<a href="/auth/user/index"><i class="linecons-user"></i><span class="title">用户管理</span></a>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-fire"></i> 
					<span class="title">技术博客管理</span>
				</a>
				<ul>
					<li><a href="/auth/blog/index"><span class="title">列表管理</span></a></li>
					<li><a href="/auth/blogmenu/index"><span class="title">菜单管理</span></a></li>
					<li><a href="/auth/bloglog/index"><span class="title">日志管理</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-note"></i> 
					<span class="title">代码笔记管理</span>
				</a>
				<ul>
					<li><a href="/auth/code/index"><span class="title">列表管理</span></a></li>
					<li><a href="/auth/codemenu/index"><span class="title">菜单管理</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-camera"></i> 
					<span class="title">旅行日志管理</span>
				</a>
				<ul>
					<li><a href="/auth/picblog/index"><span class="title">列表管理</span></a></li>
					<li><a href="/auth/picbloglog/index"><span class="title">日志管理</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-attach"></i> 
					<span class="title">demo管理</span>
				</a>
				<ul>
					<li><a href="/auth/demo/index"><span class="title">列表管理</span></a></li>
					<li><a href="/auth/demomenu/index"><span class="title">菜单管理</span></a></li>
				</ul>
			</li>
			<!-- <li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-note"></i> 
					<span class="title">项目管理</span>
				</a>
				<ul>
					<li><a href="/user"><span class="title">用户管理</span></a></li>
					<li><a href="/handbook"><span class="title">代码笔记管理</span></a></li>
					<li><a href="/blog"><span class="title">技术博客管理</span></a></li>
					<li><a href="/pic"><span class="title">图片博客管理</span></a></li>
					<li><a href="/demo"><span class="title">demo管理</span></a></li>
				</ul>
			</li> -->
			<li class="li">
				<a href="/auth/sys/index"><i class="linecons-clock"></i><span class="title">系统维护</span></a>
			</li>
			<li class="li">
				<a href="/mall"><i class="linecons-mail"></i><span class="title">邮件管理</span></a>
			</li>
			<li class="li">
				<a href="/bell"><i class="linecons-sound"></i><span class="title">提醒管理</span></a>
			</li>
		</ul>

	</div>
</div>