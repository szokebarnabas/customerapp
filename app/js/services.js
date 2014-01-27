'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', []).
value('version', '0.1')
.factory('Books', ['$http', function($http){
	return{
		get: function(callback){
			$http.get('data/customers.json').success(function(data) {
          // prepare data here
          callback(data);
      });
		}
	};
}]);