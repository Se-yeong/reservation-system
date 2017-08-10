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
			
			this.$minusBtn.on("click",this.minus.bind(this));
			this.$plusBtn.on("click",this.plus.bind(this));
		},
		plus : function(event){
			event.preventDefault(); 
			++this.count;
			this.updateText();	
		},
		// 개수 체크하는 부분 필요 
		minus : function(event){
			event.preventDefault();
			--this.count;
			this.updateText();
		},
		updateText : function(){
			this.$count.val(this.count);
			this.updateSum();
			this.trigger("changeAmount");
		},
		updateSum : function(){
			this.priceSum = this.count * this.price;
			this.$priceSum.text(this.priceSum);
		}
	});
	
	return ticket;
/*	function Ticket($root,max){
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
		
		this.$minusBtn.on("click",this.minus.bind(this));
		this.$plusBtn.on("click",this.plus.bind(this));
		
		
		//  on      -> 핸들러를 지정하면 , ticket.("upd" , function~ )     
		//  trigger -> 이벤트     안에서 부르는 걸로 ~ 
		//   ticket  -> start 
		//   slide  -> end 
		// main 
		
		 *  start 에 trigger 가 잇어야댐 
		 *  main 
		 *  
		 *  ticket.on("start", function(){
		 *  	slide.end();
		 *  // 이렇게 해볼것 .
		 *  });
		 
	}

	Ticket.prototype = new Component();
	Ticket.prototype.constructor = Ticket;

	Ticket.prototype.plus = function(event){
		event.preventDefault(); 
		++this.count;
		this.updateText();	
	}

	// 개수 체크하는 부분 필요 
	Ticket.prototype.minus = function(event){
		event.preventDefault();
		--this.count;
		this.updateText();
	}
	
	Ticket.prototype.updateText = function(){
		this.$count.val(this.count);
		this.updateSum();
		this.trigger("changeAmount");
	}
	
	Ticket.prototype.updateSum = function(){
		this.priceSum = this.count * this.price;
		this.$priceSum.text(this.priceSum);
	}
	
	return Ticket;
	*/
	
	
	
});