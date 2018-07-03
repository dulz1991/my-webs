<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

		
		<form class="form-horizontal form" id="editForm" action="javascript:void(0);" style="padding:10px">
			<input type="hidden" name="id" value="${entity.id}">
			<input type="hidden" value="${entity.parentId}" name="parentId" />
			<div class="form-group">
				<div class="col-sm-11">
					菜单名称
					<input type="text" class="form-control input" name="name" value="${entity.name}">
				</div>
				
				<div class="col-sm-11">
	        		父级分类
					<div id="zTree" class="ztree"></div>
				</div>
				
				<div class="col-sm-11">
					windows路径
					<input type="text" class="form-control input" name="pathWindows" value="${entity.pathWindows}">
				</div>
				
				<div class="col-sm-11">
					linux路径
					<input type="text" class="form-control input" name="pathLinux" value="${entity.pathLinux}">
				</div>
			</div>
		</form>

		<script type="text/javascript" src="/js/custom/my.tree.js"></script>
		<script>
		var ztree;
		$(function(){
			ztree = $('#zTree').mytree({
				data : ${demoMenuList},
				onClick : zTreeOnClick
			});
			var node = ztree.treeObj.getNodeByParam("id", '${entity.parentId}');
			ztree.treeObj.selectNode(node);
		})
		function zTreeOnClick(event, treeId, treeNode){
			$('input[name="parentId"]').val(treeNode.id);
		}
		</script>