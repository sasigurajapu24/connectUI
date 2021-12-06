var app = angular.module('tc', ['ui.bootstrap', 'blockUI'], function ($compileProvider) {
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file):/);
});
app.controller('tcCntrl', ['$rootScope','$scope', '$http', 'menuService', function($rootScope, $scope, $http, menuService) {
	$rootScope.module = 2;
	$scope.selectedIndex = 1;
	$scope.alert = [];
	$scope.success = '';
	
}]);
app.controller('approveCtrl',['$rootScope','$scope','$http','menuService',function($rootScope,$scope,$http,menuService){
	$rootScope.module = 8;
	$scope.approve={};
	$scope.screen = "list";
	$scope.users= [];
	$scope.getApprove = function() { 
		$http.get("/tc/subservice/")
		.success(function(response) {
			$scope.users = response;
	 });
	}
	$scope.approveServiceUser = function(userID){
		var message;
		message = "Are you sure you want to Proceed?";
		showCommonConfirmMessage(message,"Alert","Yes","No",450,function(flag){
			if(flag){
				
			}else{
				
			}
			
		})
	};
	
	$scope.getApprove();
}]);




app.controller('codesCntrl', ['$scope', '$http' , 'blockUI' , function($scope, $http, blockUI) {

	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.code = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/codes/save';
	$scope.errors = {};

	$scope.loadCodes = function(){
		var url = "/codes/codes";
		if(userType == 'admin'){
			url = "/codes/all";
		}
		$http.get(url)
		.success(function(response) {
			$scope.codes = response;
			$scope.currentPage = $scope.codes.number+1;
			$scope.errors = {};
		})
	}

	$scope.pageChanged = function() {
		var url = "/codes/codes";
		if(userType == 'admin'){
			url = "/codes/all";
		}
		var page = $scope.currentPage-1;
		var size = $scope.codes.size;
		$http.get(url+"?page="+page+"&size="+size)
		.success(function(response) {
			$scope.codes = response;
			$scope.currentPage = $scope.codes.number+1;
		});
	};
	
	$scope.loadProducts = function() { 
		$http.get("/products/all")
		.success(function(response) {
			$scope.products = response;
		});
	}
	
	$scope.addCodes = function() {
	    $scope.screen ='Save';
		$scope.action = '/codes/save';
		$scope.loadProducts();		    	
		$scope.code = {'rewards' : 1 , 'product' : {'id' : parseInt(message)}};
	}

	$scope.editCode = function(uniqueCode) {
		$scope.screen = 'Update';
		$scope.action = '/codes/update';
		$scope.code = uniqueCode;
	}

	$scope.viewCode = function(uniqueCode) {
		$scope.screen = 'View';
		$scope.code = uniqueCode;
		/*$http.get("/master/products/"+code)
		.success(function(response) {
			$scope.code = code;
		});*/
	}
	$scope.deleteCode = function(unique) {
		alert('Inside delete ')
		$scope.screen = 'Delete';
		$scope.action = '/codes/delete';
		$scope.code = unique;
	}
	
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


	$scope.submitForm = function(screen, code){
		
		if($scope.code.product ===null || typeof $scope.code.product === 'undefined' || $scope.code.product === ""){
			$scope.isProductEmpty="Select Product from list";
			return;
		}else{$scope.isProductEmpty = null;}
		
		if($scope.code.batchCode ===null || typeof $scope.code.batchCode === 'undefined' || $scope.code.batchCode === ""){
			$scope.isBCodeEmpty="Batch Code can not be left empty";
			return;
		}else{$scope.isBCodeEmpty = null;}
		
		if($scope.code.noProductBatch ===null || typeof $scope.code.noProductBatch === 'undefined' || $scope.code.noProductBatch === ""){
			$scope.isPrdctEmpty="No Of product for Batch can not be left empty";
			return;
		}else{$scope.isPrdctEmpty = null;}
		
		if($scope.code.mfgDate ===null || typeof $scope.code.mfgDate === 'undefined' || $scope.code.mfgDate === ""){
			$scope.ismfgDateEmpty="Manufacture Date can not be left empty";
			return;
		}else{$scope.ismfgDateEmpty = null;}
		
		if($scope.code.productSpecification ===null || typeof $scope.code.productSpecification === 'undefined' || $scope.code.productSpecification === ""){
			$scope.isPrdctSpecEmpty="Product Specification can not be left empty";
			return;
		}else{$scope.isPrdctSpecEmpty = null;}
		
//		if($scope.code.thanksMsg ===null || typeof $scope.code.thanksMsg === 'undefined' || $scope.code.thanksMsg === ""){
//			$scope.isthnkEmpty="Thank Message can not be left empty";
//			return;
//		}else{$scope.isthnkEmpty = null;}
//		
//		if($scope.code.promotionMsg ===null || typeof $scope.code.promotionMsg === 'undefined' || $scope.code.promotionMsg === ""){
//			$scope.isPrmpMsgEmpty="Promotion Message can not be left empty";
//			return;
//		}else{$scope.isPrmpMsgEmpty = null;}
		
		if($scope.code.specialInstruction ===null || typeof $scope.code.specialInstruction === 'undefined' || $scope.code.specialInstruction === ""){
			$scope.isSpecialInsEmpty="Special Instruction can not be left empty";
			return;
		}else{$scope.isSpecialInsEmpty = null;}
	
		
		if(screen == 'Save'){
			
			
			$http.post('/codes/save', code).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadCodes();
					$scope.screen = 'list';
					$scope.code = {};
					//menuService.setSuccess("Code saved successfully.");
				}else{
					$scope.errors = data.errors; 
					//menuService.setErrors(data.errors);
				}
			});
			
		}else if( screen == 'Update'){
			$http.post('/codes/update', code).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadCodes();
					$scope.screen = 'list';
					$scope.code = {};
					//menuService.setSuccess("Code saved successfully.");
				}else{
					
					$scope.errors = data.errors;
					//menuService.setErrors(data.errors);
				}
			});
			
		}else if( screen == 'Delete'){
		}
	};
	
	$scope.requestCodes = function(id){
		$http.post("/codes/requestCode/"+id)
		.success(function(response) {
			if(response.success==true){
				$scope.loadCodes();
				menuService.setSuccess("Request Code saved successfully.");
			}else{
				menuService.setSuccess("Request Code Already Exist");
			}
		});
	}
	
	$scope.searchCodes = function() {
		$scope.spinner=true
		$http.post('/search', $scope.search).
		success(function(data, status, headers, config) {
			$scope.codes = data;
			$scope.spinner=false;
		});
	}
	$scope.open = function($event) {
		$scope.status.opened = true;
	};

	$scope.status = {
			opened: false
	};
	$scope.open1 = function($event) {
		$scope.status1.opened = true;
	};

	$scope.status1 = {
			opened: false
	};
	$scope.loadCodes();
	$scope.loadProducts();

}]);


