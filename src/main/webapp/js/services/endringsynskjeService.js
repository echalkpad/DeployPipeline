(function () {
    var endringsynskjeService = function ($http) {
        var service = {};

        service.registrerEndringsynskje = function(namn) {
            return $http.post('../services/endringsynskje', namn);
        };

        return service;
    }

    angular.module('exampleModule').factory('endringsynskjeService', endringsynskjeService)
}());