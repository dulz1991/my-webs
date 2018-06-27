<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<div id="formArea"></div>
	
	<div  id="datatable">
		<table>
			<thead>
				<tr>
					<th width="60" field="index_no">编号</th>
					<th field="blogTitle" my-attrs='{textFun:"viewDetail",args:"id",clazz:"table-a"}'>博客标题</th>
					<th field="username">用户名</th>
					<th field="createTime">创建时间</th>
					<th field="remark">备注</th>
				</tr>
			</thead>
		</table>
 	</div>
 			
	<script type="text/javascript">
	$(function(){
		$('#formArea').formRender({
		    formItemList : [
		                    {type:'text',name:'blogTitle', label:'博客标题' },
		                    {type:'text',name:'username', label:'用户名' },
		                    ]
		});
		
		$('#datatable').datatable({
			url_load : '/backend/blogLog/getList'
		}); 
	});
	
	function viewUser(id){
		gotoUrl("/backend/profile/index?userId="+id);
	}
	
	function viewDetail(id){
		gotoUrl('/backend/blogLog/getDetail?id='+id);
	}
	</script>

