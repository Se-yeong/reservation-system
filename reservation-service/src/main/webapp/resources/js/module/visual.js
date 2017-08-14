define(["jquery","handlebars","component","extend"], function($, Handlebars, Component, extend) {

		function Visual(setting) {
			this.$root = setting.$root;
			this.url = setting.url;
			
		}
		
		Visual.prototype.getImages = function() {
			$.ajax({
				url: this.url,
				method: "GET",
				dataType: 'json'
			}).then(showImages.bind(this));
		}
		
		Visual.prototype.showImages = function(data) {
			
		}
});