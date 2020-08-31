var swiper = new Swiper('.swiper-container', {
      slidesPerView: 1,
      autoplay: true,
      autoplaySpeed: 2000,

      breakpoints: {
        640: {
          slidesPerView: 5,
          spaceBetween: 20,
        },
        768: {
          slidesPerView: 6,
          spaceBetween: 40,
        },
        1024: {
          slidesPerView: 6,
          spaceBetween: 55,
        },
      }
    });