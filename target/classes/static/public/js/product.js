app.controller('productCtrl', ['$rootScope', '$scope', '$http','menuService', 'blockUI' ,function($rootScope, $scope, $http,menuService, blockUI) {
	menuService.setModule(1);
	$scope.screen = 'list';
	$scope.search = {};
	$scope.spinner = false;
	$scope.product = {};
	$scope.size=10;
	$scope.currentPage = 1;
	$scope.action = '/products/save';
	$scope.nameerror = '';
	$scope.module=1;
	$scope.types = [{'id':1, 'name':'Apparel'}, {'id':2, 'name':'Footwear'}, {'id':3, 'name':'Accessories'}, {'id':4, 'name':'Handbags'} , {'id':5, 'name':'Other'}];
	$scope.subtypes = { 1 : [{'id':1, 'name':'Shirts'}, {'id':2, 'name':'Trousers'}, {'id':3, 'name':'T-Shirts'}, {'id':4, 'name':'Jeans'} , {'id':5, 'name':'Other'}] ,
						2 : [{'id':6, 'name':'Shoes'}, {'id':7, 'name':'Sandals'}, {'id':8, 'name':'Loafers'}, {'id':9, 'name':'Flip-Flops'} , {'id':10, 'name':'Flats'} ],
						3 : [{'id':11, 'name':'Apparel'}, {'id':12, 'name':'Footwear'}, {'id':13, 'name':'Accessories'}, {'id':14, 'name':'Handbags'} , {'id':15, 'name':'Other'} ],
						4 : [{'id':16, 'name':'Apparel'}, {'id':17, 'name':'Footwear'}, {'id':18, 'name':'Accessories'}, {'id':19, 'name':'Handbags'} , {'id':20, 'name':'Other'} ]	};

	$scope.loadProducts = function(value) {
		blockUI.start();
		var url = "/products/users";
		if(userType == 'admin'){
			url = (value == undefined || value == -1) ? "/products" : "/products/findbyentrypoint?entrypoint="+value  ;
		}
		$http.get(url)
		.success(function(response) {
			$scope.products = response;
			$scope.currentPage = $scope.products.number+1;
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
		if($scope.product.category != null)
		$http.get("/products/types/"+$scope.product.category.id)
		.success(function(response) {
			$scope.types = response;
		});
	}
	
	
	$scope.getBrands = function(val) { 
		if($scope.product.division != null)
		$http.get("/products/brands/"+$scope.product.division.id)
		.success(function(response) {
			$scope.brands = response;
		});
	}
	
//	$scope.brands = [{"id" : 1, "name" : "Sony"}, {"id" : 2,"name" : "Bose"}, {"id" : 3,"name" : "HP"}, {"id" : 4,"name" : "Adidas"}, {"id" : 5,"name" : "Fog"}, 
//	                 {"id" : 6,"name" : "Image"}, {"id" : 7,"name" : "4me"}, {"id" : 8,"name" : "puma"}, {"id" : 9,"name" : "killy"}];
	
	$scope.pageChanged = function() {
		var value = $scope.toggle;
		var url = "/products/users";
		if(userType == 'admin'){
			url = (value == undefined || value == -1) ? "/products?" : "/products/findbyentrypoint?entrypoint="+value+"&"  ;
		}
		var page = $scope.currentPage-1;
		var size = $scope.products.size;
		$http.get(url+"page="+page+"&size="+size)
		.success(function(response) {
			$scope.products = response;
			$scope.currentPage = $scope.products.number+1;
		});
	};
	
	$scope.checkName = function(val, type) { 
		
		$http.get("/products/checkunique/"+type+"/"+val)
		.success(function(response) {
				if(response == true){
					if(type === 'name'){
						$scope.nameerror = 'Product name already exists!!';
					}else { 
						$scope.nameerror = 'Product model already exists!!';
					}
					
				}else{
					$scope.nameerror ='';
				}
		});
	}
	
	$scope.loadCompanies =function() { 
		$http.get("/products/companies")
			.success(function(response) {
			$scope.companies = response;
		});
	}


	$scope.addProduct = function() {
		$scope.screen = 'Save';
		$scope.product = {};
		$scope.action = '/products/save';
		$scope.errors = {};
	}

	$scope.editProduct = function(code) {
		$scope.screen = 'Update';
		$scope.product= code;
		$scope.getTypes();
		$scope.action = '/products/update';
		$scope.errors = {};
	}

	$scope.viewProduct = function(code) {
		$scope.screen = 'View';
		$scope.product = code;
		$scope.errors = {};
	}

	$scope.resetproduct = function() {
		$scope.search = {};
		$scope.searchproduct();
		$scope.errors = {};
	}
	
//	$scope.uploadedFile = function(element) {
//		 $scope.$apply(function($scope) {
//		   $scope.files = element.files;         
//		 });
//		}

	$scope.saveProduct = function(screen, product) {
		
		
		if($scope.product.category ===null || typeof $scope.product.category === 'undefined' || $scope.product.category === ""){
			$scope.isCategoryEmpty="Product category can not be left empty";
			return;
		}else{$scope.isCategoryEmpty = null;}
		if($scope.product.division ===null || typeof $scope.product.division === 'undefined' || $scope.product.division === ""){
			$scope.isDivisionEmpty="Product division can not be left empty";
			return;
		}else{$scope.isDivisionEmpty = null;}
		if($scope.product.name ===null || typeof $scope.product.name === 'undefined' || $scope.product.name === ""){
			$scope.isNameEmpty="Product Name can not be left empty";
			return;
		}else{$scope.isNameEmpty = null;}
		
		if($scope.product.brand ===null || typeof $scope.product.brand === 'undefined' || $scope.product.brand === ""){
			$scope.isBrandEmpty="Product brand can not be left empty";
			return;
		}else{$scope.isBrandEmpty = null;}
		/*if($scope.product.file ===null || typeof $scope.product.file === 'undefined' || $scope.product.file === ""){
			$scope.isLogoEmpty="Product Logo can not be left empty";
			return;
		}else{$scope.isLogoEmpty = null;}
		*/
		if($scope.product.modelNumber ===null || typeof $scope.product.modelNumber === 'undefined' || $scope.product.modelNumber === ""){
			$scope.isModelNumberEmpty="Model number can not be left empty";
			return;
		}else{$scope.isModelNumberEmpty = null;}
		
		/*if($scope.product.price ===null || typeof $scope.product.price === 'undefined' || $scope.product.price === ""){
			$scope.isPriceEmpty="price can not be left empty";
			return;
		}else{$scope.isPriceEmpty = null;}*/
		
//		
//		if($scope.product.points ===null || typeof $scope.product.points === 'undefined' || $scope.product.points === ""){
//			$scope.isPointEmpty="Points / Scam can not be left empty";
//			return;
//		}else{$scope.isPointEmpty = null;}
//		
//		if($scope.product.pointsMsg ===null || typeof $scope.product.pointsMsg === 'undefined' || $scope.product.pointsMsg === ""){
//			$scope.isPointMsgEmpty="Points Message can not be left empty";
//			return;
//		}else{$scope.isPointMsgEmpty = null;}
//		
//		if($scope.product.targetPoints ===null || typeof $scope.product.targetPoints === 'undefined' || $scope.product.targetPoints === ""){
//			$scope.isTargetPointMsg="Target Point can not be left empty";
//			return;
//		}else{$scope.isTargetPointMsg = null;}
//		
//		if($scope.product.targetMsg ===null || typeof $scope.product.targetMsg === 'undefined' || $scope.product.targetMsg === ""){
//			$scope.isTargetMsgEmpty="Target Message can not be left empty";
//			return;
//		}else{$scope.isTargetMsgEmpty = null;}
		
		//product.file = $scope.files[0];
		var file = $scope.file;
		var fd = new FormData();
		if(null != file){
			fd.append("file", file);
		}
		var img = $scope.image;
		if(null != img){
			fd.append("image", img);
		}
		$scope.product.file = null;
		$scope.product.image = null;
		fd.append("product", angular.toJson($scope.product));
		$http.post($scope.action, fd, {
			transformRequest : angular.identity,
			headers : {
			'Content-Type' : undefined }
			}).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.loadProducts();
					$scope.screen = 'list';
					$scope.product = {};
					$scope.errors = {};
					$scope.file = null;
					$scope.image = null;
				}else{
					$scope.errors = data.errors;
				}
			}).error(function(error){
				$scope.errors = error;
			});
//		$http.post('/products/'+screen.toLowerCase(), product, {transformRequest: function(data, headersGetterFunction) {
//            return data;
//        }, headers : {
//		    'Content-Type' : 'multipart/form-data'
//		   }}).
		
	}
	
	$scope.searchProduct = function() {
		$scope.spinner=true
		$http.post('/products/search', $scope.search).
		success(function(data, status, headers, config) {
			$scope.products = data;
			$scope.spinner=false;
		});
	}
	
	//$scope.minDate = new Date();
	
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
	
	$scope.toggleModal1 = function(product) {
	    $scope.modalShown1 = !$scope.modalShown1;
	};
	
	$scope.modalShown = false;
	$scope.productid = 0;
	$scope.toggleModal = function(product) {
		if(0 === product){
			$scope.productid = 0;
		} else { 
			$scope.productid = product.id;
			if(null == product.productOffer || product.productOffer.length == 0){
				$scope.offer = {};
				$scope.offer['productId'] = product.id;
			}else{
				$scope.offer = product.productOffer[0];
			}
		} 
	    $scope.modalShown = !$scope.modalShown;
	};
	$scope.offer = {};
	$scope.saveOffer = function() {
		
		if($scope.offer.name ===null || typeof $scope.offer.name === 'undefined' || $scope.offer.name === ""){
			$scope.isOfferNameEmpty="Offer Name can not be left empty";
			return;
		}else{$scope.isOfferNameEmpty = null;}
		
		if($scope.offer.description ===null || typeof $scope.offer.description === 'undefined' || $scope.offer.description === ""){
			$scope.isOfferDescEmpty="Offer Description can not be left empty";
			return;
		}else{
			if($scope.offer.description.length <= 1000){
				$scope.isOfferDescEmpty = null;
			}else{
				$scope.isOfferDescEmpty="Offer Description can not be morethan 1000 characters";
			}
		}
		
		if($scope.offer.startDate ===null || typeof $scope.offer.startDate === 'undefined' || $scope.offer.startDate === ""){
			$scope.isOfferStartDateEmpty="Offer StartDate can not be left empty";
			return;
		}else{$scope.isOfferStartDateEmpty = null;}
		
		
		if($scope.offer.endDate ===null || typeof $scope.offer.endDate === 'undefined' || $scope.offer.endDate === ""){
			$scope.isOfferEndDateEmpty="Offer EndDate can not be left empty";
			return;
		}else{$scope.isOfferEndDateEmpty = null;}
		
		
		
		isOfferStartDateEmpty!=null
		
		$http.post('/products/saveoffer', $scope.offer).success(function(data, status, headers, config) {
				if(data.success) {
					$scope.screen = 'list';
					$scope.errors = {};
					$scope.offer = {};
					$scope.loadProducts();
					$scope.modalShown = !$scope.modalShown;
				}else{
					$scope.errors = data.errors;
				}
			});
//		$http.post('/products/'+screen.toLowerCase(), product, {transformRequest: function(data, headersGetterFunction) {
//            return data;
//        }, headers : {
//		    'Content-Type' : 'multipart/form-data'
//		   }}).
		
	}
	
	$(".codetype").change(function(){
		alert($(this).attr("idx"));
		if($(this).val() == 1 ) {
			//$("#")
		} else {
			
		}
	});
	
	$scope.loadProducts();
	$scope.loadCompanies();
	$scope.getCategories();
}]);