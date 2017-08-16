define(["jquery", "component", "extend2"], function($, Component, extend) {
	function Scroll(){
		
	}

	Scroll.prototype.constructor = Scroll;

	Scroll.prototype.scrollEnd = function(){
		var bottom = $(document).height();
		var scrollY = window.scrollY;
		var height = window.innerHeight;
		
		if( scrollY + height > bottom - 100 ) {	
			this.trigger("scrollEnd");
		}
	}
	
	return extend(Component, Scroll.prototype);
		
});