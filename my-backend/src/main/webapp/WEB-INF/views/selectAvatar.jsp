<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
    ul{padding: 0;margin: 0;}
    li{list-style-type: none;}
    .avatarsList,.avatars{width:100%;}
    .avatarsList:after{content: '';display: table;clear: both;}
    .avatarsItem{float: left;margin:5px;cursor: pointer;}
    img{width:100px;height:100px;}
    .avatars img{display:block;margin:0 auto;border: 1px solid #ccc;}

</style>
<form id="addForm" style="padding: 10px;">
    <div class="col-lg-12 col-sm-12 col-xs-12">
            <div class="widget">
                <div class="widget-body no-padding">
                    <div class="widget-main ">
                        <div class="panel-group accordion" id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading ">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                            卡通类
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in">
                                    <div class="panel-body border-red">
                                        <ul class="avatarsList">
                                            <li class='avatarsItem'><img src='/img/avatar/cartoon/1.png'></li>
                                            <li class='avatarsItem'><img src='/img/avatar/cartoon/2.png'></li>
                                            <li class='avatarsItem'><img src='/img/avatar/cartoon/5.png'></li>
                                            <li class='avatarsItem'><img src='/img/avatar/cartoon/3.gif'></li>
                                            <li class='avatarsItem'><img src='/img/avatar/cartoon/4.png'></li>
                                            <li class='avatarsItem'><img src='/img/avatar/cartoon/logo.png'></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                            风景类
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body border-palegreen">
                                        <ul class="avatarsList">
                                            <li class='avatarsItem'><img src='/img/avatar/scenery/0.jpg'></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <div class="avatars">
        <div>已选头像：</div>
        <img id="chooseAvatar" src='${avatar}' onerror="this.src='/assets/img/default.png'">
    </div>
</form>

<script>
    $('.avatarsItem').on('click',function(){
        var imgUrl = $(this).children('img').attr('src');
        $('#chooseAvatar').attr('src',imgUrl);
    });
</script>
