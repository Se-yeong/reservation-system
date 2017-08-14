define(["jquery","handlebars"], function($, Handlebars) {
	var start = 0;
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
		
		return $.ajax({
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
});