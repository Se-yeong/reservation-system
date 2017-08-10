define(["jquery", "component"], function($, Component){
	function Slider(root, length, size,endFlag){
		this.root = root;
		this.position = 1;
		this.length = length || 2;
		this.size = size || 338;
		this.endFlag = endFlag;
		
		this.root.find(".nxt_inn").on("click", this.goRight.bind(this));
		this.root.find(".prev_inn").on("click", this.goLeft.bind(this));
	};
	
	Slider.prototype = new Component();
	Slider.prototype.constructor = Slider;

	
	Slider.prototype.goLeft = function() {
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
	
	Slider.prototype.goRight = function() {
		// 1. 아래 코드 중 주석처리된 부분과 아닌 부분의 두 코드 중 어떤 코드가 더 좋을까요??
		// 2. 아래 setTimeout을 안써도 정상적으로 실행이 되던데 animation이 비동기라면 정상적으로 실행되면 안되는것 같습니다. 왜 정상적으로 실행이 될까요?

/*		if ( !(this.position === this.length && this.endFlag) ) {
			this.position++;
			this.move("slow");
			
			if (this.position > this.length && !this.endFlag) {
				setTimeout(function(){
					this.position = 1;
					this.move(0);
				}.bind(this), 0);
			}
		}*/

		if(this.position === this.length) {
			if(!this.endFlag){
				this.position++;
				this.move("slow");
				setTimeout(function(){
					this.position = 1;
					this.move(0);
				}.bind(this), 0);
				
			} 	
		} 
		else {
			this.position++;
			this.move("slow");
		}
	}

	Slider.prototype.move = function(speed, callback) {
		return this.root.find(".visual_img").animate({
			"left": ( -(this.position-1) * this.size ) + "px"
		}, speed).promise();
	}
	
	return Slider;
});