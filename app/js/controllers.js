'use strict';

/* Controllers */

angular.module('myApp.controllers', ['ngGrid']).
controller('CustomerListCtrl', ['$scope', function($scope) {
	$scope.myData = [
        { id: 'T08', name: 'INTERNET RBU', prev: '9540', actual: '10998', rxDelta: '1458', ms: '100', msDelta: '0', growth: '15.3' },
        { id: 'T03', name: 'QUEBEC RBU', prev: '3841601', actual: '4274441', rxDelta: '432840', ms: '100', msDelta: '0', growth: '11.3' },
        { id: 'T04', name: 'ATLANTIC RBU', prev: '419693', actual: '436691', rxDelta: '16998', ms: '100', msDelta: '0', growth: '4.1' },
        { id: 'T01', name: 'ONTARIO RBU', prev: '3044133', actual: '3465951', rxDelta: '421818', ms: '100', msDelta: '0', growth: '13.9' },
        { id: 'T02', name: 'WEST RBU', prev: '1521840', actual: '1581739', rxDelta: '59899', ms: '100', msDelta: '0', growth: '3.9' }
    ];
    
    $scope.gridOptions = { 
        data: 'myData',
        columnDefs: [
            { field: 'id', displayName: 'Geo Id', width: '46px', cellClass:'align-center' },
            { field:'name', displayName:'Geo Name' },
            { field:'prev', displayName:'Rx LY', cellClass: 'align-right' },
            { field:'actual', displayName:'Rx', cellClass: 'align-right' },
            { field:'rxDelta', displayName:'Delta Rx', cellClass: 'align-right' },
            { field:'ms', displayName:'Rx MS %', cellClass: 'align-right' },
            { field:'msDelta', displayName:'Delta Rx MS', cellClass: 'align-right' },
            { field:'growth', displayName:'Growth %', cellClass: 'align-right' }
        ]
    };
}])
.controller('NewCustomerCtrl', [function() {

}]);