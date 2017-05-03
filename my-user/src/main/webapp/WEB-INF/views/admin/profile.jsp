<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>User Profile</title>
	
</head>
<body class="page-body">
	<jsp:include page="../include/hidden_header.jsp"></jsp:include>
	
	<div class="page-container">
		<jsp:include page="../include/left.jsp"></jsp:include>
		
		<div class="main-content">
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">管理员信息</h1>
					<p class="description">编辑管理员信息</p>
				</div>
			</div>	
			<section class="profile-env">
				<div class="row">
					
					<div class="col-sm-3">
						<!-- User Info Sidebar -->
						<div class="user-info-sidebar">
							<a href="#" class="user-img">
								<img src="http://127.0.0.1:8095/upload/img/user/user.png" alt="user-img" class="img-cirlce img-responsive img-thumbnail" />
							</a>
							<a href="#" class="user-name">
								${user.username}<span class="user-status is-online"></span>
								<button class="btn btn-blue btn-xs basic-panel-btn"><i class="fa-edit"></i></button>
							</a>
							<span class="user-title">
								权限: <strong>Admin</strong>
							</span>
							<hr />
							<ul class="list-unstyled user-info-list">
								<li>
									<i class="fa-phone-square fa-fw"></i>${user.phone}
								</li>
								<li>
									<i class="fa fa-envelope-o fa-fw"></i>${user.email} 
								</li>
							</ul>	
							<button type="button" class="btn btn-success btn-block text-left pwd-panel-btn">
								修改密码<i class="fa fa-key fa-fw pull-right"></i>
							</button>
						</div>
					</div>
					
					<div class="col-sm-9">
						<div class="panel panel-default basic-panel" style="display:none">
							<div class="panel-heading">
								<h3 class="panel-title">基本信息</h3>
								<div class="panel-options">
									<a href="#" data-toggle="panel">
										<span class="collapse-icon">–</span>
										<span class="expand-icon">+</span>
									</a>
									<a href="#" data-toggle="remove">×</a>
								</div>
							</div>
							<div class="panel-body">
								<form role="form" id="basic-form" class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-1">Username</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="username" value="${user.username}" id="field-1">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-2">Phone</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="phone" value="${user.phone}" id="field-2">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-3">Email</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="email" value="${user.email}" id="field-3">
										</div>
									</div>
									<div class="form-group-separator"></div>
									<button class="btn btn-info btn-small btn-icon pull-right basic-submit"><span>提交</span></button>
								</form>
							</div>
						</div>
						<div class="panel panel-default pwd-panel" style="display:none">
							<div class="panel-heading">
								<h3 class="panel-title">修改密码</h3>
								<div class="panel-options">
									<a href="#" data-toggle="panel">
										<span class="collapse-icon">–</span>
										<span class="expand-icon">+</span>
									</a>
									<a href="#" data-toggle="remove">×</a>
								</div>
							</div>
							<div class="panel-body">
								<form role="form" id="pwd-form" class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-4">旧密码</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="oldpwd" id="field-4">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-5">新密码</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="newpwd" id="field-5">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-6">确认密码</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="cfmpwd" id="field-6">
										</div>
									</div>
									<div class="form-group-separator"></div>
									<button class="btn btn-info btn-small btn-icon pull-right pwd-submit"><span>提交</span></button>
								</form>
							</div>
						</div>
					</div>
				
				</div>
			</section>		
		
		</div>
		
		<jsp:include page="../include/chat.jsp"></jsp:include>
	</div>
	
	
	<div class="page-loading-overlay">
		<div class="loader-2"></div>
	</div>
	
<script type="text/javascript">
$(function(){
	$('.basic-panel-btn').click(function(){
		$('.panel-default').hide();
		$('.panel-default').eq(0).show();
	});
	$('.pwd-panel-btn').click(function(){
		$('.panel-default').hide();
		$('.panel-default').eq(1).show();
	});
});
</script>

</body>
</html>