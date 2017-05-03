<#-- 在基本宏里定义#compress 压缩页面指令， 扩展页就不需要定义了 -->
<#compress> 

	<#-- 
		base: 模板名 
		base_title：页面标题 
		base_keywords： 可由扩展页传入的标题和关键字 
		base_js,base_css: 由扩展页传入其自己的css js 我这里定义的是一个数据，方便传入多个
		openIndex,activeIndex: 左侧菜单高亮显示
	-->
	<#macro base base_title base_keywords="" base_js=[] base_css=[] openIndex=0 activeIndex=0>
		
		<!DOCTYPE html>
		<html>
			<head>
				<meta charset="UTF-8">
				<meta http-equiv="X-UA-Compatible" content="IE=edge">
				<meta name="viewport" content="width=device-width, initial-scale=1.0" />
				<meta name="description" content="Xenon Boostrap Admin Panel" />
				<meta name="author" content="" />
				<title>${base_title}</title>
				<link rel="stylesheet" href="/css/fonts/linecons/css/linecons.css">
				<link rel="stylesheet" href="/css/fonts/fontawesome/css/font-awesome.min.css">
				<link rel="stylesheet" href="/css/bootstrap.css">
				<link rel="stylesheet" href="/css/xenon-core.css">
				<link rel="stylesheet" href="/css/xenon-forms.css">
				<link rel="stylesheet" href="/css/xenon-components.css">
				<link rel="stylesheet" href="/css/xenon-skins.css">
				<link rel="stylesheet" href="/css/custom.css">
				<link rel="stylesheet" href="/css/jquery.page.css">
				<#-- 遍历扩展页css -->
				<#list base_css as c>
				<link rel="stylesheet" href="${c}">
				</#list>
				
				<script src="/js/jquery-1.11.1.min.js"></script>
			<script src="/js/bootstrap.min.js"></script>
			<script src="/js/TweenMax.min.js"></script>
			<script src="/js/resizeable.js"></script>
			<script src="/js/joinable.js"></script>
			<script src="/js/xenon-api.js"></script>
			<script src="/js/xenon-toggles.js"></script>
			<script src="/js/xenon-widgets.js"></script>
			<!-- <script src="/js/devexpress-web-14.1/js/globalize.min.js"></script>
			<script src="/js/devexpress-web-14.1/js/dx.chartjs.js"></script> -->
			<script src="/js/toastr/toastr.min.js"></script>
			<script src="/js/xenon-custom.js"></script>
			
			<script src="/js/jquery.form.js"></script>
			<script src="/js/common-util.js"></script>	
			<script src="/js/jquery.page.js"></script>
			<script src="/js/jquery.dateutil.js"></script>
			<script src="/js/jquery.datatable.js"></script>
			<script src="/js/jquery.select.js"></script>
			<#-- 遍历公共js -->
			<#list base_js as j>
			<script src="${j}"></script>
			</#list>
			<script type="text/javascript">
			$(function(){
				$('#main-menu li.li').removeClass('active').removeClass('opened');
				$('#main-menu li.li').eq(${openIndex}).addClass('active').addClass('opened');
				$('#main-menu li.li').eq(${openIndex}).find('ul li').eq(${activeIndex}).addClass('active');
			});
			</script>
			</head>
			
			<body class="page-body">
				<div class="page-container">
					<#include "leftMenu.ftl"> 
					<div class="main-content">
						<#include "head.ftl"> 
						
						<#-- #nested 指令表示扩展页内容将嵌套在此处 -->
			    		<#nested>	
					
					</div>
				</div>
			    
			    <#-- 以下是公共页脚	-->
			    <#-- 
			    <footer class="blog-footer">
			        <p>? 2015-2016 初</p>
			    </footer>
			    -->
			</body>
			</html>
	
	</#macro>		

</#compress>

