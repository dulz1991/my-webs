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
      <title>个人网站首页</title>
      <link type="text/css" href="/home/style.css" rel="stylesheet"/>
      <script type="text/javascript" src="/js/lib/jquery-1.10.2.min.js"></script>
   </head>
<body class="no-js">
    <section class="main">
        <header>
            <div class="wrap">
                <div class="logo">
                    <a href="#">
                        <img src="/home/images/logo.png" width="341" height="68" alt="MY Journey | SIMPLE WAY to do it today">
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
                <h1>Hello! You are looking at My Site.<br>学习！ 整理！ 分享！</h1>
            </div><!-- wrap -->
        </section><!-- caption -->
        <section class="slider">
            <div class="wrap">
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                        	<img src="/home/images/slide3.jpg" width="940" height="360" alt="">    
                        </div><!-- swiper-slide -->
                        <div class="swiper-slide">
                            <img src="/home/images/slide1.jpg" width="940" height="360" alt="">
                        </div><!-- swiper-slide -->
                        <div class="swiper-slide">
                            <img src="/home/images/slide2.jpg" width="940" height="360" alt="">
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
                        <p>记录项目过程中用到的技术、问题以及解决方案. 文档可在线编辑、修改和删除. 支持所有登录用户编辑.</p>
                    </div><!-- features-column -->
                    <div class="features-column">
                        <a href="/code"><span class="ico-rocket"></span></a>
                        <a href="/code"><h3>代码笔记</h3></a>
                        <p>记录所有项目中或者平时学习过程中接触过的技术和遇到的问题解决方案等文档，由后台编辑，这里只做展示.</p>
                    </div><!-- features-column -->
                    <div class="features-column">
                        <a href="http://116.62.205.176:83/" target="_blank"><span class="ico-flag"></span></a>
                        <a href="http://116.62.205.176:83/" target="_blank"><h3>前端demo</h3></a>
                        <p>前端代码demo，或摘自于网上，或自己写的前端demo或者js插件. 基于Nodejs开发，可下载和预览.</p>
                    </div><!-- features-column -->
                </div><!-- features-columns -->
                <div class="separator"></div>
            </div><!-- wrap -->
        </section><!-- features -->
        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >手机网站模板</a></div>
        <section class="textblock">
            <div class="wrap">
                <img src="/home/images/image3.jpg" width="320" height="280" alt="">
                <div class="text-column">
                    <h2>So simple, so beautiful</h2>
                    <p>What will matter is not how many people you knew, but how many will feel a lasting
 						loss when you're gone. What will matter is not your memories, but the memories of 
						those who loved you. What will matter is how long you will be remembered, by whom
 						and for what. Living a life that matters doesn’t happen by accident.</p>
                </div><!-- text-column -->
                <div class="separator"></div>
            </div><!-- wrap -->
        </section><!-- textblock -->
        <section class="textblock textblock-last">
            <div class="wrap">
                <img src="/home/images/image4.jpg" width="320" height="280" class="align-right" alt="">
                <div class="text-column">
                    <h2>Right here, right now</h2>
                    <p>Happiness is not about being immortal nor having food or rights in one's hand. It’s about having each tiny wish come true, or having something to eat when you are hungry or having someone's love when you need love.</p>
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