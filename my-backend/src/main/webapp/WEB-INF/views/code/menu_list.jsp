<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h5 class="row-title btn" onclick="javascript:editMenu();">
	<i class="fa fa-plus"></i>新增一级菜单
</h5>
	
<div class="ztree" id="zTree" ></div>

<script type="text/javascript">
var setting = {
	view: {
		selectedMulti: false,
		addDiyDom: addDiyDom
	},
	data: {
		title:"name",
		simpleData: {
			enable: true
		}
	}
};

var zTree = $.fn.zTree.init($("#zTree"), setting, ${menuTreeNode});
//添加表头
var li_head = ' <li class="head"><a><div class="diy" style="width:55%">名称</div><div class="diy" style="width:10%">状态</div><div class="diy" style="width:10%">排序</div><div class="diy" style="width:10%">级别</div><div class="diy" style="width:15%">操作</div></a></li>';
var rows = $("#zTree").find('li');
if (rows.length > 0) {
    rows.eq(0).before(li_head)
} else {
    $("#zTree").append(li_head);
    $("#zTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
}

/**
 * 自定义DOM节点
 */
function addDiyDom(treeId, treeNode) {
    var spaceWidth = 15;
    var liObj = $("#" + treeNode.tId);
    var aObj = $("#" + treeNode.tId + "_a");
    var switchObj = $("#" + treeNode.tId + "_switch");
    
    var icoObj = $("#" + treeNode.tId + "_ico");
    var spanObj = $("#" + treeNode.tId + "_span");
    spanObj.prepend(icoObj);
    
    aObj.attr('title', treeNode.name);
    /* aObj.append(icoObj); */
    /* aObj.append(spanObj); */
    aObj.append('<div class="diy swich" style="width:55%"></div>');
    
    
    var div = $(liObj).find('div').eq(0);
    switchObj.remove();
    /* spanObj.remove(); */
    /* icoObj.remove(); */
    
    /* div.append(icoObj); */
    div.append(switchObj);
    div.append(spanObj);
    var spaceStr = "<span style='height:1px;display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
    switchObj.before(spaceStr);
    var editStr = '';
    editStr += '<div class="diy" style="width:10%">' + treeNode.statusStr + '</div>';
    editStr += '<div class="diy" style="width:10%">' + treeNode.orderBy + '</div>';
    editStr += '<div class="diy" style="width:10%">' + (treeNode.menuLevel) + '</div>';
    editStr += '<div class="diy op_diy" style="width:15%">' + formatHandle(treeNode) + '</div>';
    aObj.append(editStr);
}

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
<style>
    /*按钮*/
    .icon_div {
        display: inline-block;
        height: 25px;
        width: 35px;
    }
    .icon_div a {
        display: inline-block;
        width: 27px;
        height: 20px;
        cursor: pointer;
    }

    /*ztree表格*/
    .ztree {
        padding: 0;
        border: 2px solid #CDD6D5;
    }

    .ztree li a {
        vertical-align: middle;
        height: 30px;
    }

    .ztree li > a {
        width: 100%;
    }

    .ztree li > a,
    .ztree li a.curSelectedNode {
        padding-top: 0px;
        /* background: none; */
        height: auto;
        border: none;
        cursor: default;
        opacity: 1;
    }

    .ztree li ul {
        padding-left: 0px
    }

    .ztree div.diy span {
        line-height: 30px;
        vertical-align: middle;
    }

    .ztree div.diy {
        /* height: 100%; */
        line-height: 30px;
        border-top: 1px dotted #ccc;
        border-left: 1px solid #eeeeee;
        text-align: center;
        display: inline-block;
        box-sizing: border-box;
        color: #6c6c6c;
        font-family: "SimSun";
        font-size: 12px;
        /* overflow: hidden; */
    }
	.ztree div.op_diy {
		padding-left:30px;
		text-align: left;
	}
	.ztree div.op_diy div img {
		margin-top: 5px;
	} 
    .ztree div.diy:first-child {
        text-align: left;
        text-indent: 10px;
        border-left: none;
    }

    .ztree .head {
        background: #5787EB;
    }

    .ztree .head div.diy {
        border-top: none;
        border-right: 1px solid #CDD2D4;
        color: #fff;
        font-family: "Microsoft YaHei";
        font-size: 14px;
    }
    /*end--ztree表格*/
    .ztree li span.button.ico_docu{margin-top:5px;}
    .ztree li span.button.ico_open{margin-top:5px;}
</style>
