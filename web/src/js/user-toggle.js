var $message = $("#message");
var $post = $("#post");
var $message_button = $("#message-button");
var $post_button = $("#post-button");

$message.click(function (event) {
    $message.show();
    $post.show
    $post.hide();
    $message_button.addClass("active");
    $post_button.removeClass("active");
});

$post.click(function (event) {
    $post.show();
    $message.hide();
    $message_button.removeClass("active");
    $post_button.addClass("active");
});
