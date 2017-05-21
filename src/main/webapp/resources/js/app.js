'use strict';

var kalah = angular.module('kalah', ['ngRoute', 'KalahApp.services']);

kalah.config(['$routeProvider', '$sceProvider',
    function ($routeProvider, $sceProvider) {

        $routeProvider.
                when('/init', {
                    templateUrl: 'resources/partials/game.html',
                    controller: 'GameController'
                }).
                otherwise({
                    redirectTo: '/init'
                });
        $sceProvider.enabled(false);

    }]);
