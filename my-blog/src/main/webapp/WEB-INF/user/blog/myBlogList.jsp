<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>我的博客</title>
</head>
<body>

	<div id="main-wrapper">
       
        <!-- Site Navigation -->
        <div id="menu" style="overflow:auto;overflow-y:auto;overflow-x:hidden;">
            <jsp:include page="../include/left.jsp"></jsp:include>
        </div>
        
        <div id="container" style="overflow:auto;overflow-y:auto;overflow-x:hidden;">
			<div class="col-xs-10 bg-white mg-top-20">
				<div class="panel-body">
					<form class="form-horizontal form" id="form" action="javascript:void(0);">
						<div class="form-group">
							<div class="col-sm-3">
								<input type="text" class="form-control input" name="title" value="" placeholder="输入标题">
							</div>
							<div class="col-sm-3">
								<select class="form-control select" name="menuId">
									<option value="-1">-- 选择菜单 --</option>
									<c:forEach items="${menuList}" var="item">
									<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-2">
								<button class="btn btn-info btn-icon" onclick="search();">
									<i class="fa fa-search"></i>
									<span>搜索</span>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-xs-10 bg-white mg-top-20">
				<h2><i class="fa fa-bookmark"></i> 我的博客</h2>
					<div class="panel panel-default">
						<div class="panel-body">
							<table class="table table-bordered table-striped" id="datatable">
								<thead>
									<tr>
										<th field="index">序号</th>
										<th field="title" url="http://my.blog/blog/detail?id=" parm="id">标题</th>
										<th field="menuName">分类</th>
										<th field="click">访问数</th>
										<th field="updateTime">最近更新</th>
										<th field="op" field-role="0,2"></th>
									</tr>
								</thead>
								<tbody class="middle-align"></tbody>
							</table>
							<div class="pagebar"></div>
						</div>
					</div>
				</div>
        
        </div>
    
    </div>

	
	<jsp:include page="../../dialog/dialog_delete.jsp"></jsp:include>
	<jsp:include page="../../dialog/dialog_tip.jsp"></jsp:include>
	
<script>
$(function(){
	//初始化左边栏菜单
	$('ul.left-menu li a').eq(1).addClass('hover');
	var menuPosition = $('ul.left-menu').offset().top;
	$('#lanPos').css('top',$('ul.left-menu li').eq(1).offset().top-menuPosition);
	$('ul.left-menu li').hover(function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$(this).offset().top-menuPosition);
	},function(){
		menuPosition = $('ul.left-menu').offset().top;
		$('#lanPos').css('top',$('.hover').offset().top-menuPosition);
	});
	
	$('#datatable').datatable({
		url_load : '/auth/userCenter/getMyBlogList',
		backFn : function(p) {
			// console.log(p);
		}
	});  
});

	function search() {
		var form = $('#form');
		var title = encodeURI(form.find('input[name="title"]').val());
		var menuId = form.find('select[name="menuId"]').val();
		var parm = {
				pageNum : 1,
				pageSize : 10,
				title : title,
				menuId : menuId
			}
		$.fn.doSearch(parm);
	}
	
	function edit(obj) {
		var url="/user/editBlog?id=" + $(obj).attr("id");
		window.open(url);
	}

	function viewDetail(obj) {
		var url="http://my.blog/blog/detail?id=" + $(obj).attr("id");
		window.open(url);
	}
	
	function showDeleteDialog(obj) {
		var _id = $(obj).attr("id");
		var _url = '/auth/userCenter/deleteBlog';
		$("#modal-delete .modal-body").html('确定删除吗?');
		$("input[name='hidden-url']").val(_url);
		$("input[name='hidden-id']").val(_id);
		jQuery("#modal-delete").modal("show", {backdrop: "fade"});
	}
	
</script>
</body>
</html>