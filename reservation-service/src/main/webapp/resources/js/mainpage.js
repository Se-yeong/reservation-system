requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        handlebars: 'node_modules/handlebars/dist/handlebars.min',
        count: "js/module/count",
        product: "js/module/product",
        scroll: "js/module/scroll",
        slider: "js/module/slider"
    }
});

define(["jquery", "count", "product", "scroll"], function($, CountModule, ProductModule, Scroll) {
	var categoryId =0;
	var scroll = new Scroll();
	var slider;
	scroll.on("scrollEnd",function(){
		var amount = 8;
		ProductModule.getProduct(categoryId, amount, "append");
	});

	// 이부분이 init 
	$(".tab_lst_min > .item:last > .anchor").addClass("last");
	$(".event_tab_lst > .item").on("click", categoryClick);

	// 보류 ... bind에 대하여 질문할 것. 
	$(window).on("scroll", function(){
		scroll.scrollEnd();
	}.bind(this));
	
	getData();
	
	function categoryClick() {
		var $this = $(this);
		categoryId = $this.data("category");
		$(".anchor.active").removeClass("active");
		$this.find(".anchor").addClass("active");
		 getData();
		 
	}
	
	function getData() {
		var amount = 4;
		CountModule.getCount(categoryId);
		ProductModule.getProduct(categoryId, amount, "html");
	}
	
	require(["slider"], function(Slider){
		
		slider = new Slider($(".section_visual"), 3, 338,true);
	});

});






