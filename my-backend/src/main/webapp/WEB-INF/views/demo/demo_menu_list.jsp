<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

			<div id="formArea"></div>
			
   			<div id="datatable">
				<table>
					<thead>
						<tr>
							<th field="index_no">编号</th>
							<th field="name">菜单名称</th>
							<th field="pathLinux">linux目录</th>
							<th field="pathWindows">windows目录</th>
							<th field="button"
								btn_list='[
				                {fnName:"edit",args:"id",name:"修改",icon:"fa fa-edit"},
				                {fnName:"$.fn.deleteById", args:"id",name:"删除",icon:"fa fa-trash-o",cls:"btn-danger"}
				                ]'
							></th>
						</tr>
					</thead>
				</table>
   			</div>
   			
   			<div id="menuContent" class="menuContent">
				<ul id="menuTreeDemo" class="ztree" style="width:160px;"></ul>
				<div class="menuContentClean"><span onclick="cleanDemoMenu()">清除</span></div>
			</div>
	
	<script type="text/javascript" src="/js/custom/my.tree.js"></script>
	<script type="text/javascript">
		var ztree;
		$(function(){
			$('#formArea').formRender({
			    formItemList : [
								{type:'hidden',name:'parentId', label:'demo分类' },
			                    {type:'text',name:'name', label:'分类名称' },
			                    {type:'text',name:'parentName', label:'demo分类', ztree:true  }
			                    ]
			});
			
			ztree = $('#menuTreeDemo').mytree({
				data : ${demoMenuList},
				onClick : zTreeOnClick
			});
			
			$('#datatable').datatable({
				url_load : '/backend/demoMenu/getList',
				url_delete : '/backend/demoMenu/doDelete',
				buttons: [{type:'add', onclick:'edit'}],
				backFn : function(p) {
					// console.log(p);
				}
			}); 
		});
		
		// 修改
		function edit(id){
			var parm = {};
			if($.common.isNotBlank(id)){
				parm.loadUrl = '/backend/demoMenu/edit?id='+id;
				parm.title = '修改菜单';
			} else {
				parm.loadUrl = '/backend/demoMenu/edit';
				parm.title = '新增菜单';
			}
			parm.height='400px';
			$.pop.dialog(parm, editVerify, function(data){
				$.pop.success("保存成功");
				$.fn.reload();
			});
		}
		function editVerify(){
			var formData = $.common.getFormJson('#editForm');
			var rst = '';
			$.common.postSync(formData, '/backend/demoMenu/doSave', function(data){
				if(data.errorNo!=0){
					rst = data.errorInfo;
				}
			});
			return rst;
		}
		
		function zTreeOnClick(e, treeId, treeNode) {
			$('input[name="parentId"]').val(treeNode.id);
			$('input[name="parentName"]').val(treeNode.name);
		}
		function cleanDemoMenu(){
			$('input[name="parentId"]').val('');
			$('input[name="parentName"]').val('');
		}
		
	</script>


