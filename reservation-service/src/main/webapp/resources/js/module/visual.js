define([ "jquery", "handlebars", "component", "extend2" ], function($,Handlebars, Component, extend) {


// handlebarsElement :  $("#image-template").html() 

	function Visual(setting) {
		this.$root = setting.$root;
		this.url = setting.url || false;
		this.source = setting.handlebarsElement;
		this.template = Handlebars.compile(source);
		//console.log(getEl());
	}
	
	Visual.prototype.constructor = Visual;
	
	Visual.prototype.getImages = function() {
		
		
	}
	
	Visual.prototype.getAjax = function(){
		$.ajax({
			url : this.url,
			method : "GET",
			dataType : 'json'
		}).then(showImages.bind(this));
	}

	Visual.prototype.showImages = function(data) {
		
		console.log(data);
		
		$root.find("ul.visual_img").append(template("어떤 데이터"));
	}
	
	
	
	var visual = extend(Component, Visual);

	console.log(visual);
	
	return visual;
});