define(["jquery", "component", "extend2", "handlebars"], function($, Component, extend, Handlebars) {
	function WriteContents(root) {
		this.root = root;
		this.fileList = new Array();
		this.resultList = new Array();
		this.index = 0;
		this.fileListlengh = 0;
	}
	WriteContents.prototype.constructer = WriteContents;

	WriteContents.prototype.init = function() {

		this.root.on("click", ".review_contents", this.textareaFocusOn.bind(this));
		this.root.on("focusout", ".review_textarea", this.textareaFocusOut.bind(this));
		this.root.on("keyup", this.lengthUpdate.bind(this));
		this.fileUpload();
	}



	WriteContents.prototype.textareaFocusOn  = function(e) {
		e.preventDefault();
		this.root.find(".review_write_info").hide();
		this.root.find(".review_textarea").focus();

	}

	WriteContents.prototype.textareaFocusOut  = function(e) {
		e.preventDefault();
		var text = this.root.find(".review_textarea").val();
		if(text == null || text.length == 0 ){
			this.root.find(".review_write_info").show();
		}

	}

	WriteContents.prototype.lengthUpdate = function(e) {
		e.preventDefault();
		var length = this.root.find(".review_textarea").val().length;
		this.root.find(".guide_review span:first-child").html(length);
	}


	WriteContents.prototype.fileUpload = function() {
		var fileBlock = this.root.find("#reviewImageFileOpenInput");

		this.root.find(".review_photos").on("click", ".spr_book.ico_del", deleteImage.bind(this));

		fileBlock.change( (function(fileBlock) { // this: WriteContents
			var files = fileBlock.prop("files");

			for(var i=0; i < files.length; i++) {

				if( this.fileListlengh + i >= 5) {
					console.log("max image quantity is 5.")
					break;
				}

				var file = files[i];
			    var reader = new FileReader();
			    reader.readAsDataURL(file);

			    reader.onload = addImage.bind(this, file);
			}

		}).bind(this, fileBlock));


		function addImage(file, event) {
			const MAX_FILE_SIZE = 1024 * 1024;

	    	var reader = event.currentTarget;
	    	var result = reader.result;
	    	if(file.size > MAX_FILE_SIZE) {
	    		console.log("file size is too big.");
	    		return;
	    	}

	    	var source = $("#review_photos_template").html();
			var template = Handlebars.compile(source);
			var data = {
					index : this.index,
					imgSrc : result,
					imgAlt : file.name
			}
			var html = template(data);
			this.root.find(".lst_thumb").append(html);

			this.fileList.push(file);
			this.resultList.push(result)

			this.index++;
			this.fileListlengh++;
			console.log(this.fileList);
		}


		function deleteImage(e) {
			e.preventDefault();
			var index = $(e.currentTarget).parent().parent().data("index");
			this.root.find(".review_photos .item[data-index='"+index+"']").remove();
			this.fileList[index] = null;
			this.resultList[index] = null;
			console.log(this.fileList);
			console.log(this.resultList);
			this.fileListlengh--;
		}
	}	// WriteContents.prototype.fileUpload ends.


	WriteContents.prototype.getText = function() {
		return this.root.find(".review_textarea").val();
	}


	WriteContents.prototype.getFileData = function() {

		var files = new Array();
		var dataUrls = new Array();

		for (var i=0; i < this.fileList.length; i++) {
			var file = this.fileList[i];
			var dataUrl = this.resultList[i];

			if(file != null && dataUrl != null){
				files.push(file);
				dataUrls.push(dataUrl);
			}
		}
		var fileData = {files: files, dataUrls: dataUrls};
		console.log(fileData);
		return fileData;
	}
	
	var writeContents = extend(Component, WriteContents.prototype);

	
	return writeContents;

});