<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="代码笔记列表" openIndex=2 activeIndex=3>
	
	<div class="col-sm-2">
		<div id="zTree" class="ztree"></div>
	</div>
	
	<div class="col-sm-10">
		<!-- 列表区 -->
		<@dataList>
			<@dataHeader title="列表区">
				<a href="/backend/code/edit" target="_blank"><i class="fa-plus"></i></a>
				<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
			</@dataHeader>
			<@dataTable tableId="datatable" pageId="pageDiv">
				<th width="60" field="index_no">编号</th>
				<th field="item" my-attrs='{textFun:"viewDetail",args:"id",clazz:"table-a"}'>标题</th>
				<th field="codeSubType">类别</th>
				<th field="orderBy">排序</th>
				<th field="createTimeStr">创建时间</th>
				<th field="updateTimeStr">更新时间</th>
				<th field="button" field-role="2" width="110"
					btn_list='[
	                {fnName:"edit", args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"}
	                ]'
				></th>
			</@dataTable>
		</@dataList>
		<!-- 列表区结束 -->
	</div>
	
	
				
<script type="text/javascript">
var setting = {
	view: {
		selectedMulti: false
	},
	data: {
		title:"name",
		simpleData: {
			enable: true
		}
	},
	callback: {
	 	onClick : zTreeOnClick
	}
};
var zTree = $.fn.zTree.init($("#zTree"), setting, ${codeMenuList});
var node = zTree.getNodeByParam("id", "${defaultNodeId}");
zTree.selectNode(node);

$(function(){
	$('#datatable').datatable({
		url_load : '/backend/code/getList',
		parm:{
			pageNo : 1,
			pageSize : 10,
			codeSubMenuId:(parseInt('${defaultNodeId}')-100)
		}
	}); 
})

function zTreeOnClick(event, treeId, treeNode){
	$('#datatable').datatable({
		url_load : '/backend/code/getList',
		parm:{
			pageNo : 1,
			pageSize : 10,
			codeSubMenuId:(parseInt(treeNode.id)-100)
		}
	});
}

function edit(id){
	window.open('/backend/code/edit?id='+id);
}
function viewDetail(id){
	window.open('/backend/code/viewDetail?id='+id);
}

</script>

</@base> 