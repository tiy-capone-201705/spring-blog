angular
  .module('blog')
  .component('postDetails', {
    templateUrl: '/app/post-details/post-details.component.html',
    controllerAs: 'details',
    bindings: {
      post: '<'
    }
  });
