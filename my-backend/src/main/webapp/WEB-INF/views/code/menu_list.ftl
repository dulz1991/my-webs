<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="笔记菜单管理" openIndex=2 activeIndex=4>
	
	<!-- 列表区 -->
	<@dataList>
		<@dataHeader title="${codeMenuName!''}列表区">
			<#if level==2>
				<a href="/backend/code/codeMenuPage" class="upMenu" title="返回父级菜单"><i class=""></i>上级菜单</a>
			</#if>
			<a href="#" onclick="gotoEdit()"><i class="fa-plus"></i></a>
			<a href="#" data-toggle="reload" onclick="$.fn.reload()" id="reloadBtn"><i class="fa-rotate-right"></i></a>
		</@dataHeader>
		<@dataTable tableId="datatable" pageId="pageDiv">
			<th width="60" field="index_no">编号</th>
			
			<!-- 一级菜单 -->
			<#if level==1>
				<th field="name">分类名称</th>
				<th field="status">状态</th>
			</#if>
			
			<!-- 二级菜单 -->
			<#if level==2>
				<th field="menuName" my-attrs='{textFun:"viewCodeList",args:"id",clazz:"table-a"}'>分类名称</th>
				<th field="fatherName">父级分类</th>
				<th field="statusStr">状态</th>
			</#if>
			
			<th field="orderBy">分类排序</th>
			<th field="remark">备注</th>
			<th field="button" field-role="2" 
				btn_list='[
				<#if level==1>
                {fnName:"gotoNext", args:"id",name:"下级菜单",icon:"",cls:"btn btn-success btn-xs"},	
                </#if>
                {fnName:"gotoEdit", args:"id,menuName",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"},
                {fnName:"enableMenu", args:"id",name:"启用",icon:"fa fa-shield",cls:"btn btn-success btn-xs",hidden:"hideEnable"},
                {fnName:"$.fn.deleteById", args:"id",name:"禁用",icon:"fa fa-ban",cls:"btn btn-danger btn-xs",hidden:"hideBan"}
                ]'
			></th>
		</@dataTable>
	</@dataList>
	<!-- 列表区结束 -->
	
<style>
.upMenu{padding:4px 6px;}
.panel .panel-heading > .panel-options a.upMenu:hover{background-color:#2eb4e8;color:#fff }
</style>

<script type="text/javascript">
var level = ${level};
$(function(){
	if(level==1){
		$('#datatable').datatable({
			url_load : '/backend/codeMenu/getList',
			url_delete : '/backend/codeMenu/doDelete'
		}); 	
	} else {
		$('#datatable').datatable({
			url_load : '/backend/codeSubMenu/getList',
			url_delete : '/backend/codeSubMenu/doDelete',
			parm:{
				pageNo : 1,
				pageSize : 10,
				fatherId:'${codeMenuId!''}'
			}
		});
	}
});
//编辑
function gotoEdit(id, menuName){
	var title = "";
	var saveUrl = "";
	var loadDataUrl = "";
	var formElem = "";
	if(level==1){
		//编辑一级菜单
		saveUrl = "/backend/codeMenu/doSave";
		formElem = "#codeMenuForm";
		loadDataUrl	
		if($.common.isBlank(id)){
			title = "新增一级菜单";
			loadDataUrl = "/backend/codeMenu/edit";
		} else {
			title = "编辑"+menuName;
			loadDataUrl = "/backend/codeMenu/edit?id="+id;
		}
	} else {
		//编辑二级菜单
		saveUrl = "/backend/codeSubMenu/doSave";	
		formElem = "#codeMenuSubForm";
		if($.common.isBlank(id)){
			title = "新增二级菜单";
			loadDataUrl = "/backend/codeSubMenu/edit";
		} else {
			title = "编辑"+menuName;
			loadDataUrl = "/backend/codeSubMenu/edit?id="+id;
		}
	}
	editMenu(id, title, saveUrl, loadDataUrl, formElem);
}
//编辑菜单
function editMenu(id, title, saveUrl, loadDataUrl, formElem){
	var html = '<div id="menuHtml"><i class="fa fa-spinner fa-pulse"></i>数据加载中...</div>';
	$.common.dialog(title, html, function(data){
		var formData = $.common.getFormJson(formElem);
		$.common.postRequest(formData, saveUrl, function(data){
			if(data.errorNo==200){
				$.common.tip('保存成功');
				$.fn.reload();
				$('#reloadBtn').trigger('click');
			} else {
				$.common.error(data.errorInfo);
			}
		});
	});
	$('#menuHtml').load(loadDataUrl);
}

//下级下单
function gotoNext(codeMenuId){
	self.location='/backend/code/codeMenuPage?level=2&codeMenuId='+codeMenuId;
}

//启用菜单
function enableMenu(id){
	var parm = {};
	parm.id=id;
	parm.status = 1;
	var url = ""; 
	if(level==1){
		url = "/backend/codeMenu/doSave";
	} else {
		url = "/backend/codeSubMenu/doSave";
	}
	$.common.postRequest(parm, url, function(data){
		if(data.errorNo==200){
			self.location='';
		} else {
			$.common.tip(data.errorInfo);
		}
	})
}

//跳转到code列表页
function viewCodeList(codeSubMenuId){
	window.open('/backend/code/zTreelist?codeSubMenuId='+codeSubMenuId);
}
</script>

</@base> 