'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', ['ngResource'])
.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
])
.value('version', '0.1')
.factory('CustomerDataService', ['$resource', function($resource){
	return $resource('data/:customerId.json', {}, {
		query: {method:'GET', params:{customerId:'customers'}, isArray:true}
	})
}]);