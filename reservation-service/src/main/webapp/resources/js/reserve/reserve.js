/**
 * 
 */

requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        ticket : "js/reserve/ticket",
        preservator : "js/reserve/preservator",
        terms : "js/reserve/terms",
        extend : "js/lib/extend"
    }
});

define(["jquery","ticket","preservator","terms"], function($,Ticket,Preservator,Terms) {
	var preservator = new Preservator($(".section_booking_form"));
	
	var ticketList = $(".qty").map(function(i,v){
		var ticket = new Ticket($(v));
		ticket.on("changeAmount",preservator.updateAmount.bind(preservator));
		return ticket;
	});
	
	var terms = new Terms();
	
	
	
});



