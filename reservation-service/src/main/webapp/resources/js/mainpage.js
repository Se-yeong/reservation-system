/**
 * 
 */


/*
 * 1. slide 모듈은 내일.. 
 * 2. dataload 모듈 
 * 3. count 모듈 /  product 모듈 / scroll 모듈 
 * 4. main 
 * 
 */

$(function() {
	var categoryId =0;
	var scroll = new Scroll();
	scroll.on("scrollEnd",function(){
		console.log(this);
		ProductModule.getProduct(categoryId, 8, "append");
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

});


var CountModule  = (function(){
	
	function getCount(categoryId) {
		var url = "api/product/count";
		if (categoryId !== 0) {
			url = url + "/" + categoryId;
		}
		$.ajax({
			url : url,
			dataType : "json"
		}).then(showCount);
	}
	
	function showCount(data) {
		$(".event_lst_txt > .pink").text(data + "개");
	}
	return {
		getCount : getCount
	}
})();


var ProductModule = (function(){
	var start =0;
	var source = $("#product-template").html();
	var template = Handlebars.compile(source);
	
	function getProduct(categoryId,amount,method){
		var url = "api/product";
		
		if(method ==="html"){
			start =0;
		}
		
		if (categoryId !== 0) {
			url = url + "/category/" + categoryId;
		}
		
		$.ajax({
			url : url + "?start=" + start + "&amount=" + amount,
			dataType : "json"
		}).then(showProduct.bind(this, method));
	}
	
	
	function showProduct(method, data) {
		
		var left = [];
		var right =[];
		
		for (var i = 0, max = data.length; i < max; ++i) {
			if (i % 2) {
				left.push(data[i]);
			} else {
				right.push(data[i]);
			}
		}
		start += data.length;
		$(".lst_event_box:first")[method](template( {item: left} ));
		$(".lst_event_box:last")[method](template( {item: right} ));
	}
	return {
		getProduct : getProduct
	}
})();


function Scroll(){
	
}

Scroll.prototype = new eg.Component;
Scroll.prototype.constructor = Scroll;

Scroll.prototype.scrollEnd = function(){
	var bottom = $("footer").offset().top;
	var scrollY = window.scrollY;
	var height = window.innerHeight;
	
	if( scrollY + height > bottom ) {	
		console.log(this);
		this.trigger("scrollEnd");
	}
}
	


