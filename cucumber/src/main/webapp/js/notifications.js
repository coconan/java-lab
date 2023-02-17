/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
function Validate(form) { 
    this.form = form;
}

Validate.prototype.queue = function (amount) { 
    if (this.timer) {
        clearTimeout(this.timer);
    }
    var self = this;
    this.timer = setTimeout(function () {
        self.checkAmount(amount);
    }, 150);
};

Validate.prototype.checkAmount = function (amount) { 
    var self = this;
    jQuery.ajax({
        url: '/validate',
        type: 'GET',
        data: {'amount': amount},
        success: function(results) {self.render(results)}, 
        contentType: 'application/json',
        dataType: 'json',
    });
}

Validate.prototype.render = function (notifications) {
    var html ="";
    if (notifications.content.length > 0) {
        html = "<li>" + notifications.content + "</li>"; 
    }
    jQuery(this.form).find('ol.notifications').html(html); 
}

jQuery.fn.setupValidation = function () { 
    this.each(function () {
        var validator = new Validate(this);
        var input = jQuery(this).find("input[type=text]"); 
        input.bind('keyup', function () {
            validator.queue(this.value);
        });
    }); 
};

jQuery(function() { jQuery('#withdrawalForm').setupValidation();});
