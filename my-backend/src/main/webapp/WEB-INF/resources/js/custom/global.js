/**
 * 全局js文件
 */
var leftMenuArr=[]; /*左侧菜单json格式数据*/
$(function(){
	/*初始化页面内容部分的头部栏*/
	initPageContentHeader();
	
	$('#checkbox_fixedbreadcrumbs').remove();
	if($.common.isIE()){
		$('#fullscreen-toggler').hide();
	}
	
	/*阻止点击menu其他元素的时候隐藏行为，配合data-stopPropagation="true"使用*/
    $('.dropdown-menu').click(function(e) {
        e.stopPropagation();
    });
	/*全屏*/
	$("#fullscreen-toggler").on("click", function() {
		var n = document.documentElement;
		$("body").hasClass("full-screen") ? ($("body").removeClass("full-screen"), $("#fullscreen-toggler").removeClass("active"), document.exitFullscreen ? document.exitFullscreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.webkitExitFullscreen && document.webkitExitFullscreen()) : ($("body").addClass("full-screen"), $("#fullscreen-toggler").addClass("active"), n.requestFullscreen ? n.requestFullscreen() : n.mozRequestFullScreen ? n.mozRequestFullScreen() : n.webkitRequestFullscreen ? n.webkitRequestFullscreen() : n.msRequestFullscreen && n.msRequestFullscreen())	
	});
	/*显示/隐藏左侧导航*/
	$(".sidebar-toggler").on("click", function() {
		return $("#sidebar").toggleClass("hide"),
		$(".sidebar-toggler").toggleClass("active"), !1
	});
	
	/*左侧菜单栏定位*/
	var urlPath = $.common.getQueryString('toUrl');
	urlPath = $.common.convertBlank(urlPath, '/');
	urlPath = urlPath.replace('//','/');
	if(urlPath=='/' || urlPath=='/index'){
		$('ul.sidebar-menu li').eq(0).addClass('active');
	} else {
		$('ul.sidebar-menu li a').each(function(index,e){
			var toUrl = $(this).attr("toUrl");
			if(toUrl!=undefined){
				toUrl = toUrl.replace('//','/');	
			}
			if($.common.isNotBlank(toUrl) && urlPath.indexOf(toUrl)>-1){
				var menuIndex = $('ul.sidebar-menu li a').index(this);
				$(this).parent('li').addClass('active');
				$(this).parent('ul').css('display','block');
				
				//是否是一级菜单
				if($(this).parent('li').hasClass('levelOneMenu')){
					$(this).parent('li').addClass('active');	
				} else {
					//是否是二级菜单
					if(!$(this).parent('li').hasClass('levelTwoMenu')){
						$(this).parents('li.levelTwoMenu').addClass('open');	
					}
					$(this).parents('li.levelOneMenu').addClass('open').addClass('active');
					$(this).parents('li.levelOneMenu').find('ul.submenu').eq(0).css('display','block');
				}
				
				return;
			}
		});
	}
	
	/*从左侧菜单收集菜单json数据*/
	$('ul.sidebar-menu li a').each(function(index,e){
		var toUrl = $(this).attr("toUrl");
		if($.common.isNotBlank(toUrl)){
			var leftMenuJson = {};
			leftMenuJson.label=$.common.trim($(this).text());
			leftMenuJson.toUrl=toUrl;
			leftMenuArr.push(leftMenuJson);	
		}
	});
	
	/*菜单搜索 自动完成*/
	$('#menuAutoCompleteSearch').autocompleter({
        // marker for autocomplete matches
        highlightMatches: true,
        // object to local or url to remote search
        source: leftMenuArr,
        // custom template
        template: '{{ label }}',
        // show hint
        hint: false,
        // abort source if empty field
        empty: true,
        // max results
        limit: 10,
        callback: function (value, index, selected) {
        	if($.common.isNotBlank(selected.toUrl)){
        		gotoUrl(selected.toUrl);
        	}
        }
    });

	// 表格图片点击放大
	openImg();
	
	/*头部导航栏提醒*/
    /*getHeadTip();
    window.setTimeout("getHeadTip()",1000*60*60);*/
});

/*头部导航栏提醒*/
/*function getHeadTip(){
	$.common.get({},'/getHeadTip', function(data){
		if(data.errorNo==0){
			$('#financeLimitDateTip').text(data.financeTipCount);
		}
	});
}*/

//全选或反选
function chkAll(isChecked) {
    $(":checkbox[name$='Id']").each(function () {
        if (!$(this).is(":disabled")) {
            $(this).prop("checked", isChecked);
        }
    });
}

/**
 * 页面跳转
 * @param url
 */
