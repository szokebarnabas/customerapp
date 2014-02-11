'use strict';

// Declare app level module which depends on filters, and services
angular.module(
		'myApp',
		[ 'http-auth-interceptor', 'ngResource', 'ngRoute', 'myApp.filters',
				'myApp.services', 'myApp.directives', 'myApp.controllers', ])

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/customers', {
		templateUrl : '/partials/CustomerList.html',
		controller : 'CustomerListCtrl'
	});
	$routeProvider.when('/customers/new', {
		templateUrl : '/partials/NewCustomer.html',
		controller : 'NewCustomerCtrl'
	});
	$routeProvider.when('/customers/:customerId/edit', {
		templateUrl : '/partials/EditCustomer.html',
		controller : 'EditCustomerCtrl'
	});
	$routeProvider.when('/customers/:customerId/delete', {
		templateUrl : '/partials/EditCustomer.html',
		controller : 'DeleteCustomerCtrl'
	});
	$routeProvider.when('/customers/:customerId', {
		templateUrl : '/partials/CustomerDetails.html',
		controller : 'CustomerDetailsCtrl'
	});
	$routeProvider.when('/account/login', {
		templateUrl : '/partials/login.html',
		controller : 'LoginController'
	});
	$routeProvider.when('/account/logout', {
         templateUrl: '/partials/main.html',
         controller: 'LogoutController'
     })
	$routeProvider.otherwise({
		templateUrl: '/partials/main.html',
        controller: 'MainController'
	});
} ])
.run(['$rootScope', '$location', 'AuthenticationSharedService', 'Account',
            function($rootScope, $location, AuthenticationSharedService, Account) {
            $rootScope.hasRole = function(role) {
                if ($rootScope.account === undefined) {
                    return false;
                }

                if ($rootScope.account.roles === undefined) {
                    return false;
                }

                if ($rootScope.account.roles[role] === undefined) {
                    return false;
                }

                return $rootScope.account.roles[role];
            };

            $rootScope.$on("$routeChangeStart", function(event, next, current) {
                // Check if the status of the user. Is it authenticated or not?
                AuthenticationSharedService.authenticate({}, function() {
                    $rootScope.authenticated = true;
                });
            });

            // Call when the 401 response is returned by the client
            $rootScope.$on('event:auth-loginRequired', function(rejection) {
                $rootScope.authenticated = false;
                if ($location.path() !== "/" && $location.path() !== "") {
                    $location.path('/account/login').replace();
                }
            });

            // Call when the user is authenticated
           $rootScope.$on('event:auth-authConfirmed', function() {
               $rootScope.authenticated = true;
               $rootScope.account = Account.get();

               // If the login page has been requested and the user is already logged in
               // the user is redirected to the home page
               if ($location.path() === "/account/login") {
                   $location.path('/').replace();
               }
            });

            // Call when the user logs in
            $rootScope.$on('event:auth-loginConfirmed', function() {
                $rootScope.authenticated = true;
                $rootScope.account = Account.get();
                $location.path('').replace();
            });

            // Call when the user logs out
            $rootScope.$on('event:auth-loginCancelled', function() {
                $rootScope.authenticated = false;
                $location.path('');
            });
        }]);
