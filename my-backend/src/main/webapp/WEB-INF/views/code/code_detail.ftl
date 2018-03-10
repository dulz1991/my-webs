<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="${entity.item}--代码笔记" openIndex=2 activeIndex=9999>
	
	<div class="row">
		<div class="col-sm-12 panel panel-default">
			<div class="panel panel-default"><!-- Add class "collapsed" to minimize the panel -->
				
				<div class="panel-heading">
					<h3 class="panel-title">${entity.item}</h3>
					
					<div class="panel-options">
						<a href="#">
							<i class="linecons-cog"></i>
						</a>
						
						<a href="#" data-toggle="panel">
							<span class="collapse-icon">–</span>
							<span class="expand-icon">+</span>
						</a>
						
						<a href="#" data-toggle="reload">
							<i class="fa-rotate-right"></i>
						</a>
						
						<a href="#" data-toggle="remove">
							×
						</a>
					</div>
				</div>
				
				<div class="panel-body">
					${entity.content}
				</div>
				
			</div>
		</div>
	</div>
	
	<style>
	p{color:#000;}
	</style>

</@base> 