<div class="row">
<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">修改基础信息</div>
	</div>
	
	<div class="panel-body">
		<form class="baseinfoForm">
		<div class="form-group">
			<label class="control-label">Phone</label>
			<div class="input-group input-group-sm input-group-minimal">
				<span class="input-group-addon">
					<i class="linecons-mobile"></i>
				</span>
				<input type="text" class="form-control" data-mask="phone" name="phone" value="${user.phone}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label">Email</label>
			<div class="input-group input-group-sm input-group-minimal">
				<span class="input-group-addon">
					<i class="linecons-mail"></i>
				</span>
				<input type="text" class="form-control" data-mask="email" name="email" value="${user.email}">
			</div>
		</div>
		</form>
		
		<div class="form-group">
			<button type="button" class="btn btn-info btn-single" onclick="modifyUser()">修改</button>
		</div>
		
	</div>
	
</div>
</div>

<script>
function modifyUser(){
	var parm = $.common.getFormJson('.baseinfoForm');
	parm.id="${user.id}";
	$.common.postRequest(parm, '/backend/user/doSave', function(data){
		if(data.errorNo==200){
			location.reload();
		} else {
			$.common.tip(data.errorInfo);
		}
	});
}
</script>