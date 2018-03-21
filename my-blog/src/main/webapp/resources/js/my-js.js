jQuery(document).ready(function() {
	/**
	 * 页面预加载动画
	 */
    $(window).load(function () {
        $('#status').fadeOut();
        $('#preloader').delay(300).fadeOut('slow');
    });
    
    /**
     * 点击返回页面顶部
     */
    jQuery("#backtotop").backToTop();
    jQuery('.sscroll').bind('click.smoothscroll', function(e) {
        e.preventDefault();
        jQuery('.sscroll').parent().removeClass('active');
        jQuery(this).parent().addClass('active');
        jQuery('html,body').animate({
            scrollTop: jQuery(this.hash).offset().top
        }, 1200);
    });

    /**
     * blog首页 左侧折叠效果
     */
    $(".menu h3").click(function(){
 		//找menu对应的tab
 		/*$(".menu_tab > div").removeClass("active");*/

 		var val=($(this).next().attr('class'));
 		var menu_value=(val.substring(val.length-1));
 		$(".container .content .menu"+menu_value+" .tab:first-child").addClass("active");
 		$(".container .menu .ulmenu"+menu_value+" li>a").removeClass("selected");
 		$(".container .menu .ulmenu"+menu_value+" li a").eq(0).addClass("selected");//默认设置为被选中状态

 		// $("."+"val").child().child().("selected")
 		
 			//这是ul收缩效果
            $(this).next().stop().slideToggle();
            $(this).siblings().next("ul").stop().slideUp();
			
           // if($(".container .ulmenu"+menu_value+" li ").find("a").attr("class")==="selected"){
           // 		$(".container .content .menu"+menu_value+" .tab:first-child")
           // }
        });
    
});

(function($) {
	/**
	 * 点击返回页面头部
	 */
    $.fn.backToTop = function() {
        var selectz = $(this);
        selectz.hide();
        $(window).scroll(function() {
        	/*if ($(this).scrollTop() > $(window).height()) {*/
            if ($(this).scrollTop() > 0) {
                selectz.fadeIn();
            } else {
                selectz.fadeOut();
            }
        });
    };
    
   
}(jQuery));
