<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="代码笔记列表" openIndex=2 activeIndex=3>
	
	<div class="col-sm-3">
		<div id="zTree" class="ztree"></div>
	</div>
	
	<div class="col-sm-9">
		<!-- 列表区 -->
		<@dataList>
			<@dataHeader title="code树形列表">
				<a href="#" onclick="gotoEdit()"><i class="fa-plus"></i></a>
				<a href="#" data-toggle="reload" onclick="reloadCodeZtree()"><i class="fa-rotate-right"></i></a>
			</@dataHeader>
			<div class="panel-body">
				<div id="codezTree" class="ztree"></div>			
			</div>
		</@dataList>
		<!-- 列表区结束 -->
	</div>
	
	
				
<script type="text/javascript">
function gotoEdit(){
	window.open('/backend/code/edit?codeFatherId='+codeFatherId);
}

var setting = {
	view: {
		removeHoverDom: removeHoverDom,
		addHoverDom: addHoverDom,
		selectedMulti: false
	},
	data: {
		title:"name",
		simpleData: {
			enable: true
		}
	},
	<#--
	edit: {
		enable: true,
		editNameSelectAll: false,
		showRemoveBtn: false
	},
	-->
	callback: {
	 	onClick : zTreeOnClick
	}
};

var codeFatherId = ${defaultNodeId};
var codezTree;
var codeSetting = {
	view: {
		selectedMulti: false,
		addDiyDom: addDiyDom
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

$(function(){
	//菜单zTree
	var zTree = $.fn.zTree.init($("#zTree"), setting, ${codeMenuList});
	var node = zTree.getNodeByParam("id", codeFatherId);
	zTree.selectNode(node);
	
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

//加载数据
function loadData(codeSubMenuId){
	var parm = {faltherId:codeSubMenuId};
	$.common.getRequest(parm, '/backend/code/zTreeCodelist',function(data){
		if(data.errorNo!=0){
			$('.panel-heading h3').text("code树形列表("+data.count+")");
		
			codezTree = $.fn.zTree.init($("#codezTree"), codeSetting,  eval('(' + data.codeList + ')'));
			var li_head ='<li class="head"><div class="diy name_diy" style="width:30%;text-align:center;">名称</div>'
				+'<div class="diy" style="width:10%">级别</div>'
				+'<div class="diy" style="width:10%">排序</div>'
				+'<div class="diy" style="width:20%">创建时间</div>' 
				+'<div class="diy" style="width:20%">最近更新</div>'
				+'<div class="diy op_diy" style="width:10%";text-align:center">操作</div></li>';
			var rows = $("#codezTree").find('li');
			if (rows.length > 0) {
			    rows.eq(0).before(li_head)
			} else {
			    $("#codezTree").append(li_head);
			    $("#codezTree").append('<li ><div style="text-align: center;line-height: 30px;" >无符合条件数据</div></li>')
			}
		} else {
			$.common.tip(data.errorInfo);
		}
	})
}

//编辑code
function edit(id){
	window.open('/backend/code/edit?id='+id);
}
//查看code
function viewDetail(id){
	window.open('/backend/code/viewDetail?id='+id);
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
    spanObj.attr('onclick', 'javascript:window.open("/backend/code/viewDetail?id='+treeNode.id+'");');
    spanObj.attr('style', 'cursor:pointer;color:#5787EB');
    spanObj.prepend(icoObj);
    
    aObj.attr('title', treeNode.name);
    /* aObj.append(icoObj); */
    /* aObj.append(spanObj); */
    aObj.append('<div class="diy swich name_diy" style="width:30%"></div>');
    
    
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
    editStr += '<div class="diy" style="width:10%">' + (treeNode.codeLevel) + '</div>';
    editStr += '<div class="diy" style="width:10%">' + (treeNode.itemOrder) + '</div>';
    editStr += '<div class="diy" style="width:20%">' + (treeNode.createTime) + '</div>';
    editStr += '<div class="diy" style="width:20%">' + (treeNode.updateTime) + '</div>';
    editStr += '<div class="diy op_diy" style="width:10%">' + formatHandle(treeNode) + '</div>';
    aObj.append(editStr);
}
/**
 * 根据权限展示功能按钮
 * @param treeNode
 * @returns {string}
 */
function formatHandle(treeNode) {
    var htmlStr = '';
    htmlStr += '<div class="icon_div"><a target="_blank" class="btn btn-info btn-xs" title="查看" href="/backend/code/viewDetail?id='+treeNode.id+'">查看</a></div>';
    htmlStr += '<div class="icon_div"><a target="_blank" class="btn btn-info btn-xs" title="编辑" href="/backend/code/edit?id='+treeNode.id+'">编辑</a></div> ';
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
	
	var addStr = "<span class='button add' style='margin-left:5px;margin-right:5px;background:url(/img/edit.png) 0 0 no-repeat;' id='addBtn_" + treeNode.tId
		+ "' title='编辑菜单' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		if(treeNode.menuLevel==1){
			self.location="/backend/code/codeMenuPage";
		} else if(treeNode.menuLevel==2){
			self.location="/backend/code/codeMenuPage?level=2&codeMenuId="+treeNode.codeMenuId;
		}
		return;
	});
};

//移除按钮显示
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};
</script>

<style>
    /*按钮*/
    .panel-body .icon_div {
        display: inline-block;
        height: 25px;
        width: 35px;
    }
    .panel-body .icon_div a {
        display: inline-block;
        width: 27px;
        height: 20px;
        cursor: pointer;
    }
    /*ztree表格*/
    .panel-body .ztree {
        padding: 0;
        border: 2px solid #CDD6D5;
    }
    .panel-body .ztree li a {
        vertical-align: middle;
        height: 30px;
    }
    .panel-body .ztree li a a  {
    	height: 20px;
    	width:34px;
    	padding-left:4px;
    }
    .panel-body .ztree li > a {
        width: 100%;
    }
    .panel-body .ztree li > a,
    .panel-body .ztree li a.curSelectedNode {
        padding-top: 0px;
        background: none;
        height: auto;
        border: none;
        cursor: default;
        opacity: 1;
    }
    .panel-body .ztree li > a:hover{background-color:#FFE6B0}
    .panel-body .ztree li a.curSelectedNode{background-color:#FFE6B0}
    .panel-body .ztree li ul {
        padding-left: 0px
    }

    .ztree div.diy span {
        line-height: 30px;
        vertical-align: middle;
    }
    .ztree div.diy {
        line-height: 30px;
        border-top: 1px dotted #ccc;
        border-left: 1px solid #eeeeee;
        text-align: center;
        display: inline-block;
        box-sizing: border-box;
        color: #6c6c6c;
        font-family: "SimSun";
        font-size: 12px;
        
	 	white-space:nowrap; 
 		text-overflow:ellipsis; 
 		-o-text-overflow:ellipsis; 
 		overflow: hidden;
    }
    .ztree div.name_diy {
		text-align: left;
	}
	.ztree div.op_diy {
		text-align: center;
	}
	.ztree div.op_diy div img {
		margin-top: 5px;
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
    .diy.swich{width:5000px;}
    /*end--ztree表格*/
    .panel-body .ztree li span.button.ico_docu{margin-top:6px;}
    .panel-body .ztree li span.button.ico_open{margin-top:6px;}
    .panel-body .ztree li span.button.ico_close{margin-top:6px;}
</style>

</@base> 