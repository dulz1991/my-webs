<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="Demo菜单管理" openIndex=4 activeIndex=1>
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="列表区">
			<a href="javascript:;" onclick="edit('','')"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			<th field="name" my-attrs='{textFun:"viewList",args:"id",clazz:"table-a"}'>菜单名称</th>
			<th field="button"
				btn_list='[
                {fnName:"edit",args:"id,name",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"},
                {fnName:"toDelete", args:"id",name:"删除",icon:"fa fa-trash-o",cls:"btn btn-danger btn-xs"}
                ]'
			></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
	

<script type="text/javascript">
$(function(){
	$('#datatable').datatable({
		url_load : '/backend/demoMenu/getList',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

	function edit(id,name){
		swal({
  			title: '编辑Demo菜单',
  			html:'<input id="swal-input1" class="swal2-input" name="menuName" autofocus value="'+name+'">',
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
			var parm = {};
			if($.common.isNotBlank(id)){
				parm.id=id;
			}
			parm.name=name;
			$.common.postRequest(parm, '/backend/demoMenu/doSave', function(data){
				if(data.errorNo==200){
					$.common.reloadTable();
				} else {
					$.common.tip(data.errorInfo);
				}
			});
		})
	}

function toDelete(id){
	$.common.deleteById(id,'/backend/demoMenu/doDelete');
}
function viewList(id){
	window.open('/backend/demo/list?demoMenuId='+id);
}
</script>

</@base> 