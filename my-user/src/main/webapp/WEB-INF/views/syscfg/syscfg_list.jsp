<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>系统配置</title>
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
						<h3 class="panel-title">系统配置列表</h3>
						<!-- <div class="panel-options">
							<a href="/auth/code/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div> -->
					</div>
					<div class="panel-body">
						<table class="table responsive">
							<thead>
								<tr>
									<th>编号</th>
									<th>内容</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>刷新各类文章数目</td>
									<td><button class="btn btn-secondary btn-single" onclick="doFresh()">刷新</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(6).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(6).find('ul li').eq(0).addClass('active');
});
function doFresh(){
	$.ajax({
		url: "/auth/sys/refresh", 
		type: 'get',  
		cache: false,  
		dataType: 'json',
		success: function(data) {
			if(data.errorNo==200){
				$.commonUtil.showTip("刷新成功");
			} else {
				$.commonUtil.showTip(data.errorInfo);
			}
		}
	});
}
</script>
</body>
</html>