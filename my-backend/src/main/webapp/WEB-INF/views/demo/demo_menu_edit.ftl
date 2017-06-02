<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="编辑Demo菜单" openIndex=4 activeIndex=1>
	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">编辑Demo菜单</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal form" id="form" action="javascript:void(0);">
					<input type="hidden" name="id" value="${entity.id!''}">
					<div class="form-group">
						<div class="col-sm-3">
							菜单名称
							<input type="text" class="form-control input" name="name" value="${entity.name!''}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button class="btn btn-info btn-icon" onclick="$.fn.doSaveAndJump('.form','/backend/demoMenu/doSave','/backend/demoMenu/list')">
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
</script>

</@base> 