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
				<script src="/js/devexpress-web-14.1/js/globalize.min.js"></script>
				<script src="/js/devexpress-web-14.1/js/dx.chartjs.js"></script>
				
				<script src="/js/jquery.form.js"></script>
				<script src="/my/my.common.js"></script>	
				<script src="/my/my.biz.js"></script>	
				<script src="/my/my.datatable.js"></script>
				
				<link rel="stylesheet" href="/my/my.page.css">
				<script src="/my/my.page.js"></script>
				
				<link rel="stylesheet" href="/js/sweetalert2/sweetalert2.css">
				<script src="/js/sweetalert2/sweetalert2.js"></script>
				
				<link rel="stylesheet" href="/js/toast/animate.css">
				<link rel="stylesheet" href="/js/toast/toast.css">
				<script src="/js/toast/toast.js"></script>
				
				<#-- ueditor -->
				<script src="/ue/ueditor.config.js"></script>
				<script src="/ue/ueditor.all.js"></script>
				<link href="/ue/themes/default/css/ueditor.css" rel="stylesheet">
				
				<#-- ztree -->
				<link rel="stylesheet" href="/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
				<script type="text/javascript" src="/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
				<script type="text/javascript" src="/js/zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>
				
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
					<!-- 左侧菜单 -->
					<#include "left_menu.ftl"> 
					
					<!-- 主内容区域 start -->					
					<div class="main-content">
						<!-- 头部导航栏 -->
						<#include "head.ftl"> 
						
						<#-- #nested 指令表示扩展页内容将嵌套在此处 -->
			    		<#nested>	
					</div>
					<!-- 主内容区域 end -->
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

	
	<#--通用型的select语句-->
	<#--通过起别名的形式调用自定义的指令-->
    <#--<#import "/include/select.ftl" as my/>-->
    <#--对象是集合元素有默认值 -->
    <#--<@my.select id="person" datas=["张三","李四","王五"] value="李四" defaultValue="请选择人" />-->
    <#--对象是集合元素无默认值-->
    <#--<@my.select id="address" datas=["北京","上海","广州"] defaultValue="请选择地点"/>-->
    <#--对象是集合对象无默认值-->
    <#--<@my.select id="emp" datas=emps key="id" text="name"  defaultValue="请选择人" />-->
    <#--对象是map对象有默认值-->
    <#--<@my.select id="sex" datas={"0":"男","1":"女"}  value="1" defaultValue="请选择性别"/>-->
    <#--对象是集合对象有默认值-->
    <#--<@my.select id="group" datas=groups key="id" text="name" value="1" defaultValue="请选择组"/>-->
	<#macro select id datas value="" defaultValue="" key="" text="" class="form-control select" onchange="">
	    <select id="${id}" name="${id}" class="${class}" <#if onchange!="">onchange="${onchange}"</#if>>
	    	 <#if datas?exists>
	    	 
		    	 <#if defaultValue!="">
		    		<option value="">${defaultValue}</option> 
		    	 </#if>
		    	 
		        <#--判断对象是否为map-->
		        <#if datas?is_hash_ex>
		            <#--循环map的key值-->
		            <#list datas?keys as key>
		                <#--如果传进来的key值和默认的值相等，则选中这个值-->
		                <#if key==value>
		                    <option value="${key}" selected>${datas[key]}</option>
		                <#else>
		                    <option value="${key}">${datas[key]}</option>
		                </#if>
		            </#list>    
		        <#else>
		            <#list datas as data>
		                <#--如果key值不为空-->
		                <#if key!="">
		                    <#--传进来的默认value和通过data的key取出来的值相等，则选中-->
		                    <#if value==data[key]?string>
		                        <option value="${data[key]}" selected>${data[text]}</option>
		                    <#else>
		                        <option value="${data[key]}" >${data[text]}</option>
		                    </#if>
		                <#else>
		                    <#if data==value>
		                        <option value="${data}" selected>${data}</option>
		                    <#else>
		                        <option value="${data}">${data}</option>
		                    </#if>
		                </#if> 
		            </#list>
		        </#if>
	        
	        </#if>
	    <select>
	</#macro>

	<#macro searchForm id="" class="" title="">
		<div class="row">
			<div class="col-sm-12 panel panel-default">
				<#if title!="">
					<div class="panel-heading">
						<h3 class="panel-title">${title}</h3>
					</div>
				</#if>
				<div class="panel-body">
					<form class="form-horizontal ${class}" <#if id!="">id="${id}"</#if>  action="javascript:void(0);">
						<div class="form-group">
							<#nested>
						</div>
					</form>
				</div>
			</div>
		</div>
	</#macro>
	
	<#macro searchInput type="text" class="col-sm-3" title="" name="" value="">
		<#if type=="hidden">
			<input type="${type}" class="form-control input" name="${name}" value="${value}">
		</#if>
		<#if type!="hidden">
			<div class="${class}">
				${title}
				<input type="${type}" class="form-control input" name="${name}" value="${value}">
			</div>
		</#if>
	</#macro>
	
	<#macro searchSelect id datas value="" defaultValue="" key="" text="" class="form-control select" onchange="" title="">
		<div class="col-sm-3">
			${title}
		    <select id="${id}" name="${id}" class="${class}" <#if onchange!="">onchange="${onchange}"</#if>>
		    	 <#if datas?exists>
		    	 
			    	 <#if defaultValue!="">
			    		<option value="">${defaultValue}</option> 
			    	 </#if>
			    	 
			        <#--判断对象是否为map-->
			        <#if datas?is_hash_ex>
			            <#--循环map的key值-->
			            <#list datas?keys as key>
			                <#--如果传进来的key值和默认的值相等，则选中这个值-->
			                <#if key==value>
			                    <option value="${key}" selected>${datas[key]}</option>
			                <#else>
			                    <option value="${key}">${datas[key]}</option>
			                </#if>
			            </#list>    
			        <#else>
			            <#list datas as data>
			                <#--如果key值不为空-->
			                <#if key!="">
			                    <#--传进来的默认value和通过data的key取出来的值相等，则选中-->
			                    <#if value==data[key]?string>
			                        <option value="${data[key]}" selected>${data[text]}</option>
			                    <#else>
			                        <option value="${data[key]}" >${data[text]}</option>
			                    </#if>
			                <#else>
			                    <#if data==value>
			                        <option value="${data}" selected>${data}</option>
			                    <#else>
			                        <option value="${data}">${data}</option>
			                    </#if>
			                </#if> 
			            </#list>
			        </#if>
		        
		        </#if>
		    <select>
	    </div>
	</#macro>
	
	<#macro searchButton class="col-sm-2" style="margin-top:15px;" clickEvent="$.fn.autoSearch('.form')">
		<div class="${class}">
			<button class="btn btn-info btn-icon" <#if style!="">style="${style}"</#if> onclick="${clickEvent}">
				<i class="fa-search"></i>
				<span>搜索</span>
			</button>
		</div>
	</#macro>
	
	<#macro dataList>
		<div class="row">
			<div class="panel panel-default">
				<#nested>
			</div>
		</div>
	</#macro>
	
	<#macro dataHeader title="">
		<div class="panel-heading">
			<#if title!=""><h3 class="panel-title">${title}</h3></#if>
			<div class="panel-options">
				<#nested>
			</div>
		</div>
	</#macro>
	
	<#macro dataTable tableId="datatable" pageId="pageDiv">
		<div class="panel-body">
			<table class="table table-bordered table-striped" id="${tableId}">
				<thead>
					<tr>
						<#nested>		
					</tr>
				</thead>
				<tbody class="middle-align"></tbody>
			</table>
			<div id="${pageId}"></div>
		</div>
	</#macro>

</#compress>

