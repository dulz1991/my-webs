<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<div class="row">
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="databox bg-white">
            	<div class="databox-left no-padding">
                    <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="databox-right bordered bordered-platinum">
                    <a href="javascript:gotoUrl('/backend/user/list');"><span class="databox-number sky">${userCount }</span></a>
                    <div class="databox-text darkgray"> 
                    	<a href="javascript:gotoUrl('/backend/user/list');">用户人数</a>
                    </div>
                    <!-- <div class="databox-stat bg-palegreen radius-bordered">
                        <div class="stat-text">8%</div>
                        <i class="stat-icon fa fa-arrow-up"></i>
                    </div> -->
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="databox bg-white">
            	<div class="databox-left no-padding">
                    <i class="menu-icon fa fa-bold"></i>
                </div>
                <div class="databox-right bordered bordered-platinum">
                    <a href="javascript:gotoUrl('/backend/blog/list');"><span class="databox-number sky">${blogCount }</span></a>
                    <div class="databox-text darkgray">
                    	<a href="javascript:gotoUrl('/backend/blog/list');">代码博客数量</a>
                    </div>
                    <!-- <div class="databox-stat bg-palegreen radius-bordered">
                        <div class="stat-text">8%</div>
                        <i class="stat-icon fa fa-arrow-up"></i>
                    </div> -->
                </div>
            </div>
        </div>
        
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="databox bg-white">
            	<div class="databox-left no-padding">
                    <i class="menu-icon fa fa-code"></i>
                </div>
                <div class="databox-right bordered bordered-platinum">
                    <a href="javascript:gotoUrl('/backend/code/zTreelist');"><span class="databox-number sky">${codeCount }</span></a>
                    <div class="databox-text darkgray">
                    	<a href="javascript:gotoUrl('/backend/code/zTreelist');">代码笔记数量</a>
                    </div>
                    <!-- <div class="databox-stat bg-palegreen radius-bordered">
                        <div class="stat-text">8%</div>
                        <i class="stat-icon fa fa-arrow-up"></i>
                    </div> -->
                </div>
            </div>
        </div>
        
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="databox bg-white">
            	<div class="databox-left no-padding">
                    <i class="menu-icon fa fa-list-ul"></i>
                </div>
                <div class="databox-right bordered bordered-platinum">
                    <a href="javascript:gotoUrl('/courseUsual/courseUsualList');"><span class="databox-number sky">${demoCount }</span></a>
                    <div class="databox-text darkgray">
                    	<a href="javascript:gotoUrl('/courseUsual/courseUsualList');">Demo数量</a>
                    </div>
                    <!-- <div class="databox-stat bg-palegreen radius-bordered">
                        <div class="stat-text">8%</div>
                        <i class="stat-icon fa fa-arrow-up"></i>
                    </div> -->
                </div>
            </div>
        </div>
    </div>
    
    <div class="row">
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="databox bg-white">
            	<div class="databox-left no-padding">
                    <i class="glyphicon glyphicon-user"></i>
                </div>
                <div class="databox-right bordered bordered-platinum">
                    <a href="javascript:;"><span class="databox-number sky">
                    ${userImg.imgCount }
                    (<fmt:formatNumber type="number" value="${userImg.imgSize/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>MB)
                    </span></a>
                    <div class="databox-text darkgray"> 
                    	<a href="javascript:;">用户文件数</a>
                    </div>
                    <!-- <div class="databox-stat bg-palegreen radius-bordered">
                        <div class="stat-text">8%</div>
                        <i class="stat-icon fa fa-arrow-up"></i>
                    </div> -->
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="databox bg-white">
            	<div class="databox-left no-padding">
                    <i class="menu-icon fa fa-bold"></i>
                </div>
                <div class="databox-right bordered bordered-platinum">
                    <a href="javascript:;"><span class="databox-number sky">
                    ${blogImg.imgCount }
                    (<fmt:formatNumber type="number" value="${blogImg.imgSize/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>MB)
                    </span></a>
                    <div class="databox-text darkgray">
                    	<a href="javascript:;">代码博客文件数</a>
                    </div>
                    <!-- <div class="databox-stat bg-palegreen radius-bordered">
                        <div class="stat-text">8%</div>
                        <i class="stat-icon fa fa-arrow-up"></i>
                    </div> -->
                </div>
            </div>
        </div>
        
        <div class="col-lg-3 col-sm-6 col-xs-12">
            <div class="databox bg-white">
            	<div class="databox-left no-padding">
                    <i class="menu-icon fa fa-code"></i>
                </div>
                <div class="databox-right bordered bordered-platinum">
                    <a href="javascript:;"><span class="databox-number sky">
                    ${codeImg.imgCount }
                    (<fmt:formatNumber type="number" value="${codeImg.imgSize/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>MB)
                    </span></a>
                    <div class="databox-text darkgray">
                    	<a href="javascript:;">代码笔记文件数</a>
                    </div>
                    <!-- <div class="databox-stat bg-palegreen radius-bordered">
                        <div class="stat-text">8%</div>
                        <i class="stat-icon fa fa-arrow-up"></i>
                    </div> -->
                </div>
            </div>
        </div>
        
        
    </div>


