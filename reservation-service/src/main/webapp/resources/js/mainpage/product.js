define(["jquery","handlebars"], function($, Handlebars) {
	var start = 0;
	var source = $("#product-template").html();
	var template = Handlebars.compile(source);
	
	function getProductList(categoryId,amount,method){
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
		}).then(makeProductList.bind(this, method));
	}
	
	
	function makeProductList(method, data) {
		console.log(data);
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
		getProductList : getProductList
	}
});