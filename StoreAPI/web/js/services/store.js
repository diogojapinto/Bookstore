angular.module('BookStore').factory('Store', function NoteFactory() {  
  return {
    getUserOrders: function(id) {
      return $http({method: 'GET', url: '/webresources/orders/' + id});
    },
    placeOrder: function() {
      return $http({method: 'POST', url: '/webresources/orders/'});
    }, 
  };
});
