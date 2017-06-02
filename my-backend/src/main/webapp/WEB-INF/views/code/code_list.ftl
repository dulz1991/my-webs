<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="代码笔记列表" openIndex=2 activeIndex=2>
	
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
									<input type="text" class="form-control input" name="item" value="">
								</div>
								<div class="col-sm-3">
									一级分类
									<@select id="codeMenuId" class="form-control select" datas=codeMenuList key="id" text="name" value="" onchange="refreshFaterId()" defaultValue="--选择一级分类--" />
								</div>
								<div class="col-sm-3">
									二级分类
									<select id="fatherId" name="fatherId" class="form-control select" onchange="refreshCodeId()">
										<option value="-1">--选择二级分类--</option>
									</select>
								</div>
								<div class="col-sm-3" >
									父级菜单
									<select id="codeId" name="codeId" class="form-control select">
										<option value="-1">--选择父级菜单--</option>
									</select> 
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2">
									<br>
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
							<a href="/backend/code/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index">编号</th>
									<th field="item" url="/backend/code/viewDetail?id=" parm="id">标题</th>
									<th field="codeSubType">类别</th>
									<th field="orderBy">排序</th>
									<th field="createTimeStr">创建时间</th>
									<th field="updateTimeStr">更新时间</th>
									<th field="op" field-role="2,0" width="110"></th>
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
		url_load : '/backend/code/getList',
		url_edit : '/backend/code/edit',
		url_remove : '/backend/code/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

function refreshFaterId(){
	jQuery.commonUtil.refreshSelect('#codeMenuId','#fatherId','/backend/codeSubMenu/getCodeSubMenuListByFatherId');
}

function refreshCodeId(){
	jQuery.commonUtil.refreshSelect('#fatherId','#codeId','/backend/code/getCodeListByFatherId');
}
</script>

</@base> 