<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

	<div id="datatable">
		<table>
			<thead>
				<tr>
					<th field="index_no">序号</th>
					<th field="fileName" my-attrs='{textFun:"openFile",args:"fileType,filePath,fileName",clazz:"table-a"}'>名称</th>
					<th field="fileType">文件类型</th>
					<th field="filePath">文件路径</th>
					<th field="updateTime">修改时间</th>
				</tr>
			</thead>
		</table>
 	</div>
 	
 	<script type="text/javascript">
 	var dt;
 	var parentFolder,dispalyName;
 	$(function(){
 		dt = $('#datatable').datatable({
			url_load : '${loadUrl}',
			showPage : false,
			nextPage : 'dt.nextPage',
			onExport : 'dt.onExport',
			buttons :[{type:'custom',label:'上级菜单',onclick:'backFolder', id:'backFolder'}],
			backFn : function(p) {
				if($.common.isBlank(p.parentFolder)){
					$('#backFolder').hide();
					parentFolder = "";
				} else {
					parentFolder = p.parentFolder;
					$('#backFolder').show();
				}
				$('.widget-header .widget-caption').text(displayName);
			}
		}); 
 	});
 	
 	function openFile(fileType,filePath,fileName){
 		displayName = fileName;
 		if(fileType=='文件夹'){ //查看下级文件
 			dt.manualSearch({folder:filePath});
 		} else if('html|txt|htm'.indexOf(fileType)>-1) { //打开页面
 			filePath = filePath.replace('F:\\data\\','/');
 			window.open('/api_img/'+filePath);
 		} else if('jpg|jpeg|png|gif|bump'.indexOf(fileType)>-1) {
 			filePath = filePath.replace('F:\\data\\','/');
 			$.common.openImg('/api_img/'+filePath);
 		} else {
 			$.pop.error(fileType+"文件类型暂无查看功能");
 		}
 	}
 	
 	function backFolder(){
 		dt.manualSearch({folder:parentFolder});
 	}
 	</script>