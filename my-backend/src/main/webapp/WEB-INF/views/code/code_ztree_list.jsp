<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	
	<div class="col-sm-2 widget-header">
		<div id="zTree" class="ztree"></div>
	</div>
	
	<div class="col-sm-10 widget-header">
		<div class="panel-body" style="margin-top:-15px;">
			<div id="codezTree" class="ztree"></div>			
		</div>
	</div>
	
<script type="text/javascript" src="/js/custom/my.tree.js"></script>
<script type="text/javascript">
var ztreeLeft,ztreeRight;
var codeFatherId = ${defaultNodeId};

$(function(){
	//菜单zTree
	ztreeLeft = $('#zTree').mytree({
		data : ${codeMenuList},
		showIcon: false,
		onClick : zTreeOnClick,
		removeHoverDom: removeHoverDom,
		addHoverDom: addHoverDom,
		backFn : function(res){
		}
	});
	var node = ztreeLeft.treeObj.getNodeByParam("id", codeFatherId);
	ztreeLeft.treeObj.selectNode(node);
	
	$.common.sidebarSmall();
	
	//右侧code树形列表
	loadData(codeFatherId);
})

//左侧点击菜单
function zTreeOnClick(event, treeId, treeNode){
	if(treeNode.menuLevel==2){
		codeFatherId = treeNode.id;
		loadData(treeNode.id);
	}
}

//加载右侧数据
function loadData(codeSubMenuId){
	var parm = {faltherId:codeSubMenuId};
	$.common.get(parm, '/backend/code/zTreeCodelist',function(data){
		if(data.errorNo==0){
			$('.panel-heading h3').text("code树形列表("+data.count+")");
		
			//右侧内容zTree
			ztreeRight = $('#codezTree').mytree({
				data : eval('(' + data.codeList + ')'),
				treeeList: true,
				head : ['名称','排序号','创建时间','最近更新','操作'],
				fields : ['itemOrder','createTime','updateTime','buttons'],
				backFn : function(res){
				}
			});
		} else {
			$.pop.error(data.errorInfo);
		}
	})
}

//编辑code
function edit(id){
	gotoUrl('/backend/code/edit?id='+id);
}
//查看code
function viewDetail(id){
	gotoUrl('/backend/code/viewDetail?id='+id);
}
/**
 * 根据权限展示功能按钮
 * @param treeNode
 * @returns {string}
 */
function formatHandle(treeNode) {
    var htmlStr = '';
    htmlStr += '<div class="icon_div"><a target="_blank" title="查看" href="javascript:viewDetail(\''+treeNode.id+'\')">查看</a></div>';
    htmlStr += '<div class="icon_div"><a target="_blank" title="编辑" href="javascript:edit(\''+treeNode.id+'\')">编辑</a></div> ';
    return htmlStr;
}

//重新加载
function reloadCodeZtree(){
	loadData(codeFatherId);
}

function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	//渲染添加节点按钮
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	
	if(treeNode.menuLevel==2){
		var addStr = "<span class='button add' style='margin:7px 0 0 5px;background:url(/img/add.png) 0 0 no-repeat;' id='addBtn_" + treeNode.tId
		+ "' title='添加code菜单' onfocus='this.blur();'></span>";	
		sObj.after(addStr);
	}
	
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		gotoUrl('/backend/code/edit?codeFatherId='+treeNode.codeMenuId);
		/* if(treeNode.menuLevel==1){
			self.location="/backend/code/codeMenuPage";
		} else if(treeNode.menuLevel==2){
			self.location="/backend/code/codeMenuPage?level=2&codeMenuId="+treeNode.codeMenuId;
		} */
		return;
	});
};

//移除按钮显示
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};
</script>
