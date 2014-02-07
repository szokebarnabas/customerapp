'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ngTable'])
.controller('CustomerListCtrl', ['$scope','$filter','ngTableParams','CustomerDataService', function($scope, $filter, ngTableParams, CustomerDataService) {
	$scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10,           // count per page
        sorting: {
            name: 'asc'     // initial sorting
        }
    }, {
        total: 0, // length of data
        getData: function($defer, params) {
        	CustomerDataService.query(function(events){

        		var searchedData = searchData(events);
        		params.total(searchedData.length);

        		var orderedData = params.sorting() ? $filter('orderBy')(searchedData, params.orderBy()) : searchedData;

        		$scope.users = orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count());
            	$defer.resolve($scope.users);   
        	});
        }
    });

	$scope.delete = function(param) {
		alert('delete id:' + param);
	};


	$scope.$watch("searchUser", function () {
		$scope.tableParams.reload();
	});

	var searchData = function(data){
		if($scope.searchUser)
			return $filter('filter')(data,$scope.searchUser);
		return data;
	}

}])
.controller('NewCustomerCtrl', ['$scope','CustomerDataService','$location', function($scope, CustomerDataService, $location) {
	$scope.readMode = false;
	$scope.customer = new CustomerDataService();
	$scope.createNewUser = function() {
		
		$scope.customer.id = $scope.customer.firstName + '_' + $scope.customer.lastName;
		$scope.customer.$save(function() {
			$location.path('/');
		});
		
			/*if ($scope.customerForm.$valid) {
				 CustomerDataService.create($scope.customer);
				 alert('post');
			}*/

		};
	}])
.controller('EditCustomerCtrl', ['$scope','$routeParams','CustomerDataService','$location', function($scope, $routeParams, CustomerDataService, $location) {
	$scope.customer = CustomerDataService.get({customerId: $routeParams.customerId});
	$scope.editUser = function() {
		
		$scope.customer.id = $scope.customer.firstName + '_' + $scope.customer.lastName;
		$scope.customer.$update({customerId: $routeParams.customerId, command: 'modify'}, function() {
			$location.path('/');
		});
	};
}])
.controller('DeleteCustomerCtrl', ['$scope','$routeParams','CustomerDataService','$location', function($scope, $routeParams, CustomerDataService, $location) {
	alert("del. id : " + $routeParams.customerId);
	CustomerDataService.delete({customerId: $routeParams.customerId, command: 'delete'}, function() {
		$location.path('/');
	});
}])
.controller('CustomerDetailsCtrl', ['$scope','$routeParams','CustomerDataService', function($scope, $routeParams, CustomerDataService) {
	$scope.readMode = true;
	$scope.customer = CustomerDataService.get({customerId: $routeParams.customerId});
}]);