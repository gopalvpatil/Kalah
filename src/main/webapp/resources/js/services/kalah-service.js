/**
 * Created by Gopal Patil
 */
'use strict';

//This service is responsible for sending request to server and get response back.
var AppServices = angular.module('KalahApp.services', []);

AppServices.factory('kalahService', ['$http','$q', function($http, $q) {
	
	return {
		initGame: function(stonesPerPit) {
			return $http.get('/kalah/api/initGame/' + stonesPerPit)
			.then(
					function(response) {
						return response.data;
					}, 
					function(errResponse) {
						console.error('Error while initialising game.');
						return $q.reject(errResponse);
					}
			);
		},
		
		playGame: function(kalahGameRequest) {
			return $http.post('/kalah/api/moveStones', kalahGameRequest)
			.then(
					function(response) {
						return response.data;
					}, 
					function(errResponse) {
						console.error('Error while playing game.');
						return $q.reject(errResponse);
					}
			);
		}
	};
	
}]);