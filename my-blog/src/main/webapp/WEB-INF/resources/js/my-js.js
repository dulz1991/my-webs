
jQuery(document).ready(function() {

    /* ---------------------------------------------- /*
     * SMOOTH SCROLL
    /* ---------------------------------------------- */    
    jQuery('.sscroll').bind('click.smoothscroll', function(e) {
        e.preventDefault();
        jQuery('.sscroll').parent().removeClass('active');
        jQuery(this).parent().addClass('active');
        jQuery('html,body').animate({
            scrollTop: jQuery(this.hash).offset().top
        }, 1200);
    });

    /* ---------------------------------------------- /*
     * RESPONSIVE VIDEO
    /* ---------------------------------------------- */    
    if(jQuery.fn.fitVids){
        jQuery('.fitvids').fitVids();
    }

    /* ---------------------------------------------- /*
     * Back To Top
    /* ---------------------------------------------- */    
    jQuery("#backtotop").backToTop();
	
});