function gotoUrl(url, target){
	var url = "/index?toUrl="+url;
	if($.common.isBlank(target) || target=='_blank'){
		if($.common.isIE()){
			window.open(url);
		} else {
			window.open(url,url).focus();
		}
	} else if(target=='_self'){
		location.href=url;
	}
}

/**
 * 初始化页面内容部分的头部栏
 */
function initPageContentHeader(){
	var urlPath = $.common.getQueryString('toUrl');
	var name = $('ul.sidebar-menu li a[toUrl="'+urlPath+'"]').text();
	if($.common.isBlank(name)){
		name = '';
	} else {
		document.title = name;
	}
	$('.header-title h1').text(name);
	/*var html = '';
	html += '<div class="page-header position-relative">';
	html += '	<div class="header-title"><h1>'+name+'</h1></div>';
	html += '	<div class="header-buttons">';
	html += '		<a class="sidebar-toggler" href="#"><i class="fa fa-arrows-h"></i></a>';
	html += '		<a class="refresh" id="refresh-toggler" href="#" onclick="location.reload()"><i class="glyphicon glyphicon-refresh"></i></a>';
	html += '		<a class="fullscreen" id="fullscreen-toggler" href="#"><i class="glyphicon glyphicon-fullscreen"></i></a>';
	html += '	</div>';
	html += '</div>';
	$('.page-body').before(html);*/
}

//打开窗口
function OpenWindow(url, heigh, width) {
    var windowName = "信息浏览_" + (new Date).getMilliseconds();
    var newWindow = window.open(url, windowName, 'left=200,top=100,height=' + heigh + ',width=' + width + ',menubar=no,status=no,location=no,directories=no,resizable=yes,scrollbars=yes');
    newWindow.focus();
}

/*左侧菜单搜索*/
function showMoreSearchForm(obj){
	/*status 1展开 0收缩*/
	var elem = '#'+obj+' .form-group';
	if($('#'+obj).find('#formAreaShowMoreText').attr('status')==1){
		$('#'+obj).find('#formAreaShowMoreText').attr('status','0');
		$('#'+obj).find('#formAreaShowMoreText').text('更多>>')
		$(elem).each(function(index){
			if(index<3) {
				return;
			} 
			if(index==$(elem).length-1){
				return;
			}
			$(this).hide();
		});
	} else {
		$('#'+obj).find('#formAreaShowMoreText').attr('status','1');
		$(elem).show();
		$('#'+obj).find('#formAreaShowMoreText').text('收起')
	}
}

/**
 * 修改头像
 */
function updateAvatar(){
    var loadUrl = '/selectAvatar';
    var param = {title:'修改头像',width:'800px',height:'500px',loadUrl:loadUrl};
    $.pop.dialog(param,'',function(){
        var imgUrl = $('#chooseAvatar').attr('src');
        $.common.postSync(imgUrl, '/saveAvatar?avatar='+imgUrl, function(res){
            if(res.errorNo!=0){
                $.pop.error(res.errorInfo);
            }else{
                $.pop.success('修改成功！');
                layer.closeAll();
                $('#smAvatar').attr('src',imgUrl);
                $('#lgAvatar').attr('src',imgUrl);
            }
        });
    })
}

// 表格图片点击放大
function openImg() {
    $('.page-body').on('click','img',function (e) {
        if($(e.target).parent('td').length > 0){ //判断是否是表格里的图片
            var imgUrl = $(e.target.outerHTML).attr('src').split('!')[0];
            layer.photos({
                shade: [0.3, '#000'],
                success:function(){
                    $('.layui-layer-imgsee').css('display','none');
                },
                photos: {
                    "title": "", //相册标题
                    "data": [ //相册包含的图片，数组格式
                        {"src": imgUrl}
                    ]
                },
                anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            });
        }
    })
}


/*退出*/
function logout(){
	$.common.post({}, '/logout', function(data){
		if(data.errorNo==0){
			self.location='/login';
		} else {
			$.pop.warning("退出登录失败，请重试");
		}
	});
}

/*修改密码*/
function changePwd(){
	var parm={};
	parm.width='300px';
	parm.height='300px';
	parm.loadUrl = '/changePwd';
	$.pop.dialog(parm, changePwdVerify, function(data){
		$.pop.success('密码修改成功');
	});
}
function changePwdVerify(){
	var formData = $.common.getFormJson('#changePwdForm');
	var rst = '';
	$.common.postSync(formData, '/doChangePwd', function(data){
		if(data.errorNo!=0){
			rst = data.errorInfo;
		}
	});
	return rst;
}


/*ztree下拉选择*/
function showZtreeMenu(obj){
	var obj = $(obj);
	var offset = obj.offset();
	$("#menuContent").css({left:offset.left + "px", top:offset.top + "px"}).slideDown();
	$("body").bind("mousedown", onBodyDown);
}
function hideZtreeMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideZtreeMenu();
	}
}

