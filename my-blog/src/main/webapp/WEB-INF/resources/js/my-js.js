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

