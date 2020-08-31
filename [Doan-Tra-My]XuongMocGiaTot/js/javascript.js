$(window).scroll(function () { 
    let header = $('header').height();
    if( $(window).scrollTop() > 20 ){
        $('header').addClass('fixed');        
    }else{
        $('header').removeClass('fixed');  
    }
});

$('.res-menu').click(function(){ 
    $(this).toggleClass('active');  
    if( $('.menu').attr('data-show') == 0 ){
        $('.menu').addClass('active'); 
        $('.menu').removeClass('noactive');
        $('.menu').attr('data-show', 1);  
    }else{
        $('.menu').addClass('noactive'); 
        
        setTimeout(function(){
            $('.menu').removeClass('active'); 
            $('.menu').attr('data-show', 0);
        }, 200)
    }
});
// Slick Banner Product
$('.banner-prod').slick({
    dots: true,
    arrow: true,
    infinite: true,
    slidesToScroll: 1,
    slidesToShow: 1
});

// Slick Hot Product
$('.slide-prd').slick({
    arrow: true,
    infinite: true,
    slidesToScroll: 1,
    slidesToShow: 4,
    responsive: [
    {
        breakpoint: 1199,
        settings: {
            slidesToShow: 3,
            slidesToScroll: 1
        }
    },
    {
        breakpoint: 991,
        settings: {
            slidesToShow: 2,
            slidesToScroll: 1
        }
    },
    {
        breakpoint: 575,
        settings: {
            slidesToShow: 1,
            slidesToScroll: 1
        }
    }
    ]
});

// Slick Partner
$('.slide-partner').slick({
    dots: false,
    arrow: false,
    infinite: false,
    slidesToShow: 6,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    responsive: [
    {
        breakpoint: 1199,
        settings: {
            slidesToShow: 5,
            slidesToScroll: 1
        }
    },
    {
        breakpoint: 991,
        settings: {
            slidesToShow: 4,
            slidesToScroll: 1
        }
    },
    {
        breakpoint: 767,
        settings: {
            slidesToShow: 3,
            slidesToScroll: 1
        }
    },
    {
        breakpoint: 575,
        settings: {
            slidesToShow: 2,
            slidesToScroll: 1
        }
    }
    ]
});