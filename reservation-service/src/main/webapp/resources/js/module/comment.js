define(["jquery", "handlebars", "visual", "photoviewer"], function($, Handlebars, Visual, Photoviewer) {
	var start = 0;
	var source = $("#comment-template").html();
	var template = Handlebars.compile(source);
	
	function getCommentList(productId, amount){
		var url = "/api/comment?productId=" + productId + "&start=" + start + "&amount=" + amount ;
		return $.ajax({
			url : url,
			dataType : "json"
		}).then(makeCommentList);
	}
	
	function makeCommentList(data) {
		start += data.length;
		data = data.map(convertData);
		
		$(".list_short_review").append(template( {comment: data} ));
		
		initThumb();
	}
	
	function convertData(comment) {
		comment.createDate = timeConverter(comment.createDate);
		comment.modifyDate = timeConverter(comment.modifyDate);
		
		if(comment.firstImageSaveFileName == null) {
			comment.displayOption = "invisible";
		}
		return comment;
	}
	
	function initThumb() {
		$(".invisible").hide();
		$(".section_review_list .thumb").on("click", thumbClickHandler);
	}
	
	function thumbClickHandler(event) {
		event.preventDefault();
		var obj = $(event.currentTarget);
		
		var id = obj.closest(".list_item").data("id");		
		var url = "/api/comment/" + id + "/image";
		
		Photoviewer.init(url);
	}
	
	function timeConverter(timestamp){
		  var a = new Date(timestamp);
		  var year = a.getFullYear();
		  var month = a.getMonth()+1;
		  var date = a.getDate();
		  var time = year + '-' + month + '-' + date;
		  return time;
	}
	
	return {
		getCommentList : getCommentList
	}
	
});