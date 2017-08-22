requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        handlebars: 'node_modules/handlebars/dist/handlebars.min',
        slider: "js/module/slider",
        visual : "js/module/visual",
        extend2 : "js/lib/extend.function",
        comment : "js/module/comment",
        photoviewer : "js/module/photoviewer"
    }
});

define(["jquery", "visual", "slider", "comment"], function($, Visual, Slider, Comment) {
	
	var IMAGE_SIZE = 414;
	
	var product = getProductByEl();
	var visual;
	var slider;
	
	initVisual().then(initSlider);
	initComment();
	
	function initVisual() {
		var setting  = {
				$root  : $(".section_visual"),
				handlebarsElement : $("#image-template").html()
		};
		
		visual = new Visual(setting);
		
		return visual.getImages(product);
	}
	
	function initSlider() {
		var setting  = {
				$root  : $(".section_visual"),
				length : visual.getLength(),
				size : IMAGE_SIZE,
				endFlag : true,
				autoFlag : false
		};
		
		slider = new Slider(setting);
	}
	
	function initComment() {
		Comment.getCommentList(product.id, 3);
	}
	
	

});