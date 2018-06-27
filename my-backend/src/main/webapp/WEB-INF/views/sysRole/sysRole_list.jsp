<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    			
	   			<!-- table区 -->
	   			<div id="datatable">
					<table>
						<thead class="navbar-mystyle" >
							<tr>
								<th field="index_no">序号</th>
								<th field="roleName">权限名称</th>
								<th field="roleCode">权限编码</th>
								<th field="roleDescription">权限描述</th>
								<th field="button" width="160" btn_list='[
									{fnName:"viewSysUser", args:"roleCode,roleName",name:"查看人员"},
									{fnName:"addMenu", args:"id",name:"编辑菜单"}
								]'></th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- table区 结束 -->
	   			
	
	<script type="text/javascript">
		$(function(){
			$('#datatable').datatable({
				url_load : '/sysRole/getList',
				backFn : function(p) {
					
				}
			}); 
		});
		
		function viewSysUser(roleCode,roleName){
			$.common.get({}, '/sysRole/getSysUserByRoleCode?roleCode='+roleCode, function(data){
				if(data.errorNo==0){
					if(data.list.length==0){
						$.pop.info("无此权限用户");
						return;
					}
					var html = '';
					html += '<div >';
					html += '	<table class="table table-bordered table-hover bg-white">';
					html += '		<thead class="navbar-mystyle" >';
					html += '			<tr>';
					html += '				<th>登录名</th>';
					html += '				<th>状态</th>';
					html += '			</tr>';
					html += '		</thead>';
					html += '		<tbody>';
					
					for(var i=0; i<data.list.length; i++){
						var item = data.list[i];
						html += '		<tr><td>'+item.username+'</td><td>'+item.statusStr+'</td></tr>';
					}
					
					html += '		</tbody>';
					html += '	</table>';
					html += '</div>';
					$.pop.view({html:html,title:'有【'+roleName+'】权限的系统用户'});					
				} else {
					$.pop.error(data.errorInfo);
				}
			});
		}
		
		/* 添加菜单 */
		function addMenu(roleId){
			var parm = {};
			parm.loadUrl = '/sysRole/addMenu?roleId='+roleId; 
			parm.title='编辑此权限下的菜单';
	       	$.pop.dialog(parm, addMenuVerify, function(data){
	       		$.pop.success("保存成功");
	       	});
		}
		function addMenuVerify(){
			var formData = {};
			formData.roleId=$('input[name="hiddenRoleId"]').val();
			var menuIds = '';
			var nodes = treeObj.getCheckedNodes(true);
	        for(var i=0;i<nodes.length;i++){
	        	menuIds += nodes[i].id + ','; 
	        }
	        formData.menuIds = menuIds;
	        console.log(formData);
			var rst=''; 
			$.common.postSync(formData, '/sysRole/saveMenu', function(data){
				if(data.errorNo!=0){
					rst = data.errorInfo;
				}
			});
			return rst;
		}
	</script>
	
</body>
</html>