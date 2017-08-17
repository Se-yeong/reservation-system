/**
 * 
 */
define([ "jquery", "component", "extend" ], function($, Component, extend) {
	var terms = extend(Component, {
		init : function() {
			$(".agreement").on("click", ".btn_agreement", this.viewTermToggle);
			$("#chk3").on("click", this.agreeTermToggle);
			$(".bk_btn_wrap").on("click", this.vaildateInput.bind(this));
		},
		agreeTermToggle : function() {
			$(".bk_btn_wrap").toggleClass("disable");
		},
		viewTermToggle : function(event) {
			event.preventDefault();
			$(this).closest(".agreement").toggleClass("open");
		},
		vaildateInput : function() {
			var regPhone = /^(\d{3}-\d{3,4}-\d{4}$)|(\d{3}\d{3,4}\d{4}$)/;
			var regEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
			var name = $("input[name=name]").val();
			var tel = $("input[name=tel]").val();
			var email = $("input[name=email]").val();
			var count = $("#amount").text();
			if (!name) {
				alert("이름을 입력해주세요.");
				return;
			}
			if (!regPhone.test(tel)) {
				alert("올바른 전화번호를 입력해주세요");
				return;
			}

			if (email && !regEmail.test(email)) {
				alert("올바른 이메일을 입력해주세요");
				return;
			}
			if (count === "0") {
				alert("티켓을 추가해 주세요.")
				return;
			}
			this.trigger("send",{name : name , tel : tel , email : email , amount : parseInt(count)});
		}
	});

	return terms;
});