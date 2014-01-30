'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', ['ngResource']).
value('version', '0.1')
.factory('CustomerDataService', ['$resource', function($resource){
	return $resource('data/:phoneId.json', {}, {
		query: {method:'GET', params:{phoneId:'customers'}, isArray:true}
	})
}]);