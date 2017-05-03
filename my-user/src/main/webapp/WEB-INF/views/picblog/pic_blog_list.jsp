<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>图片博客管理</title>
</head>
<body class="page-body">
	<jsp:include page="../include/hidden_header.jsp"></jsp:include>
	
	<div class="page-container">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-sm-12 panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">搜索框</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" id="form" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-3">
									<input type="text" class="form-control" name="username" id="username" value="" placeholder="输入用户名">
								</div>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="title" id="title"  value="" placeholder="输入标题">
								</div>
								<div class="col-sm-2">
									<button class="btn btn-info btn-icon" onclick="search()">
										<i class="fa-search"></i>
										<span>搜索</span>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">图片博客列表</h3>
						<div class="panel-options">
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th field="id">编号</th>
									<th field="title">标题</th>
									<th field="username">用户名</th>
									<th field="click">点击</th>
									<th field="createTime">创建时间</th>
									<th field="updateTime">更新时间</th>
									<th field="op" field-role="0"></th>
								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
						<div class="pagebar"></div>
					</div>
					
				</div>
			</div>
		</div>
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>

<jsp:include page="../dialog/dialog_delete.jsp"></jsp:include>
<script>
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(4).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(4).find('ul li').eq(0).addClass('active');
	
	$('#datatable').datatable({
		url_load : '/auth/picblog/getList',
		backFn : function(p) {
			// console.log(p);
		}
	});  
});

function search() {
	var form = $('#form');
	var title = form.find('input[name="title"]').val();
	var username = form.find('input[name="username"]').val();
	var status = form.find('select[name="status"]').val();
	var parm = {
			pageNum : 1,
			pageSize : 10,
			title : title,
			username : username,
			status : status,
		}
	$.fn.doSearch(parm);
}
	function showDeleteDialog(obj) {
		var _id = $(obj).attr("id");
		var _index = $(obj).attr("index");
		var _url = '/auth/picblog/doDelete';
		$("#modal-delete .modal-body").html('确定删除?');
		$("#modal-delete .modal-footer input[name='hidden-url']").val(_url);
		$("#modal-delete .modal-footer input[name='hidden-id']").val(_id);
		jQuery("#modal-delete").modal("show", {backdrop: "fade"});
	}
</script>
</body>
</html>