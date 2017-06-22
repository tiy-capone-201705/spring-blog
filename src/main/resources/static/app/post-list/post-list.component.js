angular
  .module('blog')
  .component('postList', {
    templateUrl: '/app/post-list/post-list.component.html',
    controllerAs: 'list',
    bindings: {
      posts: '<',
      onSelected: '&'
    }
  });
