<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<div id="formArea"></div>
    			
   			<!-- table区 -->
   			<div  id="datatable">
				<table>
					<thead>
						<tr>
							<th width="60" field="index_no">编号</th>
							<th field="picPath|img">图片</th>
							<th field="title" my-attrs='{textFun:"viewDetail",args:"url",clazz:"table-a"}'>标题</th>
							<th field="name">所属分类</th>
							<th field="downloadTimes">下载次数</th>
							<th field="createTimeStr">创建时间</th>
							<th field="updateTimeStr">更新时间</th>
							<th field="button" width="110" btn_list='[
				                {fnName:"edit",args:"id",name:"编辑",icon:"fa fa-edit",cls:"btn-warning"}
				               ]'>
						</tr>
					</thead>
				</table>
   			</div>
   			<!-- table区 结束 -->
   			
   			<div id="menuContent" class="menuContent">
				<ul id="menuTreeDemo" class="ztree" style="width:160px;"></ul>
				<div class="menuContentClean"><span onclick="cleanDemoMenu()">清除</span></div>
			</div>
	
	<script type="text/javascript" src="/js/custom/my.tree.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#formArea').formRender({
			    formItemList : [
								{type:'hidden',name:'menuId', label:'demo分类' },
			                    {type:'text',name:'title', label:'Demo标题' },
			                    /* {type:'select',name:'menuId', label:'Demo菜单',list:${demoMenuList}, sKey:'id', sValue:'name', value:'${demoMenuId}' } */
			                    {type:'text',name:'menuName', label:'demo分类', ztree:true  }
			                    ]
			});
			
			ztree = $('#menuTreeDemo').mytree({
				data : ${demoMenuList},
				onClick : zTreeOnClick
			});
			
			$('#datatable').datatable({
				url_load : '/backend/demo/getList',
				url_delete : '/backend/demo/doDelete',
				parm : $.common.getFormJson('.form'),
				buttons: [{type:'add',onclick:'edit'}],
				backFn : function(p) {
					
				}
			}); 
			
		});
		
		function viewDetail(url){
			if(url.indexOf("http")>=0){
				window.open(url)	
			} else {
				window.open('/api_img'+url)
			}
		}

		function edit(id){
			gotoUrl('/backend/demo/edit?id='+(id||''));
		}
		
		function zTreeOnClick(e, treeId, treeNode) {
			$('input[name="menuId"]').val(treeNode.id);
			$('input[name="menuName"]').val(treeNode.name);
		}
		function cleanDemoMenu(){
			$('input[name="menuId"]').val('');
			$('input[name="menuName"]').val('');
		}
	</script>

