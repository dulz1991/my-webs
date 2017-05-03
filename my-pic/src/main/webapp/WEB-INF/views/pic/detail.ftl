<html>
    <head>
       	<title>${picBlog.title}</title>
       	<#include "../share/meta.ftl">
    	<style>
    		.container{width:60%;margin:auto auto;background-color:rgba(255,255,255,0.5);
    			margin-bottom:40px;margin-top:30px;}
    		.content{margin:20px 10px 10px 10px;border-top:1px dashed #aaa;}
    		.time-line{color:#aaa;margin-top:0px;}
    		img{width:100%;}
    	</style>
    </head>
    <body>
    	
    <!-- header -->
    <#include "../share/fix_nav.ftl">
    
    <div class="row container z-depth-4">	
   		<blockquote><h3>${picBlog.title}</h3></blockquote>
   		<div class="content">
   			<p class="time-line">${picBlog.createTime?string("yyyy-MM-dd HH:mm:ss")}</p>
   			<p>${picBlog.content}</p>
   		</div>
   		
    </div>
    	
    </body>
</html>