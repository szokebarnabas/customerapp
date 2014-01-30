'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ngTable']).
controller('CustomerListCtrl', ['$scope', 'CustomerDataService', function($scope, CustomerDataService, ngTableParams) {
	$scope.customers = CustomerDataService.query();
	$scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10           // count per page
    });
}])
.controller('NewCustomerCtrl', [function() {

}]);