<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="编辑 discovery" openIndex=5 activeIndex=0>
	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">编辑</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal form" id="form" action="javascript:void(0);">
					<input type="hidden" name="id" value="${entity.id}">
					<div class="form-group">
						<div class="col-sm-3">
							状态
							<@select id="status" class="form-control select" datas={"1":"正常","0":"禁用"} value="${entity.status!''}" />
						</div>
						<br><br><br>
						<div class="col-sm-12">
							<img src="${entity.imgPath!''}">
						</div>
						<br><br><br>
						<div class="col-sm-12">
							${entity.content}
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button class="btn btn-info btn-icon" onclick="$.fn.doSaveAndJump('.form','/backend/discovery/doSave','/backend/discovery/list')">
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