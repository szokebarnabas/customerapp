'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
	'ngRoute',
	'myApp.filters',
	'myApp.services',
	'myApp.directives',
	'myApp.controllers',
	]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/customers', {templateUrl: '/partials/CustomerList.html', controller: 'CustomerListCtrl'});
	$routeProvider.when('/customers/new', {templateUrl: '/partials/NewCustomer.html', controller: 'NewCustomerCtrl'});
	$routeProvider.when('/customers/:customerId/edit', {templateUrl: '/partials/EditCustomer.html', controller: 'EditCustomerCtrl'});
	$routeProvider.when('/customers/:customerId/delete', {templateUrl: '/partials/EditCustomer.html', controller: 'DeleteCustomerCtrl'});
	$routeProvider.when('/customers/:customerId', {templateUrl: '/partials/CustomerDetails.html', controller: 'CustomerDetailsCtrl'});
	$routeProvider.otherwise({redirectTo: '/customers'});
}]);
