var mouse_is_inside = false;

$(document).ready(function() {
    $(".signup_btn").click(function() {
        var signupBox = $("#signup_box");
        if (signupBox.is(":visible"))
            signupBox.fadeOut("fast");
        else
            signupBox.fadeIn("fast");
        return false;
    });
    
    $("#signup_box").hover(function(){
        mouse_is_inside=true; 
    }, function(){ 
        mouse_is_inside=false; 
    });

    $("body").click(function(){
        if(! mouse_is_inside) $("#signup_box").fadeOut("fast");
    });
});