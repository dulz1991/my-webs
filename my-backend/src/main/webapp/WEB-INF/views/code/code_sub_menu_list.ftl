<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="代码笔记二级分类列表" openIndex=2 activeIndex=2>
	
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
									分类名称
									<input type="text" class="form-control input" name="name" value="">
								</div>
								<div class="col-sm-3">
									一级分类
									<@select id="fatherId" class="form-control select" datas=codeMenuList 
									key="id" text="name" value="${codeMenuId!''}" defaultValue="--选择一级分类--" />
								</div>
								<div class="col-sm-3">
									状态
									<@select id="status" class="form-control select" datas={"0":"未开始","1":"编辑中","2":"已完成"} defaultValue="--请选择状态--" /> 
								</div>
								<div class="col-sm-2">
									<br />
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
							<a href="/backend/codeSubMenu/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index_no">编号</th>
									<th field="menuName" my-attrs='{textFun:"viewCodeList",args:"id",style:"color:rgb(0,155,219);cursor:pointer;text-decoration:underline"}'>分类名称</th>
									<th field="fatherName">父级分类</th>
									<th field="statusStr">状态</th>
									<th field="button" field-role="2" width="110"
										btn_list='[
				                        {fnName:"edit", args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"}
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
		url_load : '/backend/codeSubMenu/getList',
		url_edit : '/backend/codeSubMenu/edit',
		url_remove : '/backend/codeSubMenu/doDelete',
		parm:{
				pageNo : 1,
				pageSize : 10,
				fatherId:'${codeMenuId!''}'
			},
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});
function edit(id){
	window.open('/backend/codeSubMenu/edit?id='+id);
}
function viewCodeList(id){
	window.open('/backend/code/list?fatherId='+id);
}
</script>

</@base> 