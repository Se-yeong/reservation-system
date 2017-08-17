/**
 *  유효성 검증 및, 예매 내용 업데이트 
 */
define(["jquery", "component","extend"],function($,Component,extend){

	var	Preservator	=	extend(Component,{	
		init : function($root){
			this.$root = $root;
			this.tickets ={};
			
			this.amount = 0;
			this.$amount =this.$root.find("#amount");
		},
		updateAmount : function(ticket){
			this.tickets[ticket.type] = {};
			this.tickets[ticket.type].count = ticket.count
			this.tickets[ticket.type].price = ticket.price;
			if(ticket.state === "plus"){
				this.$amount.text(++this.amount);
			}else{
				this.$amount.text(--this.amount);
			}
		},
		sendData : function(data){
			// parsing이 조금 필요할듯 .. 
			formData =this.makeForm(data);
			$.ajax({
				method : "post",
				url : "/reservation",
				data : formData,
			}).then(function(data){
				if(data){
					// 일단 메인으로 ... 
					location.href = "/";
				}
			});
		},
		makeForm : function(data){
			var formData ={};
			var totalPrice = 0;
			formData.productId = getProductId();
			if(this.tickets["Adult"]){
				formData.generalTicketCount = this.tickets["Adult"].count;
				totalPrice += this.tickets["Adult"].price;
			}
			if(this.tickets["Teenage"]){
				formData.youthTicketCount =  this.tickets["Teenage"].count;
				totalPrice += this.tickets["Teenage"].price;
			}
			if(this.tickets["child"]){
				formData.childTicketCount =  this.tickets["child"].count;
				totalPrice += this.tickets["child"].price;
			}
			// 배열이 없으면 에러 뜨지 않나 ? 
			formData.reservationName = data.name;
			formData.reservationTel = data.tel;
			formData.reservationEmail =data.email;
			formData.totalPrice = totalPrice;
			return formData;
		}
	});
	return Preservator;
});