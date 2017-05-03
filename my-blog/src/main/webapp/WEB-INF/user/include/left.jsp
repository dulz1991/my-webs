<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-default" role="navigation">
      	<div class="collapse navbar-collapse" style="text-align:center;">
      		<img src="" style="width:160px;height:160px;border-radius:80px;" />
       		<h4>${username}</h4>
       	</div>
       	<hr />
        <div class="collapse navbar-collapse" id="bs-navbar-collapse">
	        <ul class="left-menu">
				<li><a href="/user"><i class="fa fa-info"></i> 个人资料</a></li>
				<li><a href="/user/myBlogList"><i class="fa fa-bookmark"></i> 我的博客</a></li>
				<li><a href="/user/followBlogList"><i class="fa fa-bookmark"></i> 我关注的博客</a></li>
				<li><a href="/user/editBlog" target="_blank"><i class="fa fa-pencil"></i> 发布博客</a></li>
				<li><a href="/user/pwd"><i class="fa fa-key"></i> 修改密码</a></li>
				<li><a href="/"><i class="fa fa-home"></i> 返回首页</a></li>
				<li><a href="/logout"><i class="fa fa-sign-out"></i> 安全退出</a></li>
				<div id="lanPos"></div>
			</ul>
		</div>
</nav>