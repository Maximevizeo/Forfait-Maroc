var app = angular.module("myBmApp", [ 'ui.router', 'home']);


app.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state('recherche', {
		url : '/recherche',
		templateUrl : 'views/recherche.html',
		controller : 'myCtrl'
	})
	$stateProvider.state('newCandidat', {
		url : '/newCandidat',
		templateUrl : 'views/newCandidat.html',
		controller : 'newCandidatCtrl'
	})
	$stateProvider.state('newUser', {
		url : '/newUser',
		templateUrl : 'views/newUser.html',
		controller : 'newUserCtrl'
	})
});



app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
 



app.service('fileUpload', [ '$http', function($http) {
	this.uploadFileToUrl = function(file, uploadUrl) {
		var fd = new FormData;

		fd.append('uploadfile', file);
		for (var pair of fd.entries()) {
		    console.log(pair[0]+ ', ' + pair[1]); 
		}
		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : 'multipart/form-data'
			}

		})

		.then(function() {

		})

	}
} ]);


app.controller('newCandidatCtrl', [
		'$scope',
		'$http',
		'$window',
		'fileUpload',
		function($scope, $http, $window, fileUpload) {
			$scope.candidat = {};
			$scope.mode = 0;
			
			$scope.dispo = [ "ASAP", "1 à 3 mois", ">3 mois" ];
			$scope.situation = [ "En mission", "En recherche" ];
			$scope.type = [ "Salarié", "indépendant", "sous-traitant" ];
			$scope.save = function() {
				$http.post('http://localhost:8080/candidats', $scope.candidat)
						.then(function(data) {
							$scope.mode = 1;

						})
			};

			$scope.modeForm = function() {
				$scope.candidat = {};
				$scope.mode = 0;
			};
			
			$scope.uploadFile = function(){
		        var file = $scope.myFile;
		        console.log('file is ' );
		        console.dir(file);
		        var uploadUrl = "/uploadfile";
		        fileUpload.uploadFileToUrl(file, uploadUrl);
		    };
}]);


app.controller('newUserCtrl', function($scope, $http) {
	$scope.user = {};
	$scope.role = null;
	$scope.save = function() {
		$http.post('http://localhost:8080/addUser', $scope.user).then(
				function(data) {
					$scope.user = data;
					$scope.addRole();
				})
	}

	$scope.addRole = function() {
		$http.post(
				"http://localhost:8080/addRoleToUser?username="
						+ $scope.user.username + "&role=" + $scope.role).then(
				function(content) {
					$scope.user = content;
				})
	}
});

app.controller("myCtrl", ['$scope','$http','$window' ,function($scope, $http, $window ) {
	
	$scope.pageCandidats = null;
	$scope.motCle = "";
	$scope.pages = [];
	$scope.mode = {};
	$scope.mode.value = 0;
	$scope.data = {};
	$scope.candidat={};
      
    $scope.sortType = 'name'; // set the default sort type
	$scope.sortReverse = false; // set the default sort order
	$scope.searchFish = ''; // set the default search/filter term
	
	$scope.recherche = function() {
		$http.get(
				"/candidats").then(function(content) {
			$scope.pageCandidats = content;
		})
	}
	
	
	
	$scope.edit = function() {
		//$scope.mode=1;
		var url = "/candidats/"+$scope.data.id+"/";
		var cdt = $scope.candidat.data;
		$http.put(url,cdt,{'Content-Type': 'application/json' })
		.then(function(content) {
			$scope.candidat=content;
		})
	}

}]);

angular.module('home', []).controller('homeCtrl', function($scope, $window) {
	$scope.redirect = function() {
		$window.location.href = '/accueil.html';
	};
});

/*
 * app.controller('uploadFileController', ['$scope', '$http', function($scope,
 * $http){ $scope.doUploadFile = function(){ var file = $scope.uploadedFile; var
 * url = "http://localhost:8080/candidats/uploadfile";
 * 
 * var data = new FormData(); data.append('uploadfile', file);
 * 
 * var config = { transformRequest: angular.identity, transformResponse:
 * angular.identity, headers : { 'Content-Type': undefined } }
 * 
 * $http.post(url, data, config).then(function (response) {
 * $scope.uploadResult=response.data; }, function (response) {
 * $scope.uploadResult=response.data; }); }; }]);
 */
