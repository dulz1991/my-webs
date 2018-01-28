<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="列表" openIndex=3 activeIndex=1>
	
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
							</div>
							<div class="form-group">
								<div class="col-sm-12">
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
							<a href="/backend/blogMenu/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index_no">编号</th>
									<th field="name" my-attrs='{textFun:"viewDetail",args:"id",style:"color:rgb(0,155,219);cursor:pointer;text-decoration:underline"}'>分类名称</th>
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
		url_load : '/backend/blogMenu/getList',
		url_edit : '/backend/blogMenu/edit',
		url_remove : '/backend/blogMenu/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
});

function edit(id){
	window.open('/backend/blogMenu/edit?id='+id);
}

function viewDetail(id){
	window.open('/backend/blog/list?blogMenuId='+id);
}
</script>

</@base> 