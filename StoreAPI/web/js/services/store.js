angular.module('BookStore').factory('Store', function ($http) {
    return {
        getUserOrders: function (id) {
            return $http({method: 'GET', url: '/StoreAPI/webresources/orders/' + id});
        },
        placeOrder: function (newOrder) {
            return $http({method: 'POST', url: '/StoreAPI/webresources/orders/true',
                data: newOrder});
        },
        getBooksList: function () {
            return $http({method: 'GET', url: '/StoreAPI/webresources/books/'});
        }
    };
});