app.controller('notificationCtrl', ['$rootScope', '$scope', '$http','menuService',function($rootScope, $scope, $http,menuService) {
	menuService.setModule(3);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.currentPage = 1;
	$scope.notification = {};
	$scope.size=10;
	$scope.action = '/notifications/save';
	$scope.module=3;
	$scope.page = 0;
	$scope.products = {};

	$scope.loadCodes = function(){
		var url = "/notifications/notifications";
		if(userType == 'admin'){
			url = "/notifications/all";
		}
		$http.get(url)
		.success(function(response) {
			$scope.notifications = response;
			$scope.currentPage = $scope.notifications.number+1;
		});
	}
	
	 $scope.setPage = function (pageNo) {
		    $scope.currentPage = pageNo;
	 };

	$scope.pageChanged = function() {
		var url = "/notifications/notifications";
		if(userType == 'admin'){
			url = "/notifications/all";
		}
		var page = $scope.currentPage-1;
		var size = $scope.notifications.size;
		$http.get(url+"?page="+page+"&size="+size)
		.success(function(response) {
			$scope.notifications = response;
			$scope.currentPage = $scope.notifications.number+1;
			$rootScope.currentPage = $scope.currentPage;
		});
	};
	
	/*$scope.pageChanged = function() {
		if($scope.screen == 'search') {
			$scope.searchState();
			return;
		}
		var page = $scope.currentPage-1;
		var size = $scope.states.size;
		$http.get("/master/states?page="+page+"&size="+size).success(function(response) {
			$scope.states = response;
			$rootScope.statesview = $scope.states;
			$scope.currentPage = $scope.states.number+1;
			$rootScope.currentPage = $scope.currentPage;
		});
	};*/
	
	$scope.loadProducts = function() { 
		var url = "/products/all";
		if(userType == 'admin'){
			url = "/products?component=true";
		}
		$http.get(url)
		.success(function(response) {
			if(response.content){
				$scope.products = response;
			}else{
				$scope.products['content'] = response;
			}
		});
	}
	
	$scope.addNotification = function() {
	    $scope.screen ='Save';
		$scope.action = '/notifications/save';
		$scope.loadProducts();		    	
		$scope.notification = {};
	}

	$scope.editCode = function(uniqueCode) {
		$scope.screen = 'Update';
		$scope.action = '/notifications/update';
		$scope.notification = uniqueCode;
	}

	$scope.viewCode = function(uniqueCode) {
		$scope.screen = 'View';
		$scope.notification = uniqueCode;
		/*$http.get("/master/products/"+code)
		.success(function(response) {
			$scope.code = code;
		});*/
	}
	$scope.deleteCode = function(unique) {
		alert('Delete function not supported.')
//		$scope.screen = 'Delete';
//		$scope.action = '/notifications/delete';
//		$scope.code = unique;
	}
	
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


	$scope.submitForm = function(screen, code){
		$scope.notification = code;
		if(screen == 'Save'){

			$http.post('/notifications/save', code).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadCodes();
					$scope.screen = 'list';
					$scope.notification = {};
					//menuService.setSuccess("Code saved successfully.");
				}else{
					$scope.errors = data.errors; 
					//menuService.setErrors(data.errors);
				}
			});
		}else if( screen == 'Update'){

			$http.post('/notifications/update', code).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadCodes();
					$scope.screen = 'list';
					$scope.notification = {};
					//menuService.setSuccess("Code saved successfully.");
				}else{
					$scope.errors = data.errors; 
					//menuService.setErrors(data.errors);
				}
			});
		}else if( screen == 'Delete'){
		}
	};

	$scope.searchproduct = function() {
		$scope.spinner=true
		$http.post('/notifications/search', $scope.search).
		success(function(data, status, headers, config) {
			$scope.products = data;
			$scope.spinner=false;
		});
	}
	
	$scope.fromopen = function($event) {
		$scope.fromstatus.opened = true;
	};
	
	$scope.fromstatus = {
			opened: false
	};
	$scope.fromclear = function () {
	    $scope.point.to_date = null;
	};

	
	$scope.toopen = function($event) {
		$scope.tostatus.opened = true;
	};
	
	$scope.tostatus = {
			opened: false
	};
	$scope.toclear = function () {
	    $scope.point.to_date = null;
	};
	
	$scope.loadCodes();
	$scope.loadProducts();

}]);

