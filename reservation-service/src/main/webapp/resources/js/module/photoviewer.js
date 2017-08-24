define(["jquery", "handlebars", "visual", "slider"], function($, Handlebars, Visual, Slider) {
	var ajaxUrl = null;
	var $root = $("#photoviewer");
	var visual;
	var IMAGE_SIZE = 414;
	
	function init(url) {
		ajaxUrl = url;
		$root.css("display", "block");
		initVisual().then(initSlider);
		$(".photoviewer_closer").off("click");
		$(".photoviewer_closer").on("click", closeClickHandler);
	}
	
	function initVisual() {
		
		var setting  = {
				$root  : $root,
				handlebarsElement : $("#photoviewer-image-template").html()
		};		
		
		visual = new Visual(setting);
		return visual.getImagesByAjax(ajaxUrl);
	}
	
	function initSlider() {
		var setting  = {
				$root  : $root,
				length : visual.getLength(),
				size : IMAGE_SIZE,
				endFlag : true,
				autoFlag : false
		};
		
		slider = new Slider(setting);
	}
	
	function closeClickHandler(event) {
		$root.css("display", "none");
	}
	
	
	return {
		init: init
	}
	
});