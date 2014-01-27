'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
	'ngRoute',
	'myApp.filters',
	'myApp.services',
	'myApp.directives',
	'myApp.controllers'
	]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/customers/list', {templateUrl: 'partials/customerlist.html', controller: 'CustomerListCtrl'});
	$routeProvider.when('/customers/new', {templateUrl: 'partials/newcustomer.html', controller: 'NewCustomerCtrl'});
	$routeProvider.otherwise({redirectTo: '/customers/list'});
}]);
