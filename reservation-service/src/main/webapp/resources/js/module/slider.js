define(["jquery", "component"], function($, Component){
	function Slider(root, num, size){
		this.root = root;
		this.position = 0;
		this.num = num || 3;
		this.size = size || 338;
		
		this.on("goLeft", goLeft);
		this.on("goRight", goRight);
	};
	
	Slider.prototype = new Component();
	Slider.prototype.constructor = Slider;

	
	Slider.prototype.goLeft = function() {
		position--;
		this.move();
	}
	Slider.prototype.goLeft = function() {
		position++;
		this.move();
	}

	Slider.prototype.move = function() {
		root.find(".visual_img").animate({
			"left": ( -this.position * this.size ) + "px"
		}, "slow");
	}
});