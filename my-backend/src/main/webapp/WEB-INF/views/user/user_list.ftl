<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="用户管理" openIndex=1 activeIndex=0>
	<!-- 搜索区 -->
	<@searchForm class="form" title="搜索区">
		<@searchInput title="用户名" name="username" />
		<@searchInput title="手机号" name="phone" />
		<@searchSelect id="status" datas={"0":"禁用","1":"正常"} defaultValue="--全部--" title="用户状态" />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
		
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">序号</th>
			<th field="username">用户名</th>
			<th field="phone">手机号</th>
			<th field="email">邮箱</th>
			<th field="roleName">用户权限</th>
			<th field="userStatusStr">用户状态</th>
			<th field="createTimeStr">创建时间</th>
			<th field="updateTimeStr">更新时间</th>
		</@dataTable>
	</@dataList>
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