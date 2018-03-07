<!-- 列表区 -->
<@dataList>
	<@dataHeader title="登录历史">
		<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
	</@dataHeader>
	<@dataTable tableId="datatable" pageId="pageDiv">
		<th width="60" field="index_no">序号</th>
		<th field="username">用户名</th>
		<th field="loginTimeStr">登录时间</th>
		<th field="logTypeStr">登录形式</th>
		<th field="ROLE_NAME">角色</th>
	</@dataTable>
</@dataList>
<!-- 列表区结束 -->

<script>
	$('#datatable').datatable({
		url_load : '/backend/userLog/getList',
		parm : {
			pageNo : 1,
			pageSize : 10,
			userId : '${user.id}'
		},
		backFn : function(data) {
		}
	}); 
</script>

