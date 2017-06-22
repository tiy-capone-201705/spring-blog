class BlogUiController {
  handlePostSelection(post) {
    if (this.selectedPost === post) {
      this.selectedPost = null;
    } else {
      this.selectedPost = post;
    }
  }
}

angular
  .module('blog')
  .component('blogUi', {
    templateUrl: '/app/blog-ui/blog-ui.component.html',
    controllerAs: 'ui',
    controller: [() => new BlogUiController()]
  });
