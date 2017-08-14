define([ "jquery", "handlebars", "component", "extend2" ], function($,Handlebars, Component, extend) {


// handlebarsElement :  $("#image-template").html() 

	function Visual(setting) {
		this.$root = setting.$root;
		this.source = setting.handlebarsElement;
		this.template;
		this.init();
		
	}
	
	Visual.prototype.constructor = Visual;
	
	Visual.prototype.init = function() {
		this.template = Handlebars.compile(this.source);
		
	}

	
	Visual.prototype.getImagesByEl = function(product) {
		this.makeVisual(product.productImage);
	}
	
	Visual.prototype.getImagesByAjax = function(url) {
		$.ajax({
			url : this.url,
			method : "GET",
			dataType : 'json'
		}).then(this.makeVisual.bind(this));
	}

	Visual.prototype.makeVisual = function(data) {
		
		var images = [];
		data.forEach(function(v, i) {
			images.push(v.file);
		});
		
		var result = this.template({images: images});
		this.$root.find(".visual_img").append(result);
	}
	
	
	
	var visual = extend(Component, Visual.prototype);

	
	return visual;
});