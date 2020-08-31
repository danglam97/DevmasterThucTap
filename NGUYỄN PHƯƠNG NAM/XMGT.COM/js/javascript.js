$(window).scroll(function () { 
    let header = $('header').height();
    if( $(window).scrollTop() > 20 ){
        $('header').addClass('fixed');        
    }else{
        $('header').removeClass('fixed');  
    }
});

// Slick Banner Product
$('.banner-prod').slick({
    dots: true,
    arrow: true,
    infinite: true,
    slidesToScroll: 1,
    slidesToShow: 1,
    prevArrow: '<div class="slick-prev"><i class="fa fa-chevron-left"></i></div>',
    nextArrow: '<div class="slick-next"><i class="fa fa-chevron-right"></i></div>'
});

// Slick Hot Product
$('.slide-prd').slick({
    arrow: true,
    infinite: true,
    slidesToScroll: 1,
    slidesToShow: 4,
    prevArrow: '<div class="slick-prev"><i class="fa fa-chevron-left"></i></div>',
    nextArrow: '<div class="slick-next"><i class="fa fa-chevron-right"></i></div>'
});
