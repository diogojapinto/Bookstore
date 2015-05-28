angular.module('BookStore').controller('PlaceOrderController', function ($scope, Store) {
    var waiting = true;
    Store.getBooksList()
            .success(function (data, status, headers, config) {
                $scope.books = data;
                waiting = false;
            })
            .error(function (data, status, headers, config) {
                console.log(data);
                waiting = false;
            });

    $scope.placeOrder = function () {
        // all names are already in scope (or model, whichever you prefer)
        // maybe redirect to view orders
    };
});
