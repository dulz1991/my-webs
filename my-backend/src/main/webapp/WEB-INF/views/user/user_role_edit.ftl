<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="编辑权限" openIndex=1 activeIndex=1>
	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">编辑权限</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal form" id="form" action="javascript:void(0);">
					<input type="hidden" name="id" value="${entity.id!''}">
					<div class="form-group">
						<div class="col-sm-3">
							权限名称
							<input type="text" class="form-control input" name="roleName" value="${entity.roleName!''}">
						</div>
						<div class="col-sm-3">
							权限描述
							<input type="text" class="form-control input" name="roleDescription" value="${entity.roleDescription!''}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button class="btn btn-info btn-icon" onclick="doSave()">
								<span>提交</span>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 编辑区结束 -->

<script type="text/javascript">
$(function(){
	
});
function doSave(){
	var parm = $.common.getFormJson('.form');
	$.common.postRequest(parm, '/backend/userRole/doSave', function(data){
		if(data.errorNo==200){
			self.location='/backend/userRole/list';
		} else {
			$.common.alert(data.errorInfo);
		}
	});
}
</script>

</@base> 