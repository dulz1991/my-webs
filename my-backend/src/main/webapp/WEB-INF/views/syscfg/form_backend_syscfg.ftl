<div class="panel panel-default" style="width:760px;">
	<div class="panel-heading">
		<div class="panel-title">后台管理配置</div>
	</div>
	
	<div class="panel-body">
		<div class="form-group" >
			<label class="control-label">树形code列表默认选中的二级菜单项</label>
			<div class="input-group">
				<input type="text" class="form-control no-right-border form-focus-purple" name="defaultCodeSubMenuId" value="${defaultCodeSubMenuId!''}">
				<span class="input-group-btn">
					<button class="btn btn-purple" onclick="saveCodeSubMenuId()" type="button">确定</button>
				</span>
			</div>
		</div>
	</div>
	
</div>

<script>
function saveCodeSubMenuId(){
	if($('input[name="defaultCodeSubMenuId"]').val()==''){
		$.common.tip("请输入二级菜单id");
		return false;
	}
	var parm = {};
	parm.key = "defaultCodeSubMenuId";
	parm.value = $('input[name="defaultCodeSubMenuId"]').val();
	doSave(parm);
}
function doSave(parm){
	parm.flag = "3";
	$.common.postRequest(parm, '/backend/sys/doSave', function(data){
		if(data.errorNo==200){
			$.common.tip("修改成功");
		} else {
			$.common.tip(data.errorInfo);
		}
	});
}
</script>