<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	<div id="formArea"></div>
	
	<div  id="datatable">
		<table>
			<thead>
				<tr>
					<th field="index_no">序号</th>
					<th field="name">分类名称</th>
					<th field="button" btn_list='[
		                {fnName:"edit", args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn btn-info btn-xs"},
		                {fnName:"$.fn.deleteById", args:"id",name:"删除",icon:"fa fa-trash-o",cls:"btn btn-danger btn-xs"}
		                ]'>
		            </th>
				</tr>
			</thead>
		</table>
	</div>
 			
	
	<script type="text/javascript">
	$(function(){
		$('#formArea').formRender({
		    formItemList : [
		                    {type:'text',name:'name', label:'分类名称' },
		                    ]
		});
		
		$('#datatable').datatable({
			url_load : '/backend/blogMenu/getList',
			url_delete : '/backend/blogMenu/doDelete',
			buttons:[{type:'add',onclick:'edit'}],
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	});
	
	// 修改
	function edit(id){
		var parm = {};
		if($.common.isNotBlank(id)){
			parm.loadUrl = '/backend/blogMenu/edit?id='+id;
			parm.title = '修改菜单';
		} else {
			parm.loadUrl = '/backend/blogMenu/edit';
			parm.title = '新增菜单';
		}
		parm.height='200px';
		$.pop.dialog(parm, editVerify, function(data){
			$.pop.success("保存成功");
			$.fn.reload();
		});
	}
	function editVerify(){
		var formData = $.common.getFormJson('#editForm');
		var rst = '';
		$.common.postSync(formData, '/backend/blogMenu/doSave', function(data){
			if(data.errorNo!=0){
				rst = data.errorInfo;
			}
		});
		return rst;
	}
	
	</script>

