<html>
    <head>
       	<title>List</title>
       	<#include "../share/meta.ftl">
    </head>
    <body>
    	
    <!-- header -->
    <#include "../share/fix_nav.ftl">
    <link href="/mine/css/waterfall.css" rel="stylesheet">
    <script src="/mine/js/waterfall.js"></script>
    
    <div class="row container">	
    <div id="wrap">
    	<#list picList as item>
    		<div class="box">
				<div class="info">
					<div class="pic"><a href="/list/detail?id=${item.id}">${item.showPic}</a></div>
					<div class="title"><a href="/list/detail?id=${item.id}">${item.title}</a></div>
				</div>
			</div>
		</#list>
	</div>
	</div>
				
          
	<!-- footer -->
	<!-- <#include "../share/footer.ftl"> -->
 		      
    </body>
</html>