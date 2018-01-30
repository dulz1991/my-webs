<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="列表" openIndex=3 activeIndex=1>
	
	<!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="分类名称" name="name" />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="/backend/blogMenu/edit" target="_blank"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="name" my-attrs='{textFun:"viewDetail",args:"id",style:"color:rgb(0,155,219);cursor:pointer;text-decoration:underline"}'>分类名称</th>
			<th field="button" field-role="2" width="110"
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
			url_load : '/backend/blogMenu/getList',
			url_edit : '/backend/blogMenu/edit',
			url_remove : '/backend/blogMenu/doDelete',
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	});
	
	function edit(id){
		window.open('/backend/blogMenu/edit?id='+id);
	}
	
	function viewDetail(id){
		window.open('/backend/blog/list?blogMenuId='+id);
	}
	</script>

</@base> 