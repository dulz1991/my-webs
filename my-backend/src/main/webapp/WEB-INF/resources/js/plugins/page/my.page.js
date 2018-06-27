(function($){
	var zp = {
		init:function(obj,pageinit){
			return (function(){
				zp.addhtml(obj,pageinit);
				zp.bindEvent(obj,pageinit);
			}());
		},
		addhtml:function(obj,pageinit){
			return (function(){
				obj.empty();
				pageinit.pageSize = pageinit.shownum; 
				if (pageinit.shownum<1) {
					pageinit.shownum = 1;
				}
				if (pageinit.pageNum<pageinit.shownum) {
					pageinit.shownum = pageinit.pageNum;
				}
				var numshow = pageinit.shownum-4;
				var numbefore = parseInt((pageinit.shownum - 5)/2);
				if ((pageinit.shownum - 5)%2>0) {
					var numafter = numbefore + 1;
				} else{
					var numafter = numbefore;
				}
				/*上一页*/
				if (pageinit.current > 1) {
					obj.append('<a href="javascript:;" class="prebtn"  onclick="'+pageinit.turnPageFunc+'('+(parseInt(pageinit.current)-1)+')">上一页</a>');
				} else{
					obj.remove('.prevPage');
					obj.append('<span class="disabled">上一页</span>');
				}
				/*中间页*/
				if (pageinit.current >4 && pageinit.pageNum > pageinit.shownum) {
					obj.append('<a href="javascript:;" class="zxfPagenum" onclick="'+pageinit.turnPageFunc+'('+1+')">'+1+'</a>');
					obj.append('<a href="javascript:;" class="zxfPagenum" onclick="'+pageinit.turnPageFunc+'('+2+')">'+2+'</a>');
					obj.append('<span>...</span>');
				}
				if (pageinit.current >4 && pageinit.current < pageinit.pageNum-numshow && pageinit.pageNum > pageinit.shownum) {
					var start  = pageinit.current - numbefore,end = pageinit.current + numafter;
				}else if(pageinit.current >4 && pageinit.current >= pageinit.pageNum-numshow && pageinit.pageNum > pageinit.shownum){
					var start  = pageinit.pageNum - numshow,end = pageinit.pageNum;
				}else{
					if (pageinit.pageNum <= pageinit.shownum) {
						var start = 1,end = pageinit.shownum;
					} else{
						var start = 1,end = pageinit.shownum-1;
					}
				}
				var countNum = 0;
				for (;start <= end;start++) {
					countNum++;
					if(countNum>4){
						continue;
					}
					/*if(start>3){
						continue;
					}*/
					if (start <= pageinit.pageNum && start >=1) {
						if (start == pageinit.current) {
							obj.append('<span class="zxfPagenum '+pageinit.activepage+'">'+ start +'</span>');
						} else if(start == pageinit.current+1){
							obj.append('<a href="javascript:;" class="zxfPagenum '+pageinit.activepaf+'" onclick="'+pageinit.turnPageFunc+'('+start+')">'+ start +'</a>');
						}else{
							obj.append('<a href="javascript:;" class="zxfPagenum" onclick="'+pageinit.turnPageFunc+'('+start+')">'+ start +'</a>');
						}
					}
				}
				if (end < pageinit.pageNum) {
					obj.append('<span>...</span>');
				}
				/*下一页*/
				if (pageinit.current >= pageinit.pageNum) {
					obj.remove('.nextbtn');
					obj.append('<span class="disabled">下一页</span>');
				} else{
					obj.append('<a href="javascript:;" class="nextbtn" onclick="'+pageinit.turnPageFunc+'('+(parseInt(pageinit.current)+1)+')">下一页</a>');
				}
				/*尾部*/
				obj.append('<span>'+'共'+'<b>'+pageinit.totalNum+'</b>'+'条数据，'+'</span>');
				obj.append('<span>'+'到第'+'<input type="text" class="zxfinput '+pageinit.inputPageNo+'" onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if ((! /^[0-9]+$/ig.test(this.value)) && this.value.length>0){this.value=\'1\';}}" value="'+pageinit.current+'"/>'+'页，'+'</span>');
				obj.append('<span>'+'共'+'<b>'+pageinit.pageNum+'</b>'+'页，'+'</span>');
				
				obj.append('<span>'+'每页'+'<input type="text" class="zxfinput '+pageinit.inputPageSize+'" onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if ((! /^[0-9]+$/ig.test(this.value)) && this.value.length>0){this.value=\'10\';}}" value="'+pageinit.pageSize+'"/>条'+'</span>');
				obj.append('<span class="zxfokbtn" onclick="'+pageinit.turnPageFunc+'(-1)">'+'确定'+'</span>');
			}());
		},
		bindEvent:function(obj,pageinit){
			return (function(){
				obj.on("click","a.prebtn",function(){
					var cur = parseInt(obj.children("span.current").text());
					var current = $.extend(pageinit, {"current":cur-1});
					zp.addhtml(obj,current);
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}
				});
				obj.on("click","a.zxfPagenum",function(){
					var cur = parseInt($(this).text());
					var current = $.extend(pageinit, {"current":cur});
					zp.addhtml(obj,current);
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}
				});
				obj.on("click","a.nextbtn",function(){
					var cur = parseInt(obj.children("span.current").text());
					var current = $.extend(pageinit, {"current":cur+1});
					zp.addhtml(obj,current);
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}
				});
				obj.on("click","span.zxfokbtn",function(){
					var cur = parseInt($("input.zxfinput").val());
					var current = $.extend(pageinit, {"current":cur});
					zp.addhtml(obj,current);
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}
				});
				obj.on("keydown","input.zxfinput",function(){
					if (event.keyCode == "13") {
						$(".zxfokbtn").click();
					}
				});
			}());
		}
	}
	$.fn.createPage = function(options){
		var pageinit = $.extend({
			totalNum: 0,//总数据量
			pageNum : 0, //页数
			current : 1, //当前页码
			shownum: 10, //每页条数
			pageSize: 10, //每页条数
			activepage: "current",
			activepaf: "nextpage",
			turnPageFunc: "turnPage",
			inputPageNo : 'zxfinputPageNo',
			inputPageSize : 'zxfinputPageSize',
			backfun : function(){}
		},options);
		zp.init(this,pageinit);
	}
}(jQuery));