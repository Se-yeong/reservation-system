define(["jquery", "component","extend"],function($,Component,extend){

	
	var ticket = extend(Component,{	
		init : function($root,type){
			this.$root = $root;
			this.max =  10;
			this.count = 0;
			this.$count = this.$root.find("[type = tel]");
			this.type =  type;
				
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
			var ticketData;
			this.$count.val(this.count);
			this.updateSum();
			ticketData = {state : state, type : this.type ,  count : this.count , price : this.price * this.count};
			this.trigger("changeAmount",ticketData);
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