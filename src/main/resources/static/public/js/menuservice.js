app.factory('menuService', function($rootScope) {
	//this.selectedIndex = 1;
	this.alert = [];
	this.success = '';
	this.resource = {};
	this.locationurl='';
	return {
		set : function(index) {
			this.selectedIndex = index;
		},
		get : function() {
			return this.selectedIndex;
		},
		setErrors : function(alert) {
			this.alert = alert;
		},
		setModule : function(m) {
			$rootScope.module = m;
		},
		setMessage: function(mesg) {
			var error = [];
			var msg = new Object();
			msg.defaultMessage = mesg;
			error.push(msg)
			this.alert = error;
		},
		setSuccess: function(mesg) {
			this.success = mesg;
		},
		getPaginationSize : function() {
			return 10;
		},
		getErrors : function() {
			return alert;
		},
		setLocation : function(locationurl) {
			this.locationurl = locationurl;
		}
	};
});

var serialize = function(obj, prefix) {
	var str = [];
	for(var p in obj) {
		if (obj.hasOwnProperty(p)) {
			var k = prefix ? prefix + "[" + p + "]" : p, v = obj[p];
			str.push(typeof v == "object" ?
					serialize(v, k) :
						encodeURIComponent(k) + "=" + encodeURIComponent(v));
		}
	}
	return str.join("&");
}

