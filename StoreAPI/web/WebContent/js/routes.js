/*
  Configure routes used with ngRoute. We chose not to use $locationProvider.html5Mode(true);
  because using HTML5 pushstate requires that server routes are setup to mirror the routes
  in this file. Since this isn't a node course we're going to skip it. For all intensive
  purposes, html5 mode and url hash mode perform the same when within an angular app.
*/
angular.module('BookStore').config(function($routeProvider) {
  $routeProvider
    .when('/', {
      // redirect to the notes index
      redirectTo: '/placeOrder'
    })
    
    .when('/placeOrder', {
      templateUrl: '/templates/pages/orders/place.html',
      controller: 'PlaceOrderController'
    })
    
    .when('/viewOrders', {
      templateUrl: '/templates/pages/orders/show.html',
      controller: 'ViewOrdersController'
    })

    .otherwise({redirectTo: '/'});
});
