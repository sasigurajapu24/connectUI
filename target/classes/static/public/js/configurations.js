app.controller('configurationsCtrl', ['$rootScope', '$scope', '$http','menuService', 'blockUI' ,function($rootScope, $scope, $http,menuService, blockUI) {
	menuService.setModule(9);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.product = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/products/save';
	$scope.nameerror = '';
	$scope.module=9;
	$scope.types = [{'id':1, 'name':'Apparel'}, {'id':2, 'name':'Footwear'}, {'id':3, 'name':'Accessories'}, {'id':4, 'name':'Handbags'} , {'id':5, 'name':'Other'}];
	$scope.subtypes = { 1 : [{'id':1, 'name':'Shirts'}, {'id':2, 'name':'Trousers'}, {'id':3, 'name':'T-Shirts'}, {'id':4, 'name':'Jeans'} , {'id':5, 'name':'Other'}] ,
						2 : [{'id':6, 'name':'Shoes'}, {'id':7, 'name':'Sandals'}, {'id':8, 'name':'Loafers'}, {'id':9, 'name':'Flip-Flops'} , {'id':10, 'name':'Flats'} ],
						3 : [{'id':11, 'name':'Apparel'}, {'id':12, 'name':'Footwear'}, {'id':13, 'name':'Accessories'}, {'id':14, 'name':'Handbags'} , {'id':15, 'name':'Other'} ],
						4 : [{'id':16, 'name':'Apparel'}, {'id':17, 'name':'Footwear'}, {'id':18, 'name':'Accessories'}, {'id':19, 'name':'Handbags'} , {'id':20, 'name':'Other'} ]	};

	$scope.getCategories = function(val) { 
		$http.get("/configs/getcategories/")
		.success(function(response) {
			$scope.categories = response;
		});
	}
	
	$scope.getDivisions = function(val) { 
		$http.get("/configs/getdivisions/")
		.success(function(response) {
			$scope.divisions = response;
		});
	}
	
	$scope.getBrands = function(val) { 
		$http.get("/configs/getbrands/")
		.success(function(response) {
			$scope.brands = response;
		});
	}

	$scope.getDivisionsByCategory = function(val) { 
		if(val != null)
		$http.get("/products/types/"+val.id)
		.success(function(response) {
			$scope.types = response;
		});
	}
	
	$scope.getAllCategories = function(val) { 
		$http.get("/products/categories/")
		.success(function(response) {
			$scope.allcategories = response;
		});
	}
	
//	$scope.brands = [{"id" : 1, "name" : "Sony"}, {"id" : 2,"name" : "Bose"}, {"id" : 3,"name" : "HP"}, {"id" : 4,"name" : "Adidas"}, {"id" : 5,"name" : "Fog"}, 
//	                 {"id" : 6,"name" : "Image"}, {"id" : 7,"name" : "4me"}, {"id" : 8,"name" : "puma"}, {"id" : 9,"name" : "killy"}];
	
	$scope.pageChanged = function(url) {
		var value = $scope.toggle;
		var page = $scope.currentPage-1;
		var size = $scope.products.size;
		$http.get(url+"page="+page+"&size="+size)
		.success(function(response) {
			$scope.products = response;
			$scope.currentPage = $scope.products.number+1;
		});
	};
	
	$scope.checkDuplicate = function(url, val) { 
		$http.get(url+"/"+val)
		.success(function(response) {
			$scope.nameerror ='';
		}).error(function(response){
			$scope.nameerror = response;
		});
	}
	
	$scope.add = function(url) {
		$scope.screen = 'Save';
		$scope.product = {};
		$scope.action = url;
		$scope.errors = {};
	}

	$scope.edit = function(code, url, type) {
		if(type === 'division'){
			$scope.division = code;
		}
		else if(type === 'brand'){
			$scope.getDivisionsByCategory(code.category);
			$scope.brand = code;
		} else if(type === 'category'){
			$scope.category = code;
		}
		$scope.screen = 'Update';
		$scope.action = url;
		$scope.errors = {};
	}

	$scope.view = function(code, type) {
		if(type === 'division'){
			$scope.division = code;
		}
		else if(type === 'brand'){
			$scope.getDivisionsByCategory(code.category);
			$scope.brand = code;
		} else if(type === 'category'){
			$scope.category = code;
		}
		$scope.screen = 'View';
		$scope.product = code;
		$scope.errors = {};
	}

	$scope.reset = function(obj) {
		$scope.search = {};
		obj = {};
		$scope.errors = {};
	}
	
//	$scope.uploadedFile = function(element) {
//		 $scope.$apply(function($scope) {
//		   $scope.files = element.files;         
//		 });
//		}

	$scope.save = function(screen, object) {
		
		$http.post($scope.action, object ).success(function(data, status, headers, config) {
					$scope.getCategories();
					$scope.getDivisions();
					$scope.getBrands();
					$scope.getAllCategories();
					$scope.screen = 'list';
					object = {};
					$scope.errors = {};
			}).error(function(error){
				$scope.errors = error;
			});
	}
	
	$scope.search = function(url) {
		$scope.spinner=true
		$http.post('/products/search', $scope.search).
		success(function(data, status, headers, config) {
			$scope.products = data;
			$scope.spinner=false;
		});
	}
	
	$(".codetype").change(function(){
		alert($(this).attr("idx"));
		if($(this).val() == 1 ) {
			//$("#")
		} else {
			
		}
	});
	
	$scope.getCategories();
	$scope.getDivisions();
	$scope.getBrands();
	$scope.getAllCategories();
}]);