app.controller('reportsCtrl', ['$rootScope', '$scope', '$http','menuService',function($rootScope, $scope, $http,menuService) {
	menuService.setModule(4);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.code = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/notifications/save';
	$scope.module=3;
	$scope.product = {};
	$scope.products = {};
	
	$scope.hits = {};

	$scope.getData = function(product){
		$http.get("/codes/hits/"+product.id)
		.success(function(response) {
			$scope.hits = response;
			$scope.currentPage = $scope.hits.number+1;
		});
	} 

	$scope.pageChanged = function() {
		var url = "/codes/hits/"+$scope.product.id;
		var page = $scope.currentPage-1;
		var size = $scope.hits.size;
		$http.get(url+"?page="+page+"&size="+size)
		.success(function(response) {
			$scope.hits = response;
			$scope.currentPage = $scope.hits.number+1;
		});
	};
	
	$scope.loadProducts = function() { 
		var url = "/products/all";
		if(userType == 'admin'){
			url = "/products?component=true";
		}
		$http.get(url)
		.success(function(response) {
			if(response.content){
				$scope.products = response;
			}else{
				$scope.products['content'] = response;
			}
			
		});
	}

	$scope.loadProducts();

}]);

app.controller('userCtrl', ['$rootScope', '$scope', '$http','menuService',function($rootScope, $scope, $http,menuService) {

	$scope.states = [{ "short": "AP", "name": "Andhra Pradesh", "country": "IN" },
{ "short": "AR", "name": "Arunachal Pradesh", "country": "IN" },
{ "short": "AS", "name": "Assam", "country": "IN" },
{ "short": "BR", "name": "Bihar", "country": "IN" },
{ "short": "CT", "name": "Chhattisgarh", "country": "IN" },
{ "short": "GA", "name": "Goa", "country": "IN" },
{ "short": "GJ", "name": "Gujarat", "country": "IN" },
{ "short": "HR", "name": "Haryana", "country": "IN" },
{ "short": "HP", "name": "Himachal Pradesh", "country": "IN" },
{ "short": "JK", "name": "Jammu and Kashmir", "country": "IN" },
{ "short": "JH", "name": "Jharkhand", "country": "IN" },
{ "short": "KA", "name": "Karnataka", "country": "IN" },
{ "short": "KL", "name": "Kerala", "country": "IN" },
{ "short": "MP", "name": "Madhya Pradesh", "country": "IN" },
{ "short": "MH", "name": "Maharashtra", "country": "IN" },
{ "short": "MN", "name": "Manipur", "country": "IN" },
{ "short": "ML", "name": "Meghalaya", "country": "IN" },
{ "short": "MZ", "name": "Mizoram", "country": "IN" },
{ "short": "NL", "name": "Nagaland", "country": "IN" },
{ "short": "OR", "name": "Odisha", "country": "IN" },
{ "short": "PB", "name": "Punjab", "country": "IN" },
{ "short": "RJ", "name": "Rajasthan", "country": "IN" },
{ "short": "SK", "name": "Sikkim", "country": "IN" },
{ "short": "TN", "name": "Tamil Nadu", "country": "IN" },
{ "short": "TG", "name": "Telangana", "country": "IN" },
{ "short": "TR", "name": "Tripura", "country": "IN" },
{ "short": "UP", "name": "Uttar Pradesh", "country": "IN" },
{ "short": "UT", "name": "Uttarakhand", "country": "IN" },
{ "short": "WB", "name": "West Bengal", "country": "IN" },
{ "short": "AN", "name": "Andaman and Nicobar Islands", "country": "IN" },
{ "short": "CH", "name": "Chandigarh", "country": "IN" },
{ "short": "DN", "name": "Dadra and Nagar Haveli", "country": "IN" },
{ "short": "DD", "name": "Daman and Diu", "country": "IN" },
{ "short": "LD", "name": "Lakshadweep", "country": "IN" },
{ "short": "DL", "name": "National Capital Territory of Delhi", "country": "IN" },
{ "short": "PY", "name": "Puducherry", "country": "IN" }];
	menuService.setModule(6);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.product = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/products/save';
	$scope.errors = [];
	$scope.module=1;
	$scope.loadUsers = function() { 
		$http.get("/users/all")
		.success(function(response) {
			$scope.users = response;
		});
	}
	$scope.pageChanged = function() {
		var url = "/users/all";
		var page = $scope.currentPage-1;
		var size = $scope.users.size;
		$http.get(url+"?page="+page+"&size="+size)
		.success(function(response) {
			$scope.users = response;
			$scope.currentPage = $scope.users.number+1;
		});
	};
	
//	$scope.loadUsers = function() { 
//		$http.get("/users/all-merchants")
//		.success(function(response) {
//			$scope.users = response;
//			$scope.errors = {};
//		});
//	}
//	$scope.pageChanged = function() {
//		var url = "/users/all-merchants";
//		var page = $scope.currentPage-1;
//		var size = $scope.users.size;
//		$http.get(url+"?page="+page+"&size="+size)
//		.success(function(response) {
//			$scope.codes = response;
//			$scope.currentPage = $scope.users.number+1;
//		});
//	};
	
	$scope.getUserTypes = function() {
		var url = "/users/types";
		$http.get(url)
		.success(function(response) {
			$scope.usertypes = response;
		});
	};
	
	$scope.addMerchant = function(){
		$scope.screen = 'Save';
		$scope.action = "Save";
		$scope.merchant = {"store" : {"country" : 'INDIA'}};
		$scope.errors = {};
	}
	
	$scope.editMerchant = function(user){
		$scope.screen = 'Update';
		user['msisdn'] = parseInt(user['msisdn']);
		user['store']['brand'] = parseInt(user['store']['brand']);
		
		$scope.merchant = user;
		var name = user.name.split(' ');
		$scope.merchant['firstName'] = name[0];
		if(name.length > 1)
		$scope.merchant['lastName'] = name[1];
		
		$scope.errors = {};
	}
	$scope.viewMerchant = function(user){
		$scope.screen = 'View';
		$scope.merchant = user;
	}
	
	$scope.getBrands = function() {
		var url = "/users/brands";
		$http.get(url)
		.success(function(response) {
			$scope.brands = response.data;
		});
	};
	
	
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


	$scope.submitForm = function(screen, code){
		$scope.errors = [];
		if($scope.user.username===null || typeof $scope.user.username === 'undefined' || $scope.user.username === ""){
			$scope.errors.push({"defaultMessage" : "User Name can not be left empty" });
			return;
		}else{$scope.isEmptyUser = null;}
		if($scope.user.password === null || typeof $scope.user.password === 'undefined' || $scope.user.password === ""){
			$scope.errors.push({"defaultMessage" : "Password field can not be left empty" });
			return;
		}else{$scope.isEmptyPassword=null;}
		if($scope.user.mobile === null || typeof $scope.user.mobile === 'undefined' || $scope.user.mobile ==="" ){
			$scope.errors.push({"defaultMessage" : "Mobile No can not be left empty" });
			return;
		}else{$scope.isEmptyMobile=null;}
		if(typeof $scope.user.mobile !== 'undefined'  ){
			if( ($scope.user.mobile).length > 10 || ($scope.user.mobile).length <10){
				$scope.errors.push({"defaultMessage" : "Mobile No should be 10 digits " });
			return;
			}else{$scope.isEmptyMobile=null;}
		}
		if (!/^\d{10}$/.test($scope.user.mobile)){
			$scope.errors.push({"defaultMessage" : "Enter Valid Mobile No" });
			return false;
		}else{$scope.isEmptyMobile = null;}
		
		if($scope.user.firstName === null || typeof $scope.user.firstName === 'undefined' || $scope.user.firstName ==="" ){
			$scope.errors.push({"defaultMessage" : "Firstname can not be left empty" });
			return;
		}else {$scope.emailExist = null;}
		if(!validateEmail($scope.user.username)){
			$scope.errors.push({"defaultMessage" :  "Enter Valid Email Address"});
			return false;
		}else{$scope.emailExist =null;}
		
		if($scope.user.address===null ||typeof  $scope.user.address === 'undefined' || $scope.user.address === ""){
			$scope.errors.push({"defaultMessage" : "Address can not be left empty" });
			return;
		}else{$scope.isEmptyAddress =null;}
		
		function validateEmail(email) 
		{
			var regExpEmail = /\S+@\S+\.\S+/;
			return regExpEmail.test(email);
		}
		
		code['name'] = code['firstName'] + ' '+ code['lastName'];
//		code['store']['brand'] = code['brand'];
		if(screen == 'Save'){
			$http.post('/users/create', $scope.user).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadUsers();
					$scope.screen = 'list';
					$scope.code = {};
					//menuService.setSuccess("Code saved successfully.");
				}else{
					$scope.errors = data.errors; 
					//menuService.setErrors(data.errors);
				}
			});
		}else if( screen == 'Update'){
			$http.post('/users/create', code).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadUsers();
					$scope.screen = 'list';
					$scope.code = {};
					//menuService.setSuccess("Code saved successfully.");
				}else{
					$scope.errors = data.errors;
					//menuService.setErrors(data.errors);
				}
			});
			
		}else if( screen == 'Delete'){
		}
	};
	
	$scope.loadUsers();
	$scope.getBrands();
}]);

