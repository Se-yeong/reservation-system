define([ "jquery", "component", "extend2" ], function($, Component, extend) {
	function Rating($root) {
		this.score = 3;
		this.$root = $root;
		console.log($root);
		console.log(this.$root);
	}
	
	Rating.prototype.constructer = Rating;

	Rating.prototype.update = function () {
		var score = this.score;
		var i;

		if(score >= 1){

			for(i=1; i<=score; i++) {
				this.$root.find(".rating_rdo[value=" + i + "]").addClass("checked").attr("checked","checked").prop("checked",true);

			}

			for(; i<=5; i++) {
				this.$root.find(".rating_rdo[value=" + i + "]").removeClass("checked").removeAttr("checked").prop("checked",false);

			}

			this.$root.find(".star_rank").html(score);

		} else {
			this.$root.find(".rating_rdo").removeClass("checked").attr("checked","unchecked");
			this.$root.find(".rating_rdo[value='0']").addClass("checked").attr("checked","checked");
			this.$root.find(".star_rank").html("0");
		}
	}

	Rating.prototype.init = function() {
		console.log(this.$root);
		this.$root.on("click", ".rating_rdo", (function(e) {
			e.preventDefault();
			this.score = $(e.currentTarget).attr("value");
			this.update();
		}).bind(this));

		this.update();
	}

	Rating.prototype.getScore = function() {
		return this.score;
	}
	
	
	var rating = extend(Component, Rating.prototype);

	
	return rating;
});