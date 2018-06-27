<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-body">
				<form class="form-horizontal form" method="post" action="javascript:;" enctype="multipart/form-data">
					<input type="hidden" value="${entity.id}" name="id" />
			        
			        <div class="form-group">
			          	<div class="col-sm-4">
			          		<span>标题</span>
			            	<input class="form-control" type="text" name="title" value="${entity.title}" placeholder="输入标题">
			          	</div>
			          	<c:if test="${not empty entity.id}">
			        		<div class="col-sm-3">
				          		<span>资源名称</span>
			            		<input class="form-control" type="text" name="resourceName" value="" placeholder="如：demo.zip">
				          	</div>
				        </c:if>
			        	<div class="col-sm-3">
			        		<span>选择菜单</span>
			        		<select name="menuId" class="form-control">
			        			<option value="">--</option>
			        			<c:forEach var="item" items="${demoMenuList }">
			        				<option value="${item.id }">${item.name }</option>
			        			</c:forEach>
			        		</select>
			    		</div>
			        </div>
			        
			        <c:if test="${empty entity.id}">
			        	<div class="form-group">
				        	<div class="col-sm-8">
				          		<span>访问的文件名</span>
				            	<input class="form-control" type="text" name="url" value="${entity.url}" placeholder="如：index.html">
				          	</div>
				          	<div class="col-sm-8">
				          		<span>图片名称</span>
				            	<input class="form-control" type="text" name="picPath" value="${entity.picPath}" placeholder="如：demo.png">
				          	</div>
				          	<div class="col-sm-8">
				          		<span>资源名称</span>
				            	<input class="form-control" type="text" name="resourcePath" value="${entity.resourcePath}" placeholder="如：demo.zip">
				          	</div>
				        </div>
			        </c:if>
			        
			        <!-- <div class="form-group">
			        	<div class="col-sm-12">
			        		<p>选择demo包</p>
			        		<input type="file" name="attachFile" id="attachFile" accept=".zip" />
			        	</div> 	
			        </div> -->
			        
			        <div class="form-group">
			        	<div class="col-sm-12">
			        		<p>demo描述</p>
							<textarea id="description" cols="120" rows="18" name="description">${entity.description}</textarea>
			        	</div> 	
			        </div>
			        <div class="form-group">
			        	<div class="col-sm-12">
			        		<button class="btn btn-info btn-icon" id="demoSubmit">
								<span>提交</span>
							</button>
			    		</div>
			        </div>
			     </form>
			</div>
		</div>
	</div>
	<!-- 编辑区结束 -->

<script type="text/javascript">
$(function(){
	$('#demoSubmit').click(function(){
		$.common.ajaxFileSubmit('.form', '/backend/demo/save1', function(data){
			window.close();
    		/* self.location='/backend/demo/list'; */
		})
	});
	
	/* $.common.sidebarSmall(); */
	$.common.pageTitle('Demo编辑');
});
</script>

