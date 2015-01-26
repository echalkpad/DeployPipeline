(function() {

    var endringsynskjeController = function ($scope, endringsynskjeService) {
        $scope.namn = "";

        $scope.registrerEndringsynskje = function () {
            endringsynskjeService.registrerEndringsynskje($scope.namn).success(function (data) {
                $scope.namn = '';
            });
        };

        $scope.endringsynskjer = [];

        $scope.hentEndringsynskjer = function () {
            endringsynskjeService.hentEndringsynskjer().success(function (data) {
                $scope.endringsynskjer = data;
            })
        };

        $scope.hentEndringsynskjer();
    };

    angular.module("exampleModule").controller("endringsynskjeController", endringsynskjeController)
}());