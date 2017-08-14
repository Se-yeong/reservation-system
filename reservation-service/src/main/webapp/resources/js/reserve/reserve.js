/**
 * 
 */

requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        ticket : "js/reserve/ticket",
        ticket2 : "js/reserve/ticket.function",
        preservator : "js/reserve/preservator",
        extend : "js/lib/extend",
        extend2 : "js/lib/extend.function"
        
    }
});

define(["jquery","ticket","preservator"], function($,Ticket,Preservator) {
	var preservator = new Preservator($(".section_booking_form"));
	
	var ticketList = $(".qty").map(function(i,v){
		var ticket = new Ticket($(v));
		ticket.on("changeAmount",preservator.updateAmount.bind(preservator));
		return ticket;
	});
	
});



