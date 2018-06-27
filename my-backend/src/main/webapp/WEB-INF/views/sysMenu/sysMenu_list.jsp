<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    		
    		<div id="formArea"></div>
    			
   			<!-- table区 -->
   			<div  id="datatable">
				<table>
					<thead>
						<tr>
							<!-- <th field="check_box" value="id">
			                     <label class="checkbox">
			                         <input type="checkbox" id="chkAll" class="colored-success">
			                         <span class="text"></span>
			                     </label>
							</th> -->
							<th field="index_no">序号</th>
							<th field="menuName">菜单名称</th>
							<th field="menuLevel">菜单级别</th>
							<th field="icon">图标</th>
							<th field="url">访问地址</th>
							<th field="button" btn_list='[
			                {fnName:"edit",args:"id",name:"修改",cls:"btn-warning"},
			                {fnName:"$.fn.deleteById",args:"id",name:"删除",cls:"btn-danger"}
			                ]'>操作</th>
						</tr>
					</thead>
				</table>
   			</div>
   			<!-- table区 结束 -->
	
	<script type="text/javascript">
		$(function(){
			$('#formArea').formRender({
			    formItemList : [
			                    {type:'text',name:'menuName', label:'菜单名称' },
			                    {type:'text',name:'menuLevel', label:'菜单级别' },
			                    {type:'select',name:'parentId', label:'父级菜单',list:${levelOneList}, sKey:'id', sValue:'menuName' }
			                    ]
			});
			
			$('#datatable').datatable({
				url_load : '/sysMenu/getList',
				url_delete : '/sysMenu/doDelete',
				buttons:[{type:'add',onclick:'edit'}],
				backFn : function(p) {
					
				}
			}); 
			
		});
		
		// 修改
		function edit(id){
			var parm = {};
			if($.common.isNotBlank(id)){
				parm.loadUrl = '/sysMenu/edit?id='+id;
				parm.title = '修改菜单';
			} else {
				parm.loadUrl = '/sysMenu/edit';
				parm.title = '新增菜单';
			}
			$.pop.dialog(parm, editVerify, function(data){
				$.pop.success("保存成功");
				$.fn.reload();
			});
		}
		function editVerify(){
			var formData = $.common.getFormJson('#editForm');
			var rst = '';
			$.common.postSync(formData, '/sysMenu/doSave', function(data){
				if(data.errorNo!=0){
					rst = data.errorInfo;
				}
			});
			return rst;
		}
	</script>
	
