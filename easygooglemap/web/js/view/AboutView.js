define([
  'jquery',
  'underscore',
  'backbone',
  'text!template/about/AboutTemplate.html'
], function($, _, Backbone, htmlTemplate) {

  var AboutView = Backbone.View.extend({
    el: $("#content"),
    
    render: function(){
      window.logger.debug("I am going to hit the About Page.");
      this.$el.html(htmlTemplate);
    }
  
  });

  return AboutView;
  
});
