angular.module('BookStore').controller('ViewOrdersController', function ($scope, Store) {
    //$scope.users = User.query();
    $scope.username = "";
    $scope.orders = {};

    $scope.retrieveOrders = function () {
        if ($scope.username !== "") {
            alert("calling");
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
