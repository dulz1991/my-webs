<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="编辑代码笔记一级分类" openIndex=2 activeIndex=1>
	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">编辑</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal form" id="form" action="javascript:void(0);">
					<input type="hidden" name="id" value="${entity.id!}">
					<div class="form-group">
						<div class="col-sm-3">
							分类名称
							<input type="text" class="form-control input" name="name" value="${entity.name!}">
						</div>
						<div class="col-sm-3">
							排序
							<input type="text" class="form-control input" name="orderBy" value="${entity.orderBy!}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button class="btn btn-info btn-icon" onclick="$.fn.doSaveAndJump('.form','/backend/codeMenu/doSave','/backend/codeMenu/list')">
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