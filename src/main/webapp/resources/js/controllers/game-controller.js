/**
 * Created by Gopal Patil
 */
'use strict';

var kalah = angular.module('kalah');

//This controller is responsible for sending to the back end the player's input 
//and receiving back the updated board of the game
kalah.controller('GameController', function ($scope, $http, $window, kalahService) {
	
	$scope.currentPlayerId = 1;
	$scope.stonesPerPit = 6;
	$scope.kalahBoard;
	$scope.kalahPlayer;

	//This function is responsible to initiate game.
	$scope.initGame = function() {
		
		kalahService.initGame($scope.stonesPerPit)
		.then(
				function(data) {
					$scope.kalahBoard = data.kalahBoard;
					$scope.kalahPlayer = data.kalahPlayer;
					
					$scope.board = data.kalahBoard.pits;
					$scope.currentPlayerId = data.kalahPlayer.id;
	                $scope.msg = "Player1 turn!!";
					$scope.errMsg =  '';
	                $scope.player2Points = $scope.board[13];
	                $scope.player1Points = $scope.board[6];
				},
				function(errResponse) {
					$scope.errMsg =  'Error ocurred while initialising game. Please try again';
					console.error('Error ocurred while initialising game. Please try again','Error');
				}
			);
	}
	
	$scope.initGame();

    //Function that is called when a stones from one of the pit is picked
    $scope.sow = function (selectedPitsIndex) {
    	
    	var kalahGameRequest = { selectedPitsIndex : selectedPitsIndex, 
    							 kalahBoard : $scope.kalahBoard,
    							 kalahPlayer: $scope.kalahPlayer		
    				 			};
    	
		kalahService.playGame(kalahGameRequest)
		.then(
				function(data) {
					$scope.kalahBoard = data.kalahBoard;
					$scope.kalahPlayer = data.kalahPlayer;
					
					$scope.board = data.kalahBoard.pits;
					$scope.currentPlayerId = data.kalahPlayer.id;
                    $scope.errMsg =  data.errorMessage;
                    
                    if ($scope.currentPlayerId === 1) {
                        $scope.msg = "Player1 turn!!";
                    }
                    else if ($scope.currentPlayerId === 2) {
                        $scope.msg = "Player2 turn!!";
                    }
                    else {
                    	if($scope.board[13] > $scope.board[6]) {
                    		$scope.msg = "GAME OVER!! - Player 2 Won";
                    	} else if ($scope.board[13] < $scope.board[6]){
                    		$scope.msg = "GAME OVER!! - Player 1 Won";
                    	} else {
                    		$scope.msg = "GAME OVER!! - Both players have equal scores";
                    	}
                    }
                    $scope.player2Points = $scope.board[13];
                    $scope.player1Points = $scope.board[6];
				},
				function(errResponse) {
					$scope.errMsg =  'Error ocurred while playing game. Please try again';
					console.error('Error ocurred while playing game. Please try again','Error');
				}
			);
    };
});