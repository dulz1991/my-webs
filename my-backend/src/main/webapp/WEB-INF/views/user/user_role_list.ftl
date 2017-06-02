<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="列表" openIndex=1 activeIndex=1>
	
			
			<!-- 列表区 -->
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">列表</h3>
						<div class="panel-options">
							<a href="/backend/userRole/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index">编号</th>
									<th field="id">权限id</th>
									<th field="roleName">权限名称</th>
									<th field="roleDescription">权限描述</th>
									<th field="op" field-role="2" width="110"></th>
								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
						<div class="pagebar"></div>
					</div>
				</div>
			</div>
			<!-- 列表区结束 -->

<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/userRole/getList',
		url_edit : '/backend/userRole/edit',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
});
</script>

</@base> 