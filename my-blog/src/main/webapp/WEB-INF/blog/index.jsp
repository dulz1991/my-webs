<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<title>个人博客</title>
</head>

<body data-spy="scroll" style="background-color:#f0f0f0;">
	
	<div id="top"></div>
	<!-- Preloader -->
    <div id="preloader">
        <div id="status"></div>
    </div>
	
    <div id="main-wrapper">
        <!-- Site Navigation -->
        <div id="menu" style="overflow:auto;overflow-y:hidden;overflow-x:hidden;">
            <nav class="navbar navbar-default" role="navigation">
            	<%-- <div class="collapse navbar-collapse">
					<jsp:include page="include/clock.jsp" />            	
            	</div> --%>
            	<%-- <hr />
            	<div class="collapse navbar-collapse" style="border:0px solid #ccc;">
            		<p>一个人静静坐在电脑面前写代码的感觉，那是什么感觉？那是武林高手闭关修炼的感觉。</p>
            		<p class="float-right"> -- ${username}</p>
            	</div> --%>
            	<div class="collapse navbar-collapse search-input">
					<input name="search-blog" id="search-blog" value="" placeholder="输入标题搜索" />
					<a class="btn btn-search btn-blue white" id="search-btn" href="javascript:;"><i class="fa fa-search"></i></a>            	
            	</div>
            	<hr />
            	<div class="collapse navbar-collapse">
					<a class="btn btn-red white" href="http://my.blog" target="_blank"><i class="fa fa-home"></i> 首页</a>
					<a class="btn btn-green white" href="http://my.blog/code" target="_blank"><i class="fa fa-leaf"></i> 代码笔记</a>            	
            	</div>
            	<hr />
                <div class="collapse navbar-collapse" id="bs-navbar-collapse">
                    <ul class="left-menu">
						<c:forEach items="${menuList}" var="menu" varStatus="st">
							<li menuId="${menu.id}"><a href="#">${menu.name}(${menuMap[menu.id]})</a></li>
						</c:forEach>
						<div id="lanPos"></div>
					</ul>
                </div>
            </nav>
        </div>
        
        <div id="container" style="overflow:auto;overflow-y:auto;overflow-x:hidden;">
        	<div class="col-xs-10 bg-white">
				<div class="blog-list">
					<%-- <c:forEach items="${blogList.list}" var="blog" varStatus="st">
		               <div class="row portfolio-items-main" style="width:92%">
		                  <div class="cbp-so-from-right">
		                     <div class="row">
		                        <div class="col-lg-10 port-desc-wrap">
		                           	<div class="item item-0"><h3><a href="/blog/detail?id=${blog.id}" target="_blank">${blog.title}</a></h3></div>
		                           	<div class="item item-1"><a href="#">${blog.username}</a> · <fmt:formatDate value="${blog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		                           	<div class="item item-2">${blog.preContent}</div>
		                           	<div class="item item-3">
										<span><i class="fa fa-thumbs-o-up"></i> Read(${blog.click})</span> 
										<!-- <span><i class="fa fa-comment-o"></i> Commit(0)</span> -->	
										<span><i class="fa fa-tag"> Tag(s): ${blog.tag}</i></span>
									</div>
		                        </div>
		                        <c:if test="${blog.img!=''}">
		                     		<div class="col-lg-2 img-wrap-main" style="height:140px;">
			                        	<a href="/blog/detail?id=${blog.id}" target="_blank">
			                           		<img class="img-responsive" style="height:100%" src="${blog.img}">
			                           	</a>
			                        </div>
			                    </c:if>
		                     </div>
		                  </div>
			            </div>
					</c:forEach>
				</div> --%>
				<%-- <c:if test="${blogList.page.hasNextPage}">
					<div class="col-xs-12" id="view-more">
						<a href="javascript:;" class="btn btn-block btn-lg btn-info">View More</a>
					</div>
				</c:if> --%>
			</div>
        </div>
    </div>
    </div>


    <div class="right-bottom-btns"> 
        <a class="to-btn to-top-btn sscroll" href="#top" id="backtotop" title="置顶" style="padding-top:10px;"><i class="fa fa-angle-up"></i></a>
        <c:if test="${isLogin}">
        	<a class="to-btn" href="/logout" style="padding-top:10px;" title="退出"><i class="fa fa-sign-out"></i></a>
    		<a class="to-btn" href="/user" style="padding-top:10px;" title="个人中心"><i class="fa fa-user"></i></a>
        </c:if>
        <c:if test="${!isLogin}">	
			<a class="to-btn" href="/login" style="padding-top:10px;" title="登陆"><i class="fa fa-sign-in"></i></a>        
        </c:if>
    </div>

<script type="text/javascript">
$(function(){
	//初始化左边栏菜单
	/* $('ul.left-menu li a').eq(0).addClass('hover'); */
	var menuPosition = 0;
	$('ul.left-menu li').hover(function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$(this).offset().top-menuPosition);
	},function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$('.hover').offset().top-menuPosition);
	});
	
	//选择左边菜单
	$('ul.left-menu li').click(function(){
		/* $('input[name="search-blog"]').val("");
		_title=""; */
		$('ul.left-menu li a').removeClass('hover');
		$(this).children('a').addClass('hover');
		_menuId = $(this).attr("menuId");
		_pageNum = 1; 
		loadBlogList();
	});
	
	$('#search-btn').click(function(){
		_pageNum = 1; 
		_title = $('input[name="search-blog"]').val();
		loadBlogList();
	});
});
</script>

</body>
</html>
