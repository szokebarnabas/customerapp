'use strict';

/* Directives */


angular.module('myApp.directives', [])
  .directive( 'goClick', function ( $location ) {
  return function ( scope, element, attrs ) {
    var path;

    attrs.$observe( 'goClick', function (val) {
      path = val;
    });

    element.bind( 'click', function () {
      scope.$apply( function () {
        $location.path( path );
      });
    });
  };
})
.directive('myLoadingSpinner', function() {
    return {
      restrict: 'A',
      replace: true,
      transclude: true,
      scope: {
        loading: '=myLoadingSpinner'
      },
      templateUrl: 'partials/loading.html',
      link: function(scope, element, attrs) {
    	var opts = {
    			  lines: 13, // The number of lines to draw
    			  length: 5, // The length of each line
    			  width: 2, // The line thickness
    			  radius: 8, // The radius of the inner circle
    			  corners: 1, // Corner roundness (0..1)
    			  rotate: 0, // The rotation offset
    			  direction: 1, // 1: clockwise, -1: counterclockwise
    			  color: '#000', // #rgb or #rrggbb or array of colors
    			  speed: 1.4, // Rounds per second
    			  trail: 60, // Afterglow percentage
    			  shadow: false, // Whether to render a shadow
    			  hwaccel: false, // Whether to use hardware acceleration
    			  className: 'spinner', // The CSS class to assign to the spinner
    			  zIndex: 2e9, // The z-index (defaults to 2000000000)
    			  top: 'auto', // Top position relative to parent in px
    			  left: 'auto' // Left position relative to parent in px
    			};
    	var loadingContainer = element.find('.my-loading-spinner-container')[0];
        var spinner = new Spinner(opts).spin(loadingContainer);
        //loadingContainer.appendChild(spinner.el);
      }
    };
  });
