<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="discovery收藏列表" openIndex=5 activeIndex=2>
	
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
									discovery标题
									<input type="text" class="form-control input" name="title" value="">
								</div>
								<div class="col-sm-3">
									用户名
									<input type="text" class="form-control input" name="username" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2">
									<button class="btn btn-info btn-icon" onclick="search()">
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
									<th field="title">discovery标题</th>
									<th field="author">作者</th>
									<th field="collectionUser">收藏用户</th>
									<th field="createTimeStr">收藏时间</th>
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
		url_load : '/backend/discovery/getCollectionLlist',
		url_remove : '/backend/collection/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
});
function search(){
	var parm = $.fn.getFormJsonEncode('.form');
	$.fn.doSearch(parm);
}
</script>

</@base> 