app.controller('regCtrl', ['$rootScope', '$scope', '$http','menuService',function($rootScope, $scope, $http,menuService) {

	menuService.setModule(6);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.product = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/products/save';
	$scope.errors = [];
	$scope.module=1;
	
	$scope.register =function(user){
			if($scope.user.username===null || typeof $scope$scope.user.username === 'undefined' || $scope.user.username === ""){
				$scope.isEmptyUser="User Name can not be left empty";
				return;
			}
			if($scope.user.password === null || typeof $scope.user.password === 'undefined' || $scope.user.password === ""){
				$scope.isEmptyPassword="Password field can not be left empty";
				return;
			}
			if($scope.user.person === null || typeof $scope.user.person === 'undefined' || $scope.user.person ==="" ){
				$scope.isEmptyPerson="Person Name can not be left empty";
				return;
			}if($scope.user.mobile === null || typeof $scope.user.mobile === 'undefined' || $scope.user.mobile ==="" ){
				$scope.isEmptyMobile="Mobile No can not be left empty";
				return;
			}if(typeof $scope.user.mobile !== 'undefined'  ){
				if($scope.user.mobile.length > 10 || $scope.user.mobile <10)
				$scope.isEmptyMobile="Mobile No can be max of 10 digits ";
				return;
			}if (!/^\d{10}$/.test($scope.user.mobile)){
				$scope.isEmptyMobileNo="Enter Valid Mobile No";
				return false;
			}
			
			if($scope.user.email === null || typeof $scope.user.email === 'undefined' || $scope.user.email ==="" ){
				$scope.isEmptyMobile="Email can not be left empty";
				return;
			}
			if(!validateEmail($scope.user.email)){
				$scope.emailExist = "Enter Valid Email Address";
				return false;
			}
			if($scope.user.companyName===null || typeof $scope.user.companyName === 'undefined' ||$scope.user.companyName ==="" ){
				$scope.isEmptyComapany="Company Name can not be left empty";
				return;
			}
			if($scope.user.address===null ||typeof  $scope.user.address === 'undefined' || $scope.user.address === ""){
				$scope.isEmptyAddress="Address can not be left empty";
				return;
			}
		    
			$http.post('/register/create', $scope.user).
			success(function(data, status, headers, config) {
				if(status == 200){
				//success message 
			    //redirect to login Page		
				} 
				else {
					$scope.msg==null;
				}
			});	
		  }
	$scope.isExistLoginName= function() {
		$http.post('/regsiter/isLoginExist',$scope.user).
		success(function(data, status, headers, config) {
			if(data.statusCode){
				$scope.isEmptyUser="LoginName is Already Exist Choose Another One.";
			} else {
				$scope.isEmptyUser=null;
			}
		});	
	}
	
	$scope.isExistEmail= function() {
		$http.post('/register/isEmailExist',$scope.user).
		success(function(data, status, headers, config) {
			if(data.statusCode){
				$scope.emailExist="Email Is Already Exist Choose Another One .";
			} else {
				$scope.emailExist=null;
			}
		});	
	}
	
}]);


