define([ "jquery", "handlebars", "component", "extend2" ], function($,Handlebars, Component, extend) {

	function Visual(setting) {
		this.$root = setting.$root;
		this.source = setting.handlebarsElement;
		this.template;
		this.length;
		
		this.init();
	}
	
	Visual.prototype.constructor = Visual;
	
	Visual.prototype.init = function() {
		this.template = Handlebars.compile(this.source);
	}

	Visual.prototype.getImages = function(product) {
		this.makeVisual(product.productImage);
		return Promise.resolve();
	}
	
	Visual.prototype.getImagesByAjax = function(url) {
		return $.ajax({
			url : url,
			method : "GET",
			dataType : 'json'
		}).then(this.makeVisual.bind(this));
	}

	Visual.prototype.makeVisual = function(data) {
		console.log(data);
		this.length = data.length;
		
		var images = [];
		data.forEach(function(v, i) {
			images.push(v.file);
		});
		var result = this.template({images: images});
		this.$root.find(".visual_img").html(result);
	}
	
	Visual.prototype.getLength = function() {
		return this.length;
	}

	var visual = extend(Component, Visual.prototype);

	
	return visual;
});