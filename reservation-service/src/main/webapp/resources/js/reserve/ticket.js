define(["jquery", "component","extend"],function($,Component,extend){

	
	var ticket = extend(Component,{	
		init : function($root,max){
			this.$root = $root;
			this.max = max || 10;
			this.count = 0;
			this.$count = this.$root.find("[type = tel]");
			
			// data- 로 진행 할 것. 
			this.price = parseInt(this.$root.find("span.price").text().replace( "," , "" ));
			this.priceSum = 0;
			this.$priceSum = this.$root.find("span.total_price");
			
			this.$minusBtn = this.$root.find(".ico_minus3 ");
			this.$plusBtn = this.$root.find(".ico_plus3");
			
			this.$plusBtn.on("click",this.plus.bind(this));
		},
		plus : function(event){
			event.preventDefault(); 
			++this.count;
			this.updateText("plus");	
			this.updateBtnState(this.max,this.$plusBtn,this.$minusBtn,"minus");
		
		},
		// 개수 체크하는 부분 필요 
		minus : function(event){
			event.preventDefault();
			--this.count;
			this.updateText("minus");
			this.updateBtnState(0,this.$minusBtn,this.$plusBtn,"plus");
			
		},
		updateText : function(state){
			this.$count.val(this.count);
			this.updateSum();
			this.trigger("changeAmount",{state : state});
		},
		updateBtnState : function(point,$btn,$otherBtn,state){
			if(this.count === point){
				$btn.off("click");
				$btn.addClass("disabled");
			}
			if($otherBtn.hasClass("disabled")){
				$otherBtn.removeClass("disabled");
				$otherBtn.on("click",this[state].bind(this));
			}
		},
		updateSum : function(){
			this.priceSum = this.count * this.price;
			this.$priceSum.text(this.priceSum);
		}
	});
	
	return ticket;
	
});