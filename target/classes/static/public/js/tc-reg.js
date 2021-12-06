var regapp = angular.module('reg', ['blockUI']);
regapp.controller('regCtrl1', [ '$scope', '$http','$location','$window', 'blockUI' ,function( $scope, $http ,$location, $window, blockUI) {

	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.product = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/products/save';
	$scope.errors = [];
	$scope.module=1;
	$scope.user = {};
	$scope.errors = [];
	$scope.verifyotp = false;
	
	$scope.register =function(user){
		blockUI.start();
		$scope.errors = [];
			
			if($scope.user.username===null || typeof $scope.user.username === 'undefined' || $scope.user.username === ""){
				$scope.errors.push({"defaultMessage" : "User Name can not be left empty" });
				return;
			}else{$scope.isEmptyUser = null;}
			if($scope.user.password === null || typeof $scope.user.password === 'undefined' || $scope.user.password === ""){
				$scope.errors.push({"defaultMessage" : "Password field can not be left empty" });
				return;
			}else{$scope.isEmptyPassword=null;}
			if($scope.user.person === null || typeof $scope.user.person === 'undefined' || $scope.user.person ==="" ){
				$scope.errors.push({"defaultMessage" :  "Person Name can not be left empty" });
				return;
			}else{$scope.isEmptyPerson=null;}
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
			if($scope.user.companyName===null || typeof $scope.user.companyName === 'undefined' ||$scope.user.companyName ==="" ){
				$scope.errors.push({"defaultMessage" :  "Company Name can not be left empty" });
				return;
			}else{$scope.isEmptyComapany=null;}
			if($scope.user.address===null ||typeof  $scope.user.address === 'undefined' || $scope.user.address === ""){
				$scope.errors.push({"defaultMessage" : "Address can not be left empty" });
				return;
			}else{$scope.isEmptyAddress =null;}
			
			function validateEmail(email) 
			{
				var regExpEmail = /\S+@\S+\.\S+/;
				return regExpEmail.test(email);
			}
			
			var file = $scope.user.logo;
			$scope.user.logo = null;
			var fd = new FormData();
			fd.append("file", file );
			fd.append("user", angular.toJson($scope.user));
			$http.post('/register/create', fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined }
			}).success(function(data, status, headers, config) {
				if(data.success){
				//success message 
				//	$window.location.href = "/login";
			    //redirect to login Page
//					$scope.user = data.data[0];
					blockUI.stop();
					$("#logina").click();
					$scope.verifyotp = true;
				} 
				else {
				}
			});	
		  }
	$scope.isExistUser= function() {
		$http.post('/register/isuserexist',$scope.user).
		success(function(data, status, headers, config) {
			if(data.success){
				$scope.errors.push({"defaultMessage":"Username is Already Exist Choose Another One."});
			} else {
				$scope.errors = [];
			}
		});	
	}
	
	$scope.isExistEmail= function() {
		$http.post('/register/isemailexist',$scope.user).
		success(function(data, status, headers, config) {
			if(data.success){
				$scope.errors.push({"defaultMessage":"Email Is Already Exist Choose Another One ."});
			} else {
				$scope.errors = [];
			}
		});	
	}
	
	$scope.sendOtp = function(user){
		
	}
	
	
}]);

regapp.directive('fileModel', ['$parse', function ($parse) {
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