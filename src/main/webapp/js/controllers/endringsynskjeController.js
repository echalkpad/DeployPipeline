(function() {

    var endringsynskjeController = function ($scope, endringsynskjeService) {
        $scope.registrerEndringsynskje = function () {
            endringsynskjeService.registrerEndringsynskje($scope.namn).success(function (data) {
                namn = '';
            });
        };
    };

    angular.module("exampleModule").controller("endringsynskjeController", endringsynskjeController)
}());