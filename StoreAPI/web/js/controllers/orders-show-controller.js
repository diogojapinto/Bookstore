angular.module('BookStore').controller('ViewOrdersController', function($scope, Store) {
  $scope.users = User.query();

  $scope.retrieveOrders = function() {
    $scope.orders = Store.getUserOrders($scope.username);

    // username must come from form
  };
});
