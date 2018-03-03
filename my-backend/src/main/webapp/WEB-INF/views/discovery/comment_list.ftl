<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="discovery评论列表" openIndex=5 activeIndex=1>
	<!-- 搜索区 -->
	<@searchForm class="form" title="搜索区">
		<@searchInput title="标题" name="title" />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->

	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="title">标题</th>
			<th field="fromName">作者</th>
			<th field="toName">评论用户</th>
			<th field="content">评论内容</th>
			<th field="createTimeStr">评论时间</th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
			
<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/discovery/getCommentLlist',
	}); 
	
});
</script>

</@base> 