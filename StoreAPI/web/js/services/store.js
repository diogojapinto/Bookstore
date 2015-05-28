angular.module('BookStore').factory('Store', function ($http) {
    return {
        getUserOrders: function (id) {
            return $http({method: 'GET', url: '/StoreAPI/webresources/orders/' + id});
        },
        placeOrder: function () {
            return $http({method: 'POST', url: '/StoreAPI/webresources/orders/'});
        },
        getBooksList: function () {
            return $http({method: 'GET', url: '/StoreAPI/webresources/books/'});
        }
    };
});
