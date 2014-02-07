'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', ['ngResource'])
.value('version', '0.1')
.factory('CustomerDataService', ['$resource', function($resource){
	return $resource('api/customers/:customerId/:command', {customerId:'', command:''}, {
		query: {method:'GET', isArray:true},
		update: { method: 'PUT' }
	})  
}]);