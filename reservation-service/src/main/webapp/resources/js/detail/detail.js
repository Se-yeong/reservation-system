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

define(["jquery"], function($) {
	var visual;
	
	require(["visual"], function(Visual){
		var setting  = {
				root  : $(".section_visual"),
				url : "",
				handlebarsElement : $("#image-template").html()
		};
		visual = new Visual();
		console.log(Visual);
		console.log(visual);
	});
	
});