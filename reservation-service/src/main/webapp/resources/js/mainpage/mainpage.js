requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        handlebars: 'node_modules/handlebars/dist/handlebars.min',
        count: "js/mainpage/count",
        product: "js/mainpage/product",
        scroll: "js/module/scroll",
        slider: "js/module/slider",
        extend2 : "js/lib/extend.function"
    }
});

define(["jquery", "count", "product", "scroll", "slider"], function($, Count, Product, Scroll, Slider) {
	var categoryId =0;
	var scroll = new Scroll();
	var slider;

	init();
	
	
	
	function init() {
		$(".tab_lst_min > .item:last > .anchor").addClass("last");
		$(".event_tab_lst > .item").on("click", categoryClick);
		
		$(window).on("scroll", function(){
			scroll.scrollEnd();
		});
		
		getData();
		scollEvnetHandler();
		initSlider();
	}
	
	
	
	function categoryClick() {
		categoryId = $(this).data("category");
		$(".anchor.active").removeClass("active");
		$(this).find(".anchor").addClass("active");
		 getData();
		 
	}
	
	function getData() {
		var amount = 4;
		Count.getCount(categoryId);
		Product.getProductList(categoryId, amount, "html");
	}
	
	function scollEvnetHandler(){
		scroll.on("scrollEnd",function(){
			var amount = 8;
			scroll.off("scrollEnd");
			Product.getProductList(categoryId, amount, "append").then(scollEvnetHandler);
		});
	
	}
	
	function initSlider() {
		var setting  = {
				$root  : $(".section_visual"),
				length : 2,
				size : 338,
				endFlag : false,
				autoFlag : true
		};
		slider = new Slider(setting);
	}
	
});