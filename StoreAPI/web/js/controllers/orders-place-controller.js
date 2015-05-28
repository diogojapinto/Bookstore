angular.module('BookStore').controller('PlaceOrderController', function ($scope, Store) {
    var waiting = true;
    $scope.order = {};
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
        var newOrder = {
            title: $scope.selectedBook.title,
            quantity: $scope.book.quantity,
            clientName: $scope.user.name,
            clientEmail: $scope.user.email,
            clientAddress: $scope.user.address
        };        
        
        Store.placeOrder(newOrder)
                .success(function (data, status, headers, config) {
                    $scope.order.success = true;
                    $scope.order.error = false;
                    $scope.order.message = "Order successfully placed";
                })
                .error(function (data, status, headers, config) {
                    console.log(data);
                    $scope.order.success = false;
                    $scope.order.error = true;
                    $scope.order.message = "There was an error placing the order";
                });
    };
});
