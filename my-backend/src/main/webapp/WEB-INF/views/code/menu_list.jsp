<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h5 class="row-title btn" onclick="javascript:editMenu();">
	<i class="fa fa-plus"></i>新增一级菜单
</h5>
	
<div class="ztree widget-header" id="zTree" ></div>

<script type="text/javascript" src="/js/custom/my.tree.js"></script>
<script type="text/javascript">
var ztree;
$(function(){
	ztree = $('#zTree').mytree({
		data : ${menuTreeNode},
		treeList :true,
		head : ['名称','状态','排序号','级别','操作'],
		fields : ['statusStr','orderBy','menuLevel','buttons'],
		backFn : function(res){
			
		}
	});
})

/**
 * 根据权限展示功能按钮
 * @param treeNode
 * @returns {string}
 */
function formatHandle(treeNode) {
    var htmlStr = '';
    if(treeNode.menuLevel==1){
    	htmlStr += '<div class="icon_div" style="margin-left:5px;"><a style="color:#428bca" onclick="editSubMenu(\''+treeNode.codeMenuId+'\')">增加</a></div>';	
    	htmlStr += '<div class="icon_div" style="margin-left:5px;"><a style="color:red" onclick="editMenu(\''+treeNode.codeMenuId+'\')">修改</a></div>';
    }
    if(treeNode.menuLevel==2){
    	htmlStr += '<div class="icon_div" style="margin-left:5px;"><a style="color:red" onclick="editSubMenu(\''+treeNode.fatherId+'\',\''+treeNode.codeMenuId+'\')">修改</a></div>';	
    }
    return htmlStr;
}

//编辑一级菜单
function editMenu(menuId){
	var parm = {};
	parm.height='300px';
	parm.loadUrl = "/backend/codeMenu/edit";
	if($.common.isNotBlank(menuId)){
		parm.loadUrl = "/backend/codeMenu/edit?id="+menuId;	
	}
	$.pop.dialog(parm, editMenuVerify, function(data){
		$.pop.success("保存成功");
		location.reload();
	});
}
function editMenuVerify(){
	var formData = $.common.getFormJson('#editMenuForm');
	var rst = '';
	$.common.postSync(formData, '/backend/codeMenu/doSave', function(data){
		if(data.errorNo!=0){
			rst = data.errorInfo;
		}
	});
	return rst;
}

//编辑二级菜单
function editSubMenu(fatherId, menuId){
	var loadUrl =  "/backend/codeSubMenu/edit";
	if($.common.isNotBlank(fatherId)){
		loadUrl += "?fatherId="+fatherId;	
	}
	if($.common.isNotBlank(menuId)){
		loadUrl += "&id="+menuId;	
	}
	
	var parm = {};
	parm.height='300px';
	parm.loadUrl = loadUrl;
	$.pop.dialog(parm, editSubMenuVerify, function(data){
		$.pop.success("保存成功");
		location.reload();
	});
}
function editSubMenuVerify(){
	var formData = $.common.getFormJson('#editSubMenuForm');
	var rst = '';
	$.common.postSync(formData, '/backend/codeSubMenu/doSave', function(data){
		if(data.errorNo!=0){
			rst = data.errorInfo;
		}
	});
	return rst;
}

</script>
