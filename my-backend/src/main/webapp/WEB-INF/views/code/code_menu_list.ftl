<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="代码笔记一级分类列表" openIndex=2 activeIndex=1>
	
	<!-- 搜索区 -->
    <@searchForm class="form" title="搜索区">
		<@searchInput title="分类名称" name="name" />
		<@searchButton />
	</@searchForm>
	<!-- 搜索区结束 -->
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="javascript:;" onclick="edit('','','',1)"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="name" my-attrs='{textFun:"viewSubMenuList",args:"id",style:"color:rgb(0,155,219);cursor:pointer;text-decoration:underline"}'>分类名称</th>
			<th field="orderBy">分类排序</th>
			<th field="status">状态</th>
			<th field="remark">备注</th>
			<th field="button" field-role="2" 
				btn_list='[
                {fnName:"edit", args:"id,name,orderBy,status",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"},
                {fnName:"$.fn.deleteById", args:"id",name:"删除",icon:"fa fa-trash-o",cls:"btn btn-danger btn-xs"}
                ]'
			></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
	

<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/codeMenu/getList',
		url_delete :'/backend/codeMenu/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

function edit(id,name,orderBy, status){
	var select = '<select name="status" class="swal2-input"><option value="1">使用</option><option value="-1">停用</option></select>';
	if(status=='已停用'){
		select = '<select name="status" class="swal2-input"><option value="1">使用</option><option selected value="-1">停用</option></select>';
	}
	
	swal({
		title: '编辑一级菜单',
		html:'<input id="swal-input1" class="swal2-input" name="menuName" autofocus value="'+name+'">'+
		'<br/>'+
		'<input id="swal-input1" class="swal2-input" name="menuOrderBy" autofocus value="'+orderBy+'">'+select,
		showCancelButton: true,   //显示取消按钮
		confirmButtonColor: '#3085d6', //俩个按钮的颜色
		confirmButtonText: '提交', //俩个按钮的文本
		cancelButtonText: '取消',
		allowOutsideClick: false  
	}).then(function(result) {
		name=$('input[name="menuName"]').val();
		if(!$.common.isNotBlank(name)){
			$.common.tip("请输入菜单名称");
			return;
		}
		orderBy=$('input[name="menuOrderBy"]').val();
		if(!$.common.isNotBlank(orderBy)){
			$.common.tip("请输入排序");
			return;
		}
		var parm = {};
		if($.common.isNotBlank(id)){
			parm.id=id;
		}
		parm.name=name;
		parm.orderBy=orderBy;
		parm.status=$('select[name="status"]').val();
		$.common.postRequest(parm, '/backend/codeMenu/doSave', function(data){
			if(data.errorNo==200){
				$.fn.reload();
			} else {
				$.common.tip(data.errorInfo);
			}
		});
	})
}

function viewSubMenuList(id){
	window.open('/backend/codeSubMenu/list?codeMenuId='+id);
}
</script>

</@base> 