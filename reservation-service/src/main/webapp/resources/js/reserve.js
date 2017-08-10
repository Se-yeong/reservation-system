/**
 * 
 */

requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        ticket : "js/module/ticket",
        preservator : "js/module/preservator",
        extend : "js/lib/extend"
    }
});

define(["jquery","ticket","preservator"], function($,Ticket,Preservator) {
	var preservator = new Preservator($(".section_booking_form"));
	
	var ticket = $(".qty").map(function(i,v){
		var ticket = new Ticket($(v));
		ticket.on("changeAmount",preservator.updateAmount.bind(preservator));
		return ticket;
	});
	
});



