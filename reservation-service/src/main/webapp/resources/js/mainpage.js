/**
 * 
 */

$(function() {
	var categoryId = 0;

	$(".tab_lst_min > .item:last > .anchor").addClass("last");

	$(".event_tab_lst > .item").on("click", categoryClick);

	function categoryClick() {
		var $this = $(this);
		categoryId = $this.data("category");
		$(".anchor.active").removeClass("active");
		$this.find(".anchor").addClass("active");

		var url = "api/product/count";
		var productUrl = "api/product";
		var cachedCount = [];
		if (categoryId !== 0) {
			url = url + "/" + categoryId;
			productUrl = productUrl + "/category/" + categoryId;

		}

		$.ajax({
			url : url
		}).then(function(data) {
			console.log(data);
			$(".event_lst_txt > .pink").text(data + "개");
		});

		var start = 0, amount = 4;
		var source = $("#product-template").html();
		var template = Handlebars.compile(source);

		$.ajax({
			url : productUrl + "?start=" + start + "&amount=" + amount
		}).then(function(data) {
			// handle bar
			var left = [];
			var right =[];
			
			for (var i = 0, max = data.length; i < max; ++i) {
				if (i % 2) {
					left.push(data[i]);
				} else {
					right.push(data[i]);
				}
			}
			console.log(left);
			console.log(right);
			$(".lst_event_box:first").html(template({item: left}));
			$(".lst_event_box:last").html(template({item: right}));
		});
	}

});
