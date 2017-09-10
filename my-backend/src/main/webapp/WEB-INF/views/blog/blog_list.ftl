<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="博客列表" openIndex=3 activeIndex=0>
	
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
									标题
									<input type="text" class="form-control input" name="title" value="">
								</div>
								<div class="col-sm-3">
									分类
									<@select id="menuId" class="form-control select" datas=menuList key="id" 
									text="name" value="${blogMenuId!''}" defaultValue="--选择分类--" />
								</div>
								<div class="col-sm-3">
									用户
									<input type="text" class="form-control input" name="username" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2">
									<button class="btn btn-info btn-icon" onclick="$.fn.doAutoSearch()">
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
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index">编号</th>
									<th field="title" url="/backend/blog/getDetail?id=" parm="id">标题</th>
									<th field="username">用户</th>
									<th field="menuName">分类</th>
									<th field="click">点击</th>
									<th field="createTime">创建时间</th>
									<th field="updateTime">更新时间</th>
									<th field="op" field-role="0" width="110"></th>
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
		url_load : '/backend/blog/getList',
		url_edit : '/backend/blog/edit',
		url_remove : '/backend/blog/doDelete',
		parm:{
			pageNo : 1,
			pageSize : 10,
			menuId:'${blogMenuId!''}'
		},
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
});
</script>

</@base> 