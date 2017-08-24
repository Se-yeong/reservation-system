define(["jquery", "component", "rating", "writeContents"], function($, Component, Rating, WriteContents) {
	
	function FormModule(reservationInfo, ratingComponent, commentComponent)
	{
		this.reservationInfo = reservationInfo;
		this.ratingComponent = ratingComponent;
		this.commentComponent = commentComponent;
	}
	FormModule.prototype = new Component();
	FormModule.prototype.constructer = FormModule;


	FormModule.prototype.postComment = function() {

		var reservationInfo = getReservationInfo();

		var score = this.ratingComponent.getScore();
		var comment = this.commentComponent.getText();

		var commentLength = comment.length;

		if(commentLength < 5) {
			alert("코멘트를 5자 이상 입력해주요.");
			return;
		}

		var data = new FormData();

		data.append("productId", reservationInfo.productId);
		data.append("userId", reservationInfo.userId);
		data.append("score", score);
		data.append("comment", comment);

		var url = "/api/comment";

		var request = new XMLHttpRequest();
		request.open("POST", url);
		request.send(data);

		request.onload = (function(event) {
			console.log(event);
			this.trigger("postCommentOnload", event);
		}).bind(this);

	}

	FormModule.prototype.postFile = function(prevOnloadEvent) {
		console.log("postFile:");
		console.log(prevOnloadEvent);

		if(this.commentComponent.fileListlengh <= 0) {
			console.log("there is no file.");
			location.href = "/product/"+getReservationInfo().productId+"/review";
			return;
		}

		var commentId = prevOnloadEvent.currentTarget.response;
		if(commentId == null) {
			return;
		}

		var fileData = this.commentComponent.getFileData();
		var files = fileData.files;
		//var dataUrls = fileData.dataUrls;
		var formData = new FormData();

		formData.append("commentId", commentId);

		for (var i=0; i < files.length ; i++) {
			console.log(files[i]);
			formData.append("files", files[i]);
		}


		var url = "/api/file/image";

		$.ajax({
	        url: url,
	        type: "POST",
	        xhr: function() {
	            var myXhr = $.ajaxSettings.xhr();
	            return myXhr;
	        },
	        data: formData,
	        cache: false,
	        contentType: false,
	        processData: false
	    }).done(function(){
	    	location.href =  "/product/"+getReservationInfo().productId+"/review";
	    });
	}
	
	return FormModule;
});
