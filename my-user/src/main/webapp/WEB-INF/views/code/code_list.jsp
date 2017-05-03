<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>代码笔记管理</title>
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
								<label class="col-sm-2 control-label">菜单</label>
								<div class="col-sm-3">
									<select class="form-control menuSelect" name="menuId" onchange="loadSubMenuList()">
										<option value="0">-- 选择菜单 --</option>
									</select>
								</div>
								<label class="col-sm-2 control-label">子菜单</label>
								<div class="col-sm-3">
									<select class="form-control subMenuSelect" name="fatherId" onchange="loadNodeMenuList()">
										<option value="0">-- 选择子菜单 --</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">节点菜单</label>
								<div class="col-sm-3">
									<select class="form-control nodeMenuSelect" name="codeId">
										<option value="0">-- 选择节点菜单 --</option>
									</select>
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
						<h3 class="panel-title">代码笔记列表</h3>
						<!-- <div class="panel-options">
							<a href="/auth/code/edit" target="_blank" class="btn btn-white btn-xs btn-icon icon-left"><i class="fa-plus"></i></a>						
						</div>  -->
						<div class="panel-options">
							<a href="/auth/code/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th field="index">编号</th>
									<th field="item" url="http://my.blog/code/guide?codeId=" parm="id">名称</th>
									<th field="createTime">创建时间</th>
									<th field="updateTime">更新时间</th>
									<th field="op" field-role="2,4,5"></th>
								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
					</div>
				</div>
			</div>
			
		</div>
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>
	
<jsp:include page="../dialog/dialog_delete.jsp"></jsp:include>
<script type="text/javascript">
var menuId = "";
var fatherId = "";
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(3).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(3).find('ul li').eq(0).addClass('active');
	
	$('#datatable').datatable({
		url_load : '/auth/code/getList',
		parm : {
				fatherId: 0
			},
		backFn : function(p) {
			// console.log(p);
		}
	});  
});

function search() {
	var elem = $('.form');
	var menuId = elem.find('select[name="menuId"]').val();
	var fatherId = elem.find('select[name="fatherId"]').val();
	if (menuId == 0 || fatherId == 0) {
		return false;
	}
	var codeId =  elem.find('select[name="codeId"]').val();
	$('#datatable').datatable({
		url_load : '/auth/code/getList',
		parm : {
				pageNum : 1,
				pageSize : 1000,
				fatherId: fatherId,
				codeId:codeId
			},
		backFn : function(p) {
			// console.log(p);
		}
	});  
}
function showDeleteDialog(obj) {
	var _id = $(obj).attr("id");
	var _url = '/auth/code/doDelete';
	$("#modal-delete .modal-body").html('确定删除?');
	$("input[name='hidden-url']").val(_url);
	$("input[name='hidden-id']").val(_id);
	jQuery("#modal-delete").modal("show", {backdrop: "fade"});
}
function edit(obj) {
	var url="/auth/code/edit?id=" + $(obj).attr("id");
	window.open(url);
}
function moveUp(obj) {
	var _id = $(obj).attr("id");
	var _url = '/auth/code/doMove';
	var _parm = {
			id: _id,
			type : 'up'
	};
	$.fn.doSave(_parm, _url);
}
function moveDown(obj) {
	var _id = $(obj).attr("id");
	var _url = '/auth/code/doMove';
	var _parm = {
			id: _id,
			type : 'down'
	};
	$.fn.doSave(_parm, _url);
}

</script>
<script src="/mine/js/code.js"></script>
</body>
</html>