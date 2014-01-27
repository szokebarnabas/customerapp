'use strict';

/* Controllers */

angular.module('myApp.controllers', []).
controller('CustomerListCtrl', ['$scope', 'Books', function($scope, Books) {
	Books.get(function(data){
		$scope.customers = data;
	});
}])
.controller('NewCustomerCtrl', [function() {

}]);