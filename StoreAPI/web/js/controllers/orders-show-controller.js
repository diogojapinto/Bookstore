angular.module('BookStore').controller('ViewOrdersController', function ($scope, Store) {
    $scope.username = "";
    $scope.orders = {};

    $scope.retrieveOrders = function () {
        if ($scope.username !== "") {
            Store.getUserOrders($scope.username)
                .success(function(response) {
                    alert(response);
                    $scope.orders = response;
                })
                .error(function(error) {
                    console.log(error);
                });
        } else {
            alert("Username field empty!")
        }
    };
});
