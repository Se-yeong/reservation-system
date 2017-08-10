/**
 *  유효성 검증 및, 예매 내용 업데이트 
 */
define(["jquery", "component","extend"],function($,Component,extend){

	var	Preservator	=	extend(Component,{	
		init : function($root){
			this.$root = $root;
			
			this.amount = 0;
			this.$amount =this.$root.find("#amount");
		},
		updateAmount : function(data){
			if(data.state === "plus"){
				this.$amount.text(++this.amount);
			}else{
				this.$amount.text(--this.amount);
			}
		}
	});
	return Preservator;
});