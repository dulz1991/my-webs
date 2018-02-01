<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="代码笔记二级分类列表" openIndex=2 activeIndex=2>
	
	<!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="分类名称" name="name" />
		<@searchSelect title="一级分类" id="fatherId" datas=codeMenuList key="id" text="name" value="${codeMenuId!''}" defaultValue="--选择一级分类--" />
		<@searchSelect title="状态" id="status" datas={"0":"未开始","1":"编辑中","2":"已完成"} defaultValue="--请选择状态--"  />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="/backend/codeSubMenu/edit" target="_blank"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="menuName" my-attrs='{textFun:"viewCodeList",args:"id",clazz:"table-a"}'>分类名称</th>
			<th field="fatherName">父级分类</th>
			<th field="statusStr">状态</th>
			<th field="button" field-role="2" 
				btn_list='[
                {fnName:"edit", args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"},
                {fnName:"toDelete", args:"id",name:"删除",icon:"fa fa-trash-o",cls:"btn btn-danger btn-xs"}
                ]'
			></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
	

<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/codeSubMenu/getList',
		parm:{
				pageNo : 1,
				pageSize : 10,
				fatherId:'${codeMenuId!''}'
			},
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});
function toDelete(id){
	$.common.deleteById(id,'/backend/codeSubMenu/doDelete');
}
function edit(id){
	window.open('/backend/codeSubMenu/edit?id='+id);
}
function viewCodeList(id){
	window.open('/backend/code/list?fatherId='+id);
}
</script>

</@base> 