<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="discovery收藏列表" openIndex=5 activeIndex=2>
	<!-- 搜索区 -->
	<@searchForm class="form" title="搜索区">
		<@searchInput title="标题" name="title" />
		<@searchInput title="收藏用户" name="username" />
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
			<th field="title">discovery标题</th>
			<th field="author">作者</th>
			<th field="collectionUser">收藏用户</th>
			<th field="createTimeStr">收藏时间</th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->			

<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/discovery/getCollectionLlist',
	}); 
	
});
</script>

</@base> 