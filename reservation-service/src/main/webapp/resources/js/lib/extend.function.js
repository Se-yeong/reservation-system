define(function(){
	function extend(superClass, def) {
		var extendClass = function extendClass() {
			// Call a parent constructor
			superClass.apply(this, arguments);
	
			// Call a child constructor
			if (typeof def.constructor === "function") {
				def.constructor.apply(this, arguments);
			}
		};
	
		var ExtProto = function() {};
		ExtProto.prototype = superClass.prototype;
	
		var extProto = new ExtProto();
		for (var i in def) {
			extProto[i] = def[i];
		}
	
		extProto.constructor = extendClass;
		extendClass.prototype = extProto;
	
		return extendClass;
	};
	
	return extend;
});