(function ($) {
	"use strict";
	// counter up
	// $('.vp-count').counterUp({
	//     time: 5000
	// });
//更多精品模板：http://www.bootstrapmb.com
	$(window).on('load', function () {
		var img = $('.bg-img');
		img.css('background-image', function () {
			var bg = ('url(' + $(this).data('background') + ')');
			return bg;
		});
		// preloader
		$('.preloader').fadeOut(1000);
	});

	// menu
	$("ul>li>ul").parent("li").addClass("menu-item-has-children");
	$('.menu ul li a').on('click', function (e) {
		if (parseInt($(window).width()) < 992) {
			var element = $(this).parent('li');
			if (element.hasClass('open')) {
				element.removeClass('open');
				element.find('li').removeClass('open');
				element.find('ul').slideUp(300, "swing");
			} else {
				element.addClass('open');
				element.children('ul').slideDown(300, "swing");
				element.siblings('li').children('ul').slideUp(300, "swing");
				element.siblings('li').removeClass('open');
				element.siblings('li').find('li').removeClass('open');
				element.siblings('li').find('ul').slideUp(300, "swing");
			}
		}

	})

	$(document).ready(function () {
		$(".user-prof").click(function () {
			$(".user-content").toggleClass("open");
		});
	});

	// drop down menu width overflow problem fix
	$('ul').parent('li').hover(function () {
		var menu = $(this).find("ul");
		var menupos = $(menu).offset();
		if (menupos.left + menu.width() > $(window).width()) {
			var newpos = -$(menu).width();
			menu.css({
				left: newpos
			});
		}
	});

	// banner slider
	var swiper = new Swiper('.btbs-slider', {
		pagination: {
			el: '.btbs-slider-pagination',
			clickable: true,
		},
		loop: true,
		// autoplay: {
		// 	delay: 10000,
		// 	disableOnInteraction: false,
		// },
	});

	// laytest product slider
	var swiper = new Swiper('.lp-slider', {
		pagination: {
			el: '.lp-slider-pagination',
			clickable: true,
		},
		loop: true,
		// autoplay: {
		// 	delay: 10000,
		// 	disableOnInteraction: false,
		// },
	});

	// cate-all-menu class toggleclass
	$('.cam-menu-top').on('click', function () {
		$('.cate-all-menu').toggleClass('close');
	})



	document.addEventListener('readystatechange', event => {
		if (event.target.readyState === "complete") {
			var clockdiv = document.getElementsByClassName("date");
			var countDownDate = new Array();
			for (var i = 0; i < clockdiv.length; i++) {
				countDownDate[i] = new Array();
				countDownDate[i]['el'] = clockdiv[i];
				countDownDate[i]['time'] = new Date(clockdiv[i].getAttribute('data-date')).getTime();
				countDownDate[i]['days'] = 0;
				countDownDate[i]['hours'] = 0;
				countDownDate[i]['seconds'] = 0;
				countDownDate[i]['minutes'] = 0;
			}

			var countdownfunction = setInterval(function () {
				for (var i = 0; i < countDownDate.length; i++) {
					var now = new Date().getTime();
					var distance = countDownDate[i]['time'] - now;
					countDownDate[i]['days'] = Math.floor(distance / (1000 * 60 * 60 * 24));
					countDownDate[i]['hours'] = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
					countDownDate[i]['minutes'] = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
					countDownDate[i]['seconds'] = Math.floor((distance % (1000 * 60)) / 1000);

					if (distance < 0) {
						countDownDate[i]['el'].querySelector('.days').innerHTML = 0;
						countDownDate[i]['el'].querySelector('.hours').innerHTML = 0;
						countDownDate[i]['el'].querySelector('.minutes').innerHTML = 0;
						countDownDate[i]['el'].querySelector('.seconds').innerHTML = 0;
					} else {
						countDownDate[i]['el'].querySelector('.days').innerHTML = countDownDate[i]['days'];
						countDownDate[i]['el'].querySelector('.hours').innerHTML = countDownDate[i]['hours'];
						countDownDate[i]['el'].querySelector('.minutes').innerHTML = countDownDate[i]['minutes'];
						countDownDate[i]['el'].querySelector('.seconds').innerHTML = countDownDate[i]['seconds'];
					}
				}
			}, 1000);
		}
	});



	$('.vp-count').each(function () {
		$(this).prop('Counter', 0).animate({
			Counter: $(this).text()
		}, {
			duration: 4000,
			easing: 'swing',
			step: function (now) {
				$(this).text(Math.ceil(now));
			}
		});
	});

	// product view mode change js
	$(function () {
		$('.product-view-mode').on('click', 'a', function (e) {
			e.preventDefault();
			var shopProductWrap = $('.shop-product-wrap');
			var viewMode = $(this).data('target');
			$('.product-view-mode a').removeClass('active');
			$(this).addClass('active');
			shopProductWrap.removeClass('grid list').addClass(viewMode);
		});
	});


	// product single
	var galleryThumbs = new Swiper(".gallery-thumbs", {
		centeredSlides: true,
		centeredSlidesBounds: true,
		slidesPerView: 5,
		watchOverflow: true,
		loop: true,
		watchSlidesVisibility: true,
		watchSlidesProgress: true,
		direction: 'vertical',
		navigation: {
			nextEl: '.product-next',
			prevEl: '.product-prev',
		},
		// Responsive breakpoints   
		breakpoints: {
			// when window width is <= 640px     
			640: {
				slidesPerView: 3,
			}

		}
	});
	var galleryMain = new Swiper(".gallery-main", {
		watchOverflow: true,
		loop: true,
		watchSlidesVisibility: true,
		watchSlidesProgress: true,
		preventInteractionOnTransition: true,
		effect: 'fade',
		fadeEffect: {
			crossFade: true
		},
		thumbs: {
			swiper: galleryThumbs
		}
	});
	// style-2
	var galleryThumbs = new Swiper('.gallery-thumbs2', {
		spaceBetween: 15,
		slidesPerView: 5,
		loop: true,
		freeMode: true,
		loopedSlides: 5, //looped slides should be the same
		watchSlidesVisibility: true,
		watchSlidesProgress: true,
		// Responsive breakpoints   
		breakpoints: {
			// when window width is <= 640px     
			640: {
				slidesPerView: 3,
			}

		}
	});
	var galleryTop = new Swiper('.gallery-top', {
		spaceBetween: 0,
		loop: true,
		// loopedSlides: 5,
		navigation: {
			nextEl: '.product2-next',
			prevEl: '.product2-prev',
		},
		thumbs: {
			swiper: galleryThumbs,
		},
	});


	//  Isotope
	$(window).on('load', function () {
		var $grid = $('.grid').isotope({
			itemSelector: '.port-item',
			masonry: {
				columnWidth: 0
			}
		});
		var filterFns = {
			numberGreaterThan50: function () {
				var number = $(this).find('.number').text();
				return parseInt(number, 10) > 50;
			},
			ium: function () {
				var name = $(this).find('.name').text();
				return name.match(/ium$/);
			}
		};
		$('.port-filter').on('click', 'li', function () {
			var filterValue = $(this).attr('data-filter');
			filterValue = filterFns[filterValue] || filterValue;
			$grid.isotope({
				filter: filterValue
			});
		});
		$('.port-filter').each(function (i, buttonGroup) {
			var $buttonGroup = $(buttonGroup);
			$buttonGroup.on('click', 'li', function () {
				$buttonGroup.find('.active').removeClass('active');
				$(this).addClass('active');
			});
		});
	});

	// magnific-popup
	$('.popup-img').magnificPopup({
		type: 'image'
		// other options
	});

	// shop cart + - start here
	var CartPlusMinus = $('.cart-plus-minus');
	$(".qtybutton").on("click", function () {
		var $button = $(this);
		var oldValue = $button.parent().find("input").val();
		if ($button.text() === "+") {
			var newVal = parseFloat(oldValue) + 1;
		} else {
			if (oldValue > 0) {
				var newVal = parseFloat(oldValue) - 1;
			} else {
				newVal = 1;
			}
		}
		$button.parent().find("input").val(newVal);
	});


	//blog-slider
	var swiper = new Swiper('.post-thumb-container', {
		slidesPerView: 1,
		autoplay: {
			delay: 10000,
			disableOnInteraction: false,
		},
		navigation: {
			nextEl: '.thumb-next',
			prevEl: '.thumb-prev',
		},
		loop: true,
	});

	// team section
	$('.accordion-item').click(function () {
		$(this).addClass('active');
		$('.accordion-item').not($(this)).removeClass('active');
		$('body').attr('class', $(this).attr('class').split(' ')[1]);
	});

	// scroll up start here
	$(function () {
		//Check to see if the window is top if not then display button
		$(window).scroll(function () {
			if ($(this).scrollTop() > 300) {
				$('.scrollToTop').css({
					'bottom': '2%',
					'opacity': '1',
					'transition': 'all .5s ease'
				});
			} else {
				$('.scrollToTop').css({
					'bottom': '-30%',
					'opacity': '0',
					'transition': 'all .5s ease'
				})
			}
		});
		//Click event to scroll to top
		$('.scrollToTop').on('click', function () {
			$('html, body').animate({
				scrollTop: 0
			}, 500);
			return false;
		});
	});

	// Product slider 
	var swiper = new Swiper('.product-slider', {
		slidesPerView: 6,
		spaceBetween: 0,
		loop: true,
		breakpoints: {
			375: {
				slidesPerView: 1,
			},
			767: {
				slidesPerView: 2,
			},
			991: {
				slidesPerView: 3,
			},
			1199: {
				slidesPerView: 4,
			},
			1439: {
				slidesPerView: 5,
			}
		}
	});
	// Product slider style 2
	var swiper = new Swiper('.product-slider2', {
		slidesPerView: 5,
		spaceBetween: 15,
		loop: true,
		breakpoints: {
			375: {
				slidesPerView: 2,
			},
			767: {
				slidesPerView: 3,
			},
			991: {
				slidesPerView: 5,
			},
			1199: {
				slidesPerView: 6,
			}
		}
	});
	// Product slider style 3
	var swiper = new Swiper('.product-slider3', {
		slidesPerView: 4,
		slidesPerColumn: 2,
		spaceBetween: 0,
		loop: true,
		breakpoints: {
			375: {
				slidesPerView: 2,
			},
			767: {
				slidesPerView: 2,
			},
			991: {
				slidesPerView: 3,
			},
			1199: {
				slidesPerView: 2,
			},
			1399: {
				slidesPerView: 3,
			}
		}
	});
	// Product slider style 4
	var swiper = new Swiper('.product-slider4', {
		slidesPerView: 3,
		spaceBetween: 15,
		loop: true,
		breakpoints: {
			375: {
				slidesPerView: 2,
			},
			575: {
				slidesPerView: 3,
			},
			767: {
				slidesPerView: 4,
			},
			991: {
				slidesPerView: 3,
			},
			1199: {
				slidesPerView: 4,
			},
			1399: {
				slidesPerView: 2,
			}
		}
	});
	// Product slider style 5
	var swiper = new Swiper('.product-slider5', {
		slidesPerView: 5,
		slidesPerColumn: 2,
		spaceBetween: 0,
		loop: true,
		breakpoints: {
			375: {
				slidesPerView: 1,
				slidesPerColumn: 3,
			},
			767: {
				slidesPerView: 2,
			},
			1199: {
				slidesPerView: 3,
			},
			1399: {
				slidesPerView: 4,
			}
		}
	});
	// Product slider style 6
	var swiper = new Swiper('.product-slider6', {
		slidesPerView: 2,
		spaceBetween: 0,
		loop: true,
		breakpoints: {
			1199: {
				slidesPerView: 1,
			}
		}
	});

	//js for contact form
	$(function () {
		// Get the form.
		var form = $('#commentform');

		// Get the messages div.
		var formMessages = $('.form-message');

		// Set up an event listener for the contact form.
		$(form).submit(function (e) {
			// Stop the browser from submitting the form.
			e.preventDefault();

			// Serialize the form data.
			var formData = $(form).serialize();

			// Submit the form using AJAX.
			$.ajax({
					type: 'POST',
					url: $(form).attr('action'),
					data: formData
				})
				.done(function (response) {
					// Make sure that the formMessages div has the 'success' class.
					$(formMessages).removeClass('error');
					$(formMessages).addClass('success');

					// Set the message text.
					$(formMessages).text(response);

					// Clear the form.
					$('#contact-form input, #contact-form textarea').val('');
				})
				.fail(function (data) {
					// Make sure that the formMessages div has the 'error' class.
					$(formMessages).removeClass('success');
					$(formMessages).addClass('error');

					// Set the message text.
					if (data.responseText !== '') {
						$(formMessages).text(data.responseText);
					} else {
						$(formMessages).text('Oops! An error occured and your message could not be sent.');
					}
				});
		});
	});

}(jQuery));