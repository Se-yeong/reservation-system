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

define(["jquery", "comment"], function($, Comment) {
	var product = getProductByEl();
	initComment();
	
	function initComment() {
		Comment.getCommentList(product.id, 15);
	}
	
});