app.controller('genCodeCtrl', ['$rootScope', '$scope', '$http','menuService',function($rootScope, $scope, $http,menuService) {
	menuService.setModule(5);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.code = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.module=5;
	
	$scope.hits = {};

	$scope.loadProducts = function() { 
		$http.get("/products/users")
		.success(function(response) {
			$scope.products = response;
		});
	}
	
	
	$scope.loadRequests = function() { 
		$http.get("/codes/requests")
		.success(function(response) {
			$scope.requests = response;
		});
	}
	
	$scope.pageChanged = function() {
		var url = "/codes/requests";
		var page = $scope.currentPage-1;
		var size = $scope.codes.size;
		$http.get(url+"?page="+page+"&size="+size)
		.success(function(response) {
			$scope.codes = response;
			$scope.currentPage = $scope.codes.number+1;
		});
	};
	
	
	$scope.generateCodes = function(request){
		$http.post("/codes/generate/"+request.code.id+"/"+request.id)
		.success(function(response) {
			if(response){
				$scope.loadRequests();
			}
		});
	}
	

	//$scope.loadProducts();
	$scope.loadRequests();

}]);


app.controller('offersCtrl', ['$rootScope', '$scope', '$http','menuService' ,function($rootScope, $scope, $http,menuService) {
	menuService.setModule(7);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.offer = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/products/save';
	$scope.nameerror = '';
	$scope.module=7;
	$scope.loadOffers = function() {
		var url = "/offers/offers";
		$http.get(url)
		.success(function(response) {
			$scope.offers = response;
			$scope.currentPage = $scope.offers.number+1;
		});
	}
	
	$scope.pageChanged = function() {
		var url = "/offers/offers";
		var page = $scope.currentPage-1;
		var size = $scope.offers.size;
		$http.get(url+"?page="+page+"&size="+size)
		.success(function(response) {
			$scope.codes = response;
			$scope.currentPage = $scope.codes.number+1;
		});
	};
	
	$scope.checkName = function(val) { 
		$http.get("/offers/checkname/"+val)
		.success(function(response) {
				if(response == true){
					$scope.errors.push({"defaultMessage": 'Offer name already exists!!'});
				}else{
					$scope.errors ={};
				}
		});
	}
	
	$scope.addOffer = function() {
		$scope.screen = 'Save';
		$scope.offer = {};
		$scope.action = '/offers/save';
	}

	$scope.editOffer = function(code) {
		$scope.screen = 'Update';
		$scope.offer = code;
		$scope.action = '/offers/update';
	}

	$scope.viewProduct = function(code) {
		$scope.screen = 'View';
		$scope.offer= code;
	}

	$scope.resetproduct = function() {
		$scope.search = {};
		$scope.searchOffer();
	}
	
//	$scope.uploadedFile = function(element) {
//		 $scope.$apply(function($scope) {
//		   $scope.files = element.files;         
//		 });
//		}

	$scope.submitForm = function(screen, product) {
		
		//product.file = $scope.files[0];
		var fd = new FormData();
		var img = $scope.image;
//		$scope.image = null;
		if(null != img){
			fd.append("image", img);
		}
		fd.append("offer", angular.toJson($scope.offer));
		$http.post($scope.action, fd, {
			transformRequest : angular.identity,
			headers : {
			'Content-Type' : undefined }
			}).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadOffers();
					$scope.screen = 'list';
					$scope.offer = {};
				}else{
					$scope.errors = data.errors;
				}
			});
	}
	
	$scope.searchOffers  = function() {
		$scope.spinner=true
		$http.post('/offers/search', $scope.search).
		success(function(data, status, headers, config) {
			$scope.offers = data;
			$scope.spinner=false;
		});
	}
	
	$scope.fromopen = function($event) {
		$scope.fromstatus.opened = true;
	};
	
	$scope.fromstatus = {
			opened: false
	};
	$scope.fromclear = function () {
	    $scope.point.to_date = null;
	};

	
	$scope.toopen = function($event) {
		$scope.tostatus.opened = true;
	};
	
	$scope.tostatus = {
			opened: false
	};
	$scope.toclear = function () {
	    $scope.point.to_date = null;
	};
	
	$scope.loadOffers();
}]);

app.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);

angular.module('tc').directive('modalDialog', function() {
	return {
		restrict : 'E',
		scope : {
			show : '='
		},
		transclude : true, // Insert custom content inside the directive
		link : function(scope, element, attrs) {
			console.log('attrs: ', attrs);
			scope.dialogStyle = {};
			if (attrs.boxWidth) {
				scope.dialogStyle.width = attrs.boxWidth;
			}
			if (attrs.boxHeight) {
				scope.dialogStyle.height = attrs.boxHeight;
			}
			scope.hideModal = function() {
				scope.show = false;
			};
		},
		templateUrl : 'modalDialog'
	};
});

