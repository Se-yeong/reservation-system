define(["jquery", "component"], function($, Component){
	function Slider(root, num, size,endFlag){
		this.root = root;
		this.position = 1;
		this.num = num || 3;
		this.size = size || 338;
		this.endFlag = endFlag || true;
		
		this.root.find(".nxt_inn").on("click", this.goRight.bind(this));
		this.root.find(".prev_inn").on("click", this.goLeft.bind(this));
	};
	
	Slider.prototype = new Component();
	Slider.prototype.constructor = Slider;

	
	Slider.prototype.goLeft = function() {
		if(this.position <= 1){
			console.log(this.position);
			if(!this.endFlag){
				// 순환
			}
		}else{
			this.position--;
		}
		this.move();
	}
	Slider.prototype.goRight = function() {
		if(this.position >= this.num){
			if(!this.endFlag){
				// 순환
			}
		}else{
			this.position++;
		}
		this.move();
	}

	Slider.prototype.move = function() {
		this.root.find(".visual_img").animate({
			"left": ( -(this.position-1) * this.size ) + "px"
		}, "slow");
	}
	
	return Slider;
});