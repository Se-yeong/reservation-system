requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        handlebars: 'node_modules/handlebars/dist/handlebars.min',
        count: "js/mainpage/count",
        product: "js/mainpage/product",
        scroll: "js/module/scroll",
        slider: "js/module/slider"
    }
});

define(["jquery", "count", "product", "scroll"], function($, CountModule, ProductModule, Scroll) {
	var categoryId =0;
	var scroll = new Scroll();
	var slider;

	// 이부분이 init 
	$(".tab_lst_min > .item:last > .anchor").addClass("last");
	$(".event_tab_lst > .item").on("click", categoryClick);

	// 보류 ... bind에 대하여 질문할 것. 
	$(window).on("scroll", function(){
		scroll.scrollEnd();
	}.bind(this));
	
	getData();
	scollEvnetHandler();

	function categoryClick() {
		categoryId = $(this).data("category");
		$(".anchor.active").removeClass("active");
		$(this).find(".anchor").addClass("active");
		 getData();
		 
	}
	
	function getData() {
		var amount = 4;
		CountModule.getCount(categoryId);
		ProductModule.getProduct(categoryId, amount, "html");
	}
	
	function scollEvnetHandler(){
		scroll.on("scrollEnd",function(){
			var amount = 8;
			scroll.off("scrollEnd");
			ProductModule.getProduct(categoryId, amount, "append").then(scollEvnetHandler);
		});
	
	}
	
	require(["slider"], function(Slider){
		var setting  = {
				root  : $(".section_visual"),
				length : 2,
				size : 338,
				endFlag : false,
				autoFlag : true
		};
		slider = new Slider(setting);
	});
	
});