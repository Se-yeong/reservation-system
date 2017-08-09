define(["jquery", "component"], function($, Component) {
	function Scroll(){
		
	}

	Scroll.prototype = new Component();
	Scroll.prototype.constructor = Scroll;

	Scroll.prototype.scrollEnd = function(){
		var bottom = $(document).height();
		var scrollY = window.scrollY;
		var height = window.innerHeight;
		
		if( scrollY + height > bottom - 100 ) {	
			this.trigger("scrollEnd");
		}
	}
	
	return Scroll;
		
});