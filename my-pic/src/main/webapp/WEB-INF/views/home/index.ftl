<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    	<title>My Journey 首页</title>
    	<#include "../share/meta.ftl">
    </head>
    <body>
    	
    	<!-- header -->
    	<#include "../share/fix_nav.ftl">
    	<script>
    		$(function(){
    			Materialize.showStaggeredList('.staggered-list');
    		});
    		var options = [
		        {selector: '.image-fade', offset: 10, callback: 'Materialize.fadeInImage(".image-fade");' },
		        {selector: '.staggered-text', offset: 10, callback: 'Materialize.showStaggeredList(".staggered-text");' }
      			];
      		Materialize.scrollFire(options);
    	</script>
    	
    	<div class="slider">
    		<ul class="slides">
      			<li>
        			<img src="/mine/img/head_1.jpg"> <!-- random image -->
        			<div class="caption center-align">
          				<h3>This is our big Tagline!</h3>
          				<h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
        			</div>
      			</li>
      			<li>
        			<img src="/mine/img/head_2.jpg"> <!-- random image -->
        			<div class="caption left-align">
          				<h3>Left Aligned Caption</h3>
          				<h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
        			</div>
      			</li>
      			<li>
        			<img src="/mine/img/head_3.jpg"> <!-- random image -->
        			<div class="caption right-align">
          				<h3>Right Aligned Caption</h3>
          				<h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
        			</div>
 	    		</li>
      			<li>
        			<img src="/mine/img/head_4.jpg"> <!-- random image -->
        			<div class="caption center-align">
          				<h3>This is our big Tagline!</h3>
          				<h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
        			</div>
      			</li>
    		</ul>
  		</div>
        
		<div class="row container">
			<ul class="staggered-list">
				<li>
					<h2 class="header">WELCOME TO VISIT MY PIC</h2>
				</li>
				<li>
					<p><i class="fa fa-location-arrow"></i> Github SSH URL: git@github.com:dulz1991/myGit.git</p>
				</li>
				<li>
					<p><i class="fa fa-location-arrow"></i> Github HTTPS URL: https://github.com/dulz1991/myGit.git</p>
				</li>
			</ul>
    	</div>
    	
		
		<hr class="" style="border:1px solid #eee;" />
				
		<div class="row">
        	<div class="col s12">
        		<#list list as item>
            	<div class="card medium col s4">
              		<div class="card-image">
                		<a href="/list/detail?id=${item.id}">${item.showPic}</a>
                		<span class="card-title">${item.title}</span>
              		</div>
              		<div class="card-content">
                		<ul class="staggered-text">
							<li>
								<p>${item.description}</p>
							</li>
						</ul>
              		</div>
              		<div class="card-action">
                		<a href="/list/">显示更多</a>
                		<a href="/list/detail?id=${item.id}">查看详情</a>
              		</div>
            	</div>
            	</#list>
        	</div>
        </div>
          
	<!-- footer -->
	<#include "../share/footer.ftl">
 		      
    </body>
</html>