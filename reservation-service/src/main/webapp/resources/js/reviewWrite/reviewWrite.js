requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        handlebars: 'node_modules/handlebars/dist/handlebars.min',
        extend2 : "js/lib/extend.function",
        rating: "js/reviewWrite/rating",
        writeContents : "js/reviewWrite/writeContents",
        formModule : "js/reviewWrite/formModule"
    }
});

define(["jquery", "rating", "writeContents", "formModule"], function($, Rating, WriteContents, FormModule) {
	var reservaionInfo = getReservationInfo();
	var rating = new Rating( $(".ct_wrap") );
	console.log(rating);
	rating.init();

	var writeContents = new WriteContents( $(".ct_wrap") );
	writeContents.init();

	var formModule = new FormModule(reservaionInfo, rating, writeContents);

	$(".bk_btn").on("click", (function(){
		formModule.postComment();
	}).bind(this));

	formModule.on("postCommentOnload", formModule.postFile);
	
});