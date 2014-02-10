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
}])
.factory('Account', ['$resource',
    function ($resource) {
        return $resource('api/rest/account', {}, {
        });
    }])

.factory('Password', ['$resource',
    function ($resource) {
        return $resource('api/rest/account/change_password', {}, {
        });
    }])

.factory('Sessions', ['$resource',
    function ($resource) {
        return $resource('api/rest/account/sessions/:series', {}, {
            'get': { method: 'GET', isArray: true}
        });
    }])


.factory('AuthenticationSharedService', ['$rootScope', '$http', 'authService',
                                                    function ($rootScope, $http, authService) {
                                                        return {
                                                            authenticate: function() {
                                                                $http.get('api/rest/authenticate')
                                                                    .success(function (data, status, headers, config) {
                                                                        $rootScope.login = data;
                                                                        if (data == '') {
                                                                            $rootScope.$broadcast('event:auth-loginRequired');
                                                                        } else {
                                                                            $rootScope.$broadcast('event:auth-authConfirmed');
                                                                        }
                                                                    })
                                                            },
                                                            login: function (param) {
                                                                var data ="j_username=" + param.username +"&j_password=" + param.password +"&_spring_security_remember_me=" + param.rememberMe +"&submit=Login";
                                                                $http.post('api/authentication', data, {
                                                                    headers: {
                                                                        "Content-Type": "application/x-www-form-urlencoded"
                                                                    },
                                                                    ignoreAuthModule: 'ignoreAuthModule'
                                                                }).success(function (data, status, headers, config) {
                                                                    $rootScope.authenticationError = false;
                                                                    authService.loginConfirmed();
                                                                    if(param.success){
                                                                        param.success(data, status, headers, config);
                                                                    }
                                                                }).error(function (data, status, headers, config) {
                                                                    console.log("auth error");
                                                                    $rootScope.authenticationError = true;
                                                                    if(param.error){
                                                                        param.error(data, status, headers, config);
                                                                    }
                                                                });
                                                            },
                                                            logout: function () {
                                                                $rootScope.authenticationError = false;
                                                                $http.get('api/logout')
                                                                    .success(function (data, status, headers, config) {
                                                                        $rootScope.login = null;
                                                                        authService.loginCancelled();
                                                                    });
                                                            }
                                                        };
                                                    }]);