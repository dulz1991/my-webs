<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="用户管理" openIndex=1 activeIndex=0>
	
	<#-- @base 中间的内容将嵌套至 base 宏中的#nested处 -->
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
									用户名
									<input type="text" class="form-control input" name="username" value="">
								</div>
								<div class="col-sm-3">
									手机号
									<input type="text" class="form-control input" name="phone" value="">
								</div>
								<div class="col-sm-3">
									用户状态
									<select class="form-control select" name="status" id="userStatus">
										<option value="">-- 选择用户状态 --</option>
										<option value="0">禁用</option>
										<option value="1">正常</option>
									</select>
								</div>
								<div class="col-sm-2">
									<br>
									<button class="btn btn-info btn-icon" onclick="$.fn.autoSearch('.form')">
										<i class="fa-search"></i>
										<span>搜索</span>
									</button>
								</div>
							</div>
							<div class="form-group">
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
									<th width="60" field="index_no">序号</th>
									<th field="username">用户名</th>
									<th field="phone">手机号</th>
									<th field="email">邮箱</th>
									<th field="roleName">用户权限</th>
									<th field="userStatusStr">用户状态</th>
									<th field="createTimeStr">创建时间</th>
									<th field="updateTimeStr">更新时间</th>
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
		url_load : '/backend/user/getList',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
});
</script>

</@base> 