<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	<div class="col-sm-3 widget-header">
		<div id="zTree" class="ztree"></div>
	</div>
		
	<div class="col-sm-9">
    	<div class="widget">
           	<div class="widget-header">
               	<i class="widget-icon fa fa-code"></i>
               	<span class="widget-caption">
               		${entity.item} 
               		<c:if test="${entity.status==0}"><span class="text-red">(已删除)</span></c:if>
               </span>
               <div class="widget-buttons">
                   <a href="javascript:gotoUrl('/backend/code/edit?id=${entity.id}','_self')" title="编辑">
						<i class="fa fa-edit"></i>
					</a>
					<a href="javascript:reload('${entity.id}');" title="刷新">
						<i class="fa fa-rotate-right"></i>
					</a>
					<c:if test="${entity.status!=0 }">
						<a href="javascript:toDelete('${entity.id}');"  title="删除">
							<i class="fa fa-trash-o"></i>
						</a>
					</c:if>
                   	<a href="#" data-toggle="maximize">
                       <i class="fa fa-expand"></i>
                   	</a>
                   	<a href="#" data-toggle="collapse">
                       <i class="fa fa-minus"></i>
                   	</a>
               	</div>
           	</div>
           
           	<div class="widget-body">
               	${entity.content}
           	</div>
       	</div>
	</div>
                                
	<script>
	/* 左侧树形菜单 */
	var setting = {
		view: {
			selectedMulti: false,
			showIcon: false
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
	$(function(data){
		var title = '${entity.item}';
		if("${entity.status}"==0){
			title += '(已删除)';
		}
		$.common.pageTitle(title);
		
		//菜单zTree
		var zTree = $.fn.zTree.init($("#zTree"), setting, ${codeTitleTree});
		var node = zTree.getNodeByParam("id", '${entity.id}');
		zTree.selectNode(node);
		
		$.common.sidebarSmall();
	})
	
	//重新加载
	function reload(id){
		location.reload();
	}
	//删除
	function toDelete(id){
		$.pop.confirm('确定删除？', '/backend/code/doDelete?id='+id+'&status=0', function(data){
			if(data.errorNo==0){
				location.reload();
			} else {
				$.pop.error(data.errorInfo);
			}
		});
	}
	
	//左侧点击菜单
	function zTreeOnClick(event, treeId, treeNode){
		gotoUrl('/backend/code/viewDetail?id='+treeNode.id, '_self');
	}
	</script>
	
