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
.controller('NewCustomerCtrl', ['$scope', function($scope) {
	$scope.submitForm = function() {

			// check to make sure the form is completely valid
			if ($scope.customerForm.$valid) {
				alert('our form is amazing');
			}

		};
	}])
.controller('EditCustomerCtrl', ['$scope','$routeParams', function($scope, $routeParams) {
	$scope.customerId = $routeParams.customerId;
}])
.controller('CustomerDetailsCtrl', ['$scope','$routeParams','CustomerDataService', function($scope, $routeParams, CustomerDataService) {
	//$scope.customerId = $routeParams.customerId;
	$scope.readMode = true;
	$scope.customer = CustomerDataService.get({customerId: 'customer' + $routeParams.customerId});
}]);