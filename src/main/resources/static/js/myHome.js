var app=angular.module("myHome",[])
  .controller('myHomeCtrl', ['$scope', '$window', function($scope, $window) {
	  
	  
	  $scope.logo = {
		    src: 'images/LogoVizeo.png',name: 'logoVizeo'
		  };
	  
	  
	  
	  
	 
	  $scope.redirect = function() {
		  $window.location.href = '/view.html';
    };
}]);
