(function($) {
	/*进入页面预加载动画*/
	$(window).load(function () {
        $('#status').fadeOut();
        $('#preloader').delay(300).fadeOut('slow');
    });
	
	/*根据某个值选中相应条目*/
	$.fn.itemSelect = function(options) {
		var args = $.extend({
			elem : '',
			property : '',
			compare : '',
			backFn : function() {
			}
		}, options);
		if (args.elem == '' || args.property == '' || args.compare == '') {
			return;
		}
		$(args.elem).removeClass('active');
		$(args.elem).each(function (index, domElem){
			var objAttr = $(args.elem).eq(index).attr(args.property);
			if(objAttr != undefined && objAttr  == args.compare){
				$(args.elem).eq(index).addClass('active');
				return;
			}
		});
	}
	
	$.fn.onSelect = function(options) {
		var args = $.extend({
			compare : '-1',
			backFn : function() {
			}
		}, options);
		$(this).children('option[value="'+args.compare+'"]').attr("selected","selected");
	}

	 /*回到顶部按钮*/
	 $.fn.backToTop = function() {
        var selectz = $(this);
        selectz.hide();
        $(window).scroll(function() {
            if ($(this).scrollTop() > $(window).height()/5) {
                selectz.fadeIn();
            } else {
                selectz.fadeOut();
            }
        });
    };
	
}(jQuery));


