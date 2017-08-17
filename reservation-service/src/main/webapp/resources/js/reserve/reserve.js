/**
 * 
 */

requirejs.config({
	baseUrl: '/resources',
    paths: {
        jquery: 'node_modules/jquery/dist/jquery.min',
        component: 'node_modules/@egjs/component/dist/component.min',
        ticket : "js/reserve/ticket",
        enrollment : "js/reserve/enrollment",
        terms : "js/reserve/terms",
        extend : "js/lib/extend"
    }
});

define(["jquery","ticket","enrollment","terms"], function($,Ticket,Enrollment,Terms) {
	var enrollment = new Enrollment($(".section_booking_form"));
	// preservator 부분 terms 로 분리할까 ? 
	
	var ticketList = $(".qty").map(function(i,v){
		var ticket = new Ticket($(v),$(v).data("type"));
		ticket.on("changeAmount",enrollment.updateAmount.bind(enrollment));
		return ticket;
	});
	
	var terms = new Terms();
	terms.on("send",enrollment.sendData.bind(enrollment));
	
	
	
});



