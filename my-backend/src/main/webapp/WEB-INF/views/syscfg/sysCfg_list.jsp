<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    			
	   			<!-- table区 -->
	   			<div id="datatable" >
					<table>
						<thead>
							<tr>
								<th field="index_no">序号</th>
								<th width="150" field="key">参数关键字</th>
								<th field="value">参数值</th>
								<th width="180" field="button" btn_list='[
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
			$('#datatable').datatable({
				url_load : '/backend/sys/getList',
				url_delete : '/backend/sys/doDelete',
				buttons:[{type:'add',onclick:'edit'}],
				backFn : function(p) {
					
				}
			}); 
		});
		
		// 修改
		function edit(id){
			var parm = {};
			parm.width="500px";
			parm.height="400px";
			if($.common.isNotBlank(id)){
				parm.loadUrl = '/backend/sys/edit?id='+id;
			} else {
				parm.loadUrl = '/backend/sys/edit';
			}
			$.pop.dialog(parm, editVerify, function(data){
				$.pop.success("保存成功");
				$.fn.reload();
			});
		}
		function editVerify(){
			var formData = $.common.getFormJson('#editForm');
			var rst = '';
			$.common.postSync(formData, '/backend/sys/doSave', function(data){
				if(data.errorNo!=0){
					rst = data.errorInfo;
				}
			});
			return rst;
		}
	</script>
	
