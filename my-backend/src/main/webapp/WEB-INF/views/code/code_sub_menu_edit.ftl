<div class="panel-body">
	<form class="form-horizontal form" id="codeMenuSubForm" action="javascript:void(0);">
		<input type="hidden" name="id" value="${entity.id!}">
		<div class="form-group">
			<label class="col-sm-12" style="text-align:left;font-size:14px;padding-left:0">菜单名称</label>
			<div class="col-sm-12 input-group input-group-sm">
				<input type="text" class="form-control" name="name" value="${entity.name!''}">
			</div>
			<br>	
			
			<label class="col-sm-12" style="text-align:left;font-size:14px;padding-left:0">一级菜单</label>
			<div class="col-sm-12 input-group input-group-sm">
				${codeMenuSelectHtml}
			</div>
			<br>
			
			<label class="col-sm-12" style="text-align:left;font-size:14px;padding-left:0">状态</label>
			<div class="col-sm-12 input-group input-group-sm">
				${statusSelectHtml}
			</div>
			<br>
		
			<label class="col-sm-12" style="text-align:left;font-size:14px;padding-left:0">排序号</label>
			<div class="col-sm-12 input-group input-group-sm">
				<input type="text" class="form-control" name="orderBy" value="${entity.orderBy!''}">
			</div>
			<br>
			
			<label class="col-sm-12" style="text-align:left;font-size:14px;padding-left:0">备注</label>
			<div class="col-sm-12 input-group input-group-sm">
				<input type="text" class="form-control" name="remark" value="${entity.remark!''}">
			</div>
			<br>
		</div>
	</form>
</div>
