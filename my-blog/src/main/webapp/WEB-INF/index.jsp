<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta name="description" content="">
      <meta name="author" content="">
      <link rel="icon" type="image/png" href="/img/favicon.png" />
      <title>Home</title>
      <link type="text/css" href="/home/style.css" rel="stylesheet"/>
      <script type="text/javascript" src="/js/lib/jquery-1.10.2.min.js"></script>
   </head>
<body class="no-js">
    <section class="main">
        <header>
            <div class="wrap">
                <div class="logo">
                    <a href="#">
                        <img src="/home/upload/logo.png" width="341" height="68" alt="MY Journey | SIMPLE WAY to do it today">
                    </a>
                </div><!-- logo -->
                <div class="social">
                    <ul class="clearfix">
                        <li><a class="social-facebook" href="/blog" >技术文档</a></li>
                        <li><a class="social-twitter" href="/code" title="twitter">代码笔记</a></li>
                        <c:if test="${isLogin}">
				    		<li><a class="social-googleplus" href="/logout" title="google plus">退出</a></li>
				        </c:if>
				        <c:if test="${!isLogin}">
				        	<li><a class="social-googleplus" href="/login" title="google plus">登录</a></li>	
				        </c:if>
                        
                    </ul>
                </div><!-- social -->
                <div class="separator"></div>
            </div><!-- wrap -->
        </header>
        <section class="caption">
            <div class="wrap">
                <h1>Hello! You are looking at My Site.<br>This is a record and share technology location</h1>
            </div><!-- wrap -->
        </section><!-- caption -->
        <section class="slider">
            <div class="wrap">
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                        	<img src="/home/upload/slide.jpg" width="940" height="360" alt="">    
                        </div><!-- swiper-slide -->
                        <div class="swiper-slide">
                            <img src="/home/upload/slide.jpg" width="940" height="360" alt="">
                        </div><!-- swiper-slide -->
                        <div class="swiper-slide">
                            <img src="/home/upload/slide.jpg" width="940" height="360" alt="">
                        </div><!-- swiper-slide -->
                    </div>
                    <div class="swiper-pagination-wrapper">
                        <div class="swiper-pagination"></div>
                    </div><!-- /swiper-pagination-wrapper -->
                </div>
            </div><!-- wrap -->
        </section><!-- slider -->
        <section class="features">
            <div class="wrap">
                <div class="features-columns clearfix">
                    <div class="features-column">
                        <a href="/blog"><span class="ico-bulb"></span></a>
                        <a href="/blog"><h3>技术文档</h3></a>
                        <p>Clean &amp; Beautiful Design, 	 &amp; CSS3 valid code provide good readability and smooth performance.</p>
                    </div><!-- features-column -->
                    <div class="features-column">
                        <a href="/code"><span class="ico-rocket"></span></a>
                        <a href="/code"><h3>代码笔记</h3></a>
                        <p>Responsive designed and Retina Ready. MyJourney will look perfect on any device you can imagine.</p>
                    </div><!-- features-column -->
                    <div class="features-column">
                        <a href="http://116.62.205.176:83/" target="_blank"><span class="ico-flag"></span></a>
                        <a href="http://116.62.205.176:83/" target="_blank"><h3>前端demo</h3></a>
                        <p>Optimized and valid code using the latest  technologies allows to make changes in the page structure of the template.</p>
                    </div><!-- features-column -->
                </div><!-- features-columns -->
                <div class="separator"></div>
            </div><!-- wrap -->
        </section><!-- features -->
        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >手机网站模板</a></div>
        <section class="textblock">
            <div class="wrap">
                <img src="/home/upload/image.jpg" width="320" height="280" alt="">
                <div class="text-column">
                    <h2>So simple, so beautiful</h2>
                    <p>MyJourney HTML template is a landing page with clean structure and beautiful minimalistic design. It can be useful in so many ways, 
        including the most obvious, whis is to Tell a story.</p>
                    <p>Optimized and valid code using the latest HTML5 and CSS3 technologies allows to make changes in the page structure of the template as it is requared by your needs.</p>
                </div><!-- text-column -->
                <div class="separator"></div>
            </div><!-- wrap -->
        </section><!-- textblock -->
        <section class="textblock textblock-last">
            <div class="wrap">
                <img src="/home/upload/image2.jpg" width="320" height="280" class="align-right" alt="">
                <div class="text-column">
                    <h2>Right here, right now</h2>
                    <p>MyJourney is all about being useful when you need nice and simple way to tell the people about what you’ve seen and felt about something important. Something that really means a lot.</p>
                    <p class="big dark"><a class="button" href="#">More information</a> &nbsp;&nbsp;or&nbsp;&nbsp;&nbsp;<a href="#">get&nbsp;it&nbsp;right&nbsp;now!</a>&nbsp;&nbsp;</p>
                </div><!-- text-column -->
            </div><!-- wrap -->
        </section><!-- textblock -->
        
    </section><!-- main -->
    <footer>
        <div class="footer-image"></div>
    </footer>
    <script src="/home/js/library.js"></script>
    <script src="/home/js/script.js"></script>
    <script src="/home/js/retina.js"></script>
</body>
</html>