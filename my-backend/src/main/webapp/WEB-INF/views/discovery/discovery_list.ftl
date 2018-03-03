<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="列表 discovery" openIndex=5 activeIndex=0>
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
			<th field="username">用户名</th>
			<th field="commentCount">评论</th>
			<th field="collectionCount">收藏</th>
			<th field="statusStr">状态</th>
			<th field="createTimeStr">创建时间</th>
			<th field="button" field-role="2,0" width="110"
				btn_list='[
                {fnName:"edit", args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"}
                ]'
			></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
			
<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/discovery/getList',
	}); 
});

function edit(id){
	window.open('/backend/discovery/edit?id='+id);
}
</script>

</@base> 