requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        handlebars: 'node_modules/handlebars/dist/handlebars.min',
        slider: "js/module/slider",
        visual : "js/module/visual",
        extend2 : "js/lib/extend.function"
    }
});

define(["jquery","visual"], function($,Visual) {
	
	var product = getEl();
	
	
	var setting  = {
			$root  : $(".section_visual"),
			handlebarsElement : $("#image-template").html()
	};
	
	var visual = new Visual(setting);
	
	console.log(visual);
	visual.getImagesByEl(product);

	
});