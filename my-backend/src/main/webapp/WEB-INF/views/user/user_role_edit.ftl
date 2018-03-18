<div class="panel-body">
	<input type="hidden" name="id" value="${entity.id!''}">
	<div class="form-group">
		<label class="col-sm-12" style="text-align:left;font-size:14px;padding-left:0">权限名称</label>
		<div class="col-sm-12 input-group input-group-sm">
			<input type="text" class="form-control" name="roleName" value="${entity.roleName!}">
		</div>
		<br>
		
		<label class="col-sm-12" style="text-align:left;font-size:14px;padding-left:0">权限描述</label>
		<div class="col-sm-12 input-group input-group-sm">
			<input type="text" class="form-control" name="roleDescription" value="${entity.roleDescription!}">
		</div>
	</div>
</div>