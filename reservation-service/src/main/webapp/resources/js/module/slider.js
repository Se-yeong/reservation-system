define(["jquery", "component"], function($, Component){
	function Slider(setting) {
		this.$root = setting.$root;
		this.length = setting.length || 2;
		this.size = setting.size || 338;
		this.endFlag = setting.endFlag;
		this.autoFlag = setting.autoFlag;
		
		this.position = 1;
		this.autoInterval = 0;
		this.pauseTimeout = 0;
		
		this.init();	
	};
	
	Slider.prototype = new Component();
	Slider.prototype.constructor = Slider;
	
	Slider.prototype.init = function() {
		this.$root.find(".nxt_inn").on("click", this.goRight.bind(this));
		this.$root.find(".prev_inn").on("click", this.goLeft.bind(this));
		this.$root.find(".nxt_inn , .prev_inn").on("click", this.pause.bind(this));
		this.autoSlide();	
		this.paginate();
	}

	Slider.prototype.autoSlide = function(){
		if(this.autoFlag){
			this.autoInterval = setInterval(this.goRight.bind(this), 2000);
		}
	}
	
	Slider.prototype.pause = function(){
		clearTimeout(this.pauseTimeout);
		clearInterval(this.autoInterval);
		this.pauseTimeout = setTimeout(this.autoSlide.bind(this), 4000);
	}
	
	
	Slider.prototype.goLeft = function(e) {
		if(e instanceof Event){
			e.preventDefault();
		}
		
		if(this.position <= 1){
			if(!this.endFlag){
				this.position = this.length +1;
				this.move(0).then(this.goLeft.bind(this));
			}
		}else{
			this.position--;
			this.move("slow");
		}
	}
	
	Slider.prototype.goRight = function(e) {
		if(e instanceof Event){
			e.preventDefault();
		}
		
		if ( !(this.position === this.length && this.endFlag) ) {
			this.position++;
			this.move("slow");
		} else {
			// do nothing
		}
		if (this.position > this.length && !this.endFlag) {
			this.position = 1;
			this.move(0);
		}
	}
	
	// 
	Slider.prototype.move = function(speed, callback) {
		return this.$root.find(".visual_img").animate({
				"left": ( -(this.position-1) * this.size ) + "px"
			}, {
				duration: speed,
				start: this.updatePage.bind(this)
			}).promise();
	}
	
	Slider.prototype.paginate = function() {
		
		this.$root.find(".figure_pagination > .num.off > span").html(this.length);
		this.updatePage();
	}
	
	Slider.prototype.updatePage = function(position) {
		this.$root.find(".figure_pagination > .num:not(.off)").html(this.position);
	}
	
	return Slider;
});