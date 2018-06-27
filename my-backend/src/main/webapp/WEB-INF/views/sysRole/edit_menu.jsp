<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<input type="hidden" value="${roleId }" name="hiddenRoleId">
   	<div id="menuTree" class="ztree"></div>
	
	<script type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkStyle : "checkbox",
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =${menuTree};
		var treeObj;
		$(document).ready(function(){
			treeObj = $.fn.zTree.init($("#menuTree"), setting, zNodes);
			treeObj.expandAll(true);
		});
		
	</script>
	
	<style>
	/* input[type=checkbox],input[type=radio]{opacity: 1;position: static;width:14px;height:14px;} */
	</style>
