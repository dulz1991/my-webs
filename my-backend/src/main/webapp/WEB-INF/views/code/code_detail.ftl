<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="${entity.item}--代码笔记" openIndex=2 activeIndex=9999>
	
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel panel-default"><!-- Add class "collapsed" to minimize the panel -->
				
				<div class="panel-heading">
					<h3 class="panel-title">
						${entity.item}
						<#if entity.status==0>
						<span style="color:#f00">(已删除)</span>
						</#if>
					</h3>
					
					<div class="panel-options">
						<a href="/backend/code/edit?id=${entity.id}" title="编辑">
							<i class="fa fa-edit"></i>
						</a>
						<a href="#" onclick="reload('${entity.id}')" data-toggle="reload" title="刷新">
							<i class="fa-rotate-right"></i>
						</a>
						<#if entity.status!=0>
						<a href="javascript:toDelete('${entity.id}');"  title="删除">
							<i class="fa-remove"></i>
						</a>
						</#if>
					</div>
				</div>
				
				<div class="panel-body">
					${entity.content}
				</div>
				
			</div>
		</div>
	</div>
	
	<script>
	//重新加载
	function reload(id){
		var parm = {id:id};
		$.common.getRequest(parm, '/backend/code/viewDetailReload',function(data){
			if(data.errorNo==200){
				$('.panel-body').html(data.entity.content);
			} else {
				$.common.tip(data.errorInfo);
			}
		});
	}
	//删除
	function toDelete(id){
		var parm = {};
		parm.id=id;
		parm.status=0;
		$.common.doDelete(parm, '/backend/code/doDelete',function(data){
			if(data.errorNo==200){
				self.location = "";
			} else {
				$.common.tip(data.errorInfo);
			}
		});
	}
	</script>
	
	<style>
	p{color:#000;}
	</style>

</@base> 