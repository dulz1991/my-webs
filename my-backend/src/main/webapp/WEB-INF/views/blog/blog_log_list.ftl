<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="博客日志" openIndex=3 activeIndex=2>
	
	<!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="博客标题" name="blogTitle" class="col-sm-4" />
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
			<th field="blogTitle" my-attrs='{textFun:"viewDetail",args:"id",style:"color:rgb(0,155,219);cursor:pointer;text-decoration:underline"}'>博客标题</th>
			<th field="username">用户名</th>
			<th field="createTime">创建时间</th>
			<th field="remark">备注</th>
			<!--<th field="op" field-role="2,0" width="110"></th>-->
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
			
	<script type="text/javascript">
	$(function(){
		$('#datatable').datatable({
			url_load : '/backend/blogLog/getList',
			url_edit : '/backend/blogLog/edit',
			url_remove : '/backend/blogLog/doDelete',
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	});
	
	function edit(id){
		window.open('/backend/blogLog/edit?id='+id);
	}
	
	function viewDetail(id){
		window.open('/backend/blogLog/getDetail?id='+id);
	}
	</script>

</@base> 