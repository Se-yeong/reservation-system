define(["jquery", "handlebars", "visual"], function($, Handlebars, Visual) {
	var start = 0;
	var source = $("#comment-template").html();
	var template = Handlebars.compile(source);
	
	function getCommentList(productId, amount){
		var url = "api/comment" + "?start=" + start + "&amount=" + amount ;

		return $.ajax({
			url : url,
			dataType : "json"
		}).then(makeProductList.bind(this, method));
	}
	
	function makeCommentList(data) {
		start += data.length;
		$(".lst_event_box:first").append(template( {item: data} ));
	}
	return {
		getCommentList : getCommentList
	}
});