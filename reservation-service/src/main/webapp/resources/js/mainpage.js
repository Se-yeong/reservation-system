/**
 * 
 */

$(function() {
	var categoryId = 0;
	var current =0;

	$(".tab_lst_min > .item:last > .anchor").addClass("last");
	$(".event_tab_lst > .item").on("click", categoryClick);
	$(window).on("scroll", scroll);
	getData();
	
	function categoryClick() {
		var $this = $(this);
		categoryId = $this.data("category");
		$(".anchor.active").removeClass("active");
		$this.find(".anchor").addClass("active");
		 getData();
	}
	
	function getData() {
		var start = 0, amount = 4;
		getCount();
		getProduct(start, amount, "html");
		
	}
	
	function getCount() {
		var url = "api/product/count";
		if (categoryId !== 0) {
			url = url + "/" + categoryId;
		}
		$.ajax({
			url : url,
			dataType : "json"
		}).then(showCount);
	}
	
	function getProduct(start, amount, method) {
		
		var url = "api/product";
		if (categoryId !== 0) {
			url = url + "/category/" + categoryId;
		}
		
		$.ajax({
			url : url + "?start=" + start + "&amount=" + amount,
			dataType : "json"
		}).then(showProduct.bind(this, method));
	}
	
	function showCount(data) {
		$(".event_lst_txt > .pink").text(data + "개");
	}
	
	function showProduct(method, data) {
		var source = $("#product-template").html();
		var template = Handlebars.compile(source);
		
		var left = [];
		var right =[];
		
		for (var i = 0, max = data.length; i < max; ++i) {
			if (i % 2) {
				left.push(data[i]);
			} else {
				right.push(data[i]);
			}
		}
		current += data.length;
		console.log(data);
		$(".lst_event_box:first")[method](template( {item: left} ));
		$(".lst_event_box:last")[method](template( {item: right} ));
	}
	

	function scroll(event) {
		var bottom = $("footer").offset().top;
		var scrollY = window.scrollY;
		var height = window.innerHeight;
		
		if( scrollY + height > bottom ) {
			getProduct(current, 8, "append");
		}
		
	}
	

});
