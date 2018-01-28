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
			          	<div class="col-sm-6">
			          		标题
			            	<input class="form-control" type="text" name="title" value="${entity.title!''}" placeholder="输入标题">
			          	</div>
			        	<div class="col-sm-3">
			        		选择菜单
							<@select id="menuId" class="form-control select" datas=demoMenuList key="id" text="name" value="${entity.menuId!''}" defaultValue="--请选择--" />
			    		</div>
			        </div>
			        <div class="form-group">
			        	<div class="col-sm-12">
			        		选择demo包：
			        		<input type="file" name="attachFile" id="attachFile" accept=".zip" />
			        	</div> 	
			        </div>
			        <div class="form-group">
			        	<div class="col-sm-12">
			        		demo描述：
							<textarea id="description" cols="120" rows="18" name="">${entity.description!''}</textarea>
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
		$.common.ajaxFileSubmit('.form', '/backend/demo/save', function(data){
			if(data.errorNo==200){
        		self.location='/backend/demo/list';
			} else {
				$.commonUtil.showTip(data.errorInfo);
			}
		})
		
		<!--
			$(".form").ajaxSubmit({  
	            type:'post',  
	            url:'/backend/demo/save',  
	            dataType : 'json', //返回值类型 一般设置为json  
		        data:{
					description: $('#description').val()
				},
	            success : function(data, status) {  
	            	debugger;
		        	if(data.errorNo==200){
		        		self.location='/backend/demo/list';
					} else {
						$.commonUtil.showTip(data.errorInfo);
					}
		        },  
		        error : function(data, status, e) {  
		        	$.commonUtil.showTip(data.errorInfo); 
		        }   
	        }); 
		-->
	});
});
</script>

</@base> 