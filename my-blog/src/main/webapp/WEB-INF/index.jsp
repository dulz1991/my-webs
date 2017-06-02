<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta name="description" content="">
      <meta name="author" content="">
      <link rel="icon" type="image/png" href="/img/favicon.png" />
      <title>Home</title>
      <link type="text/css" href="/css/home.css" rel="stylesheet"/>
      <script type="text/javascript" src="/js/lib/jquery-1.10.2.min.js"></script>
      <script src="/js/extend/pageswitch.js"></script>
   </head>
   <body>
		<div id="container">
			<div class="section active" id="section0">
				<div class="intro">
					<h1 class="title"><a href="/blog/index">My·Blog</a></h1>
					<p>A summary of the problems encountered in the learning process</p>
					<p><img style="width:40%;" src="/img/example.png"/></p>
				</div>
			</div>
			<div class="section" id="section1">
				<div class="intro">
					<h1 class="title"><a href="/code/index">My·Code</a></h1>
					<p>A summary of the technologies which have been approached or have been learned.x</p>
					<img src="/img/example2.png"/>
				</div>
				
			</div>
			<div class="section" id="section2">
				<div class="intro">
					<h1 class="title"><a href="http://my.pic">My·Pic</a></h1>
					<p>Memories of the journey</p>
					<img src="/img/example2.png"/>
				</div>
			</div>
		</div>
		
	<script type="text/javascript">
		$(function(){
			$("#container").switchPage({
				'loop' : true,
				'keyboard' : true,
				'direction' : 'horizontal'
			});
		});
	</script>
</body>
</html>