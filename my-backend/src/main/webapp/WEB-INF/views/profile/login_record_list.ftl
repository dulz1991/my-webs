<!-- 列表区 -->
<@dataList>
	<@dataHeader title="登录历史">
		<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
	</@dataHeader>
	<@dataTable tableId="datatable" pageId="pageDiv">
		<th width="60" field="index_no">序号</th>
		<th field="username">用户名</th>
		<th field="loginTimeStr">登录时间</th>
		<th field="loginType">登录形式</th>
		<th field="role">角色</th>
	</@dataTable>
</@dataList>
<!-- 列表区结束 -->