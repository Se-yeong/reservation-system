define(["jquery"], function($) {
	
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

});