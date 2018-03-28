<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="Demo编辑" openIndex=4 activeIndex=0>
	
	<!-- 编辑区 -->
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Demo编辑</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal form" method="post" action="javascript:;" enctype="multipart/form-data">
					<input type="hidden" value="${entity.id!''}" name="id" />
			        
			        <div class="form-group">
			          	<div class="col-sm-4">
			          		<span>标题</span>
			            	<input class="form-control" type="text" name="title" value="${entity.title!''}" placeholder="输入标题">
			          	</div>
			          	<#if !entity.id??>
			        		<div class="col-sm-3">
				          		<span>资源名称</span>
			            		<input class="form-control" type="text" name="resourceName" value="" placeholder="如：demo.zip">
				          	</div>
				        </#if>
			        	<div class="col-sm-3">
			        		<span>选择菜单</span>
							<@select id="menuId" class="form-control select" datas=demoMenuList key="id" text="name" value="${entity.menuId!''}" defaultValue="--请选择--" />
			    		</div>
			        </div>
			        
			        <#if entity.id??>
			        	<div class="form-group">
				        	<div class="col-sm-8">
				          		<span>访问的文件名</span>
				            	<input class="form-control" type="text" name="url" value="${entity.url!''}" placeholder="如：index.html">
				          	</div>
				          	<div class="col-sm-8">
				          		<span>图片名称</span>
				            	<input class="form-control" type="text" name="picPath" value="${entity.picPath!''}" placeholder="如：demo.png">
				          	</div>
				          	<div class="col-sm-8">
				          		<span>资源名称</span>
				            	<input class="form-control" type="text" name="resourcePath" value="${entity.resourcePath!''}" placeholder="如：demo.zip">
				          	</div>
				        </div>
			        </#if>
			        
			        <#--
			        <div class="form-group">
			        	<div class="col-sm-12">
			        		<p>选择demo包</p>
			        		<input type="file" name="attachFile" id="attachFile" accept=".zip" />
			        	</div> 	
			        </div>
			        -->
			        
			        <div class="form-group">
			        	<div class="col-sm-12">
			        		<p>demo描述</p>
							<textarea id="description" cols="120" rows="18" name="description">${entity.description!''}</textarea>
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
			if(data.errorNo==200){
        		self.location='/backend/demo/list';
			} else {
				$.common.tip(data.errorInfo);
			}
		})
	});
});
</script>

</@base> 