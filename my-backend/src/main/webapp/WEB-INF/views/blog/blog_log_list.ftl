<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="博客日志" openIndex=3 activeIndex=2>
	
	<!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="博客标题" name="blogTitle" class="col-sm-4" />
		<@searchInput title="用户名" name="username" class="col-sm-4" />
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
			<th field="blogTitle" my-attrs='{textFun:"viewDetail",args:"id",clazz:"table-a"}'>博客标题</th>
			<th field="username" my-attrs='{textFun:"viewUser",args:"userId",clazz:"table-a"}'>用户名</th>
			<th field="createTime">创建时间</th>
			<th field="remark">备注</th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
			
	<script type="text/javascript">
	$(function(){
		$('#datatable').datatable({
			url_load : '/backend/blogLog/getList'
		}); 
	});
	
	function viewUser(id){
		window.open("/backend/profile/index?userId="+id);
	}
	
	function viewDetail(id){
		window.open('/backend/blogLog/getDetail?id='+id);
	}
	</script>

</@base> 