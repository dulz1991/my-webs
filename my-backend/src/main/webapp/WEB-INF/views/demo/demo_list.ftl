<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="Demo列表" openIndex=4 activeIndex=0>
	
    	<!-- 搜索区 -->
			<div class="row">
				<div class="col-sm-12 panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">搜索区</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" id="form" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-3">
									Demo标题
									<input type="text" class="form-control input" name="title" value="">
								</div>
								<div class="col-sm-3">
									Demo菜单
									<@select id="menuId" class="form-control select" datas=demoMenuList key="id" 
									text="name" value="${demoMenuId!''}" defaultValue="--请选择--" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2">
									<button class="btn btn-info btn-icon" onclick="$.fn.autoSearch('.form')">
										<i class="fa-search"></i>
										<span>搜索</span>
									</button>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
			<!-- 搜索区结束 -->
			
			<!-- 列表区 -->
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">列表</h3>
						<div class="panel-options">
							<a href="/backend/demo/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index_no">编号</th>
									<th field="title">标题</th>
									<th field="name">所属分类</th>
									<th field="downloadTimes">下载次数</th>
									<th field="createTimeStr">创建时间</th>
									<th field="updateTimeStr">更新时间</th>
									<th field="button" field-role="2,0" width="110" 
										btn_list='[
				                        {fnName:"edit",args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"}
				                        ]'
									></th>
								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
						<div id="pageDiv"></div>
					</div>
				</div>
			</div>
			<!-- 列表区结束 -->

<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/demo/getList',
		url_edit : '/backend/demo/edit',
		url_remove : '/backend/demo/doDelete',
		parm:{
			pageNo : 1,
			pageSize : 10,
			menuId:'${demoMenuId!''}'
		},
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

function edit(id){
	window.open('/backend/demo/edit?id='+id);
}
</script>

</@base> 