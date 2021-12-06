//var app = angular.module('menuapp', ['ui.bootstrap', 'blockUI'], function ($compileProvider) {
//    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file):/);
//});
app.controller('menuCtrl', ['$rootScope', '$scope', '$http','menuService', 'blockUI' ,function($rootScope, $scope, $http,menuService, blockUI) {
	menuService.setModule(10);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.option = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/menuoptions/save';
	$scope.nameerror = '';
	$scope.module=1;

	$scope.loadProducts = function() {
		blockUI.start();
		var url = "/menuoptions/all";
		$http.get(url)
		.success(function(response) {
			$scope.options = response;
			$scope.currentPage = $scope.options.number+1;
			$scope.errors = {};
			blockUI.stop();
		});
	}
	$scope.getCategories = function(val) { 
		$http.get("/products/categories/")
		.success(function(response) {
			$scope.categories = response;
		});
	}
	
	$scope.getTypes = function(val) { 
		if($scope.option.category != null)
		$http.get("/products/types/"+$scope.option.category.id)
		.success(function(response) {
			$scope.types = response;
		});
	}
	
	$scope.getBrands = function(val) { 
		if($scope.option.division != null)
		$http.get("/products/brands/"+$scope.option.division.id)
		.success(function(response) {
			$scope.brands = response;
		});
	}
	
	$scope.getParents = function(){
		if($scope.option.brand != null)
			$http.get("/menuoptions/parentsByBrand/"+$scope.option.brand.id)
			.success(function(response) {
				$scope.parentOptions = response;
		});
	}
	
	$scope.pageChanged = function() {
		var url = "/menuoptions/all";
		var page = $scope.currentPage-1;
		var size = $scope.options.size;
		$http.get(url+"?page="+page+"&size="+size)
		.success(function(response) {
			$scope.options = response;
			$scope.currentPage = $scope.options.number+1;
		});
	};
	
	$scope.loadCompanies =function() { 
		$http.get("/products/companies")
			.success(function(response) {
			$scope.companies = response;
		});
	}

	$scope.addOption = function() {
		$scope.screen = 'Save';
		$scope.option = {};
		$scope.action = '/menuoptions/save';
		$scope.errors = {};
	}

	$scope.editOption = function(code) {
		$scope.screen = 'Update';
		$scope.option= code;
		$scope.action = '/menuoptions/update';
		$scope.errors = {};
	}

	$scope.viewOption = function(code) {
		$scope.screen = 'View';
		$scope.option = code;
		$scope.errors = {};
	}

	$scope.resetOption = function() {
		$scope.search = {};
		$scope.searchoption();
		$scope.errors = {};
	}
	
	$scope.saveOption = function(screen, code) {
		$scope.option = code;
		$http.post($scope.action, code).success(function(data, status, headers, config) {
//			if(data.success) {
				$scope.loadProducts();
				$scope.screen = 'list';
				$scope.option = {};
//				menuService.setSuccess("Code saved successfully.");
//			}else{
//				$scope.errors = data.errors; 
////				menuService.setErrors(data.errors);
//			}
		});
		
	}
	
	$scope.searchOption = function() {
		$scope.spinner=true
		$http.post('/menuoptions/search', $scope.search).
		success(function(data, status, headers, config) {
			$scope.options = data;
			$scope.spinner=false;
		});
	}
	
	$scope.minDate = new Date();
	
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
	
	
	$scope.modalShown = false;
	$scope.toggleModal = function(product) {
		if(null == product.productOffer || product.productOffer.length == 0){
			$scope.offer = {};
			$scope.offer['productId'] = product.id;
		}else{
			$scope.offer = product.productOffer[0];
		}
	    $scope.modalShown = !$scope.modalShown;
	};
	$scope.loadProducts();
	$scope.getCategories();
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

