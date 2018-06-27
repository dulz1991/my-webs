<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<div id="formArea"></div>
	
	<div id="datatable">
		<table>
			<thead>
				<tr>
					<th field="index_no">序号</th>
					<th field="title" my-attrs='{textFun:"viewDetail",args:"id",clazz:"table-a"}'>标题</th>
					<th field="username">用户</th>
					<th field="menuName">分类</th>
					<th field="click">点击</th>
					<th field="createTime">创建时间</th>
					<th field="updateTime">更新时间</th>
				</tr>
			</thead>
		</table>
 	</div>
	
	<script type="text/javascript">
	$(function(){
		$('#formArea').formRender({
		    formItemList : [
		                    {type:'text',name:'title', label:'标题' },
		                    {type:'text',name:'username', label:'用户/作者' },
		                    {type:'select',name:'menuId', label:'博客分类',list:${menuList}, sKey:'id', sValue:'name' }
		                    ]
		}); 
		
		$('#datatable').datatable({
			url_load : '/backend/blog/getList',
			url_remove : '/backend/blog/doDelete',
			parm:{
				pageNo : 1,
				pageSize : 10,
				menuId:'${blogMenuId}'
			},
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	});
	
	function viewDetail(id){
		gotoUrl('/backend/blog/getDetail?id='+id);
	}
	</script>

