<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>编辑代码笔记</title>
	<script src="/ue/editor_config.js"></script>
	<script src="/ue/editor_api.js"></script>
	<link href="/ue/themes/default/ueditor.css" rel="stylesheet">
<style>
</style>
</head>
<body class="page-body">
	<jsp:include page="../include/hidden_header.jsp"></jsp:include>
	
	<div class="page-container">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a><i class="fa-location-arrow"> 编辑代码笔记</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${code.id}" name="id" />
					          		<p>输入标题</p>
					            	<input class="form-control" type="text" name="item" value="${code.item}" placeholder="输入标题">
					          	</div>
					          	<div class="col-sm-6">
					          		<p>选择是否是节点</p>
										<input type="radio" name="isNode" class="cbr" value="node">
										节点
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="isNode" class="cbr" value="notnode">
										非节点
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					        		<p>选择主菜单</p>
					        		<select class="form-control menuSelect" name="menuId" onchange="loadMenus()">
					        			<option value="0">-- 选择主菜单 --</option>
					        		</select>
					    		</div>
					    		<div class="col-sm-3 div-subMenuSelect">
					    			<p>选择子菜单</p>
					        		<select class="form-control subMenuSelect" name="fatherId" onchange="loadNodeMenuList()">
					        			<option value="0">-- 选择子菜单 --</option>
					        		</select>
					    		</div>
					    		<div class="col-sm-6 div-nodeMenuSelect">
					        		<p>选择父节点</p>
					        		<select class="form-control nodeMenuSelect" name="codeId">
					        			<option value="0">-- 选择父节点 --</option>
					        		</select>
					    		</div>
					        </div>
					        <div class="form-group">
					        	
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<b style="display:none;">${code.content}</b>
									<textarea id="myEditor">${code.content}</textarea>
									<script type="text/javascript">
										var editor_a = new baidu.editor.ui.Editor();
										window.onload = render;
										function render() {
									       	editor_a.render('myEditor');
									  		}
									</script>
					        	</div> 	
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<a id="codeSubmit" class="btn btn-info" href="javascript:void(0);">提交</a>
					    		</div>
					        </div>
					     </form>
					</div>
				</div>
			</div>
		
		</div>
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>
	
<script type="text/javascript">
var menuId = "${subMenu.fatherId}";
var fatherId = "${code.fatherId}";
var codeId = "${code.codeId}";
var mod = "node";
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(3).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(3).find('ul li').eq(0).addClass('active');
	
	//默认选择节点模式
	if(codeId!=""){
		$(".cbr-radio").eq(1).addClass("cbr-checked");
		mod="notnode";
	} else {
		$(".cbr-radio").eq(0).addClass("cbr-checked");	
	}
	
	resetMenuMod(mod);
	$('.cbr-radio').click(function(){
		mod = $(this).find('input').val();
		resetMenuMod(mod);
	});
})

function resetMenuMod(mod) {
	if(mod=='node'){
		$('.div-nodeMenuSelect').hide();
	} else {
		$('.div-nodeMenuSelect').show();
	}
};
</script>
<script src="/mine/js/code.js"></script>
</body>
</html>