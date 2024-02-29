$(document).ready(function() {

    $("h1").text("Registration");
    // Ẩn các form không cần thiết khi trang web được tải
    $("#login-form, #fpass-form").hide();

    // Hiển thị form registration-form
    $("#registration-form").show();

    // Xử lý sự kiện khi click vào nút NEW USER
    $("#newUser").click(function(){
        $("h1").text("Registration");
        $(".logo").css({
            "width":"120px",
            "height":"120px",
            "top":"10px"
        });
        $("#login-form, #fpass-form").fadeOut(200);
        $("#registration-form").delay(200).fadeIn(500);
        $(".other-options").fadeOut(200);
    });

    // Xử lý sự kiện khi click vào nút LOG IN hoặc FORGOT PASSWORD
    $("#signup-btn, .getpass-btn").click(function(){
        $("h1").text("Log in");
        $(".logo").css({
            "width":"150px",
            "height":"150px",
            "top":"30px"
        });
        $("#registration-form, #fpass-form").fadeOut(200);
        $("#login-form").delay(200).fadeIn(500);
        $(".other-options").fadeIn(300);
    });

    // Xử lý sự kiện khi click vào nút FORGOT PASSWORD
    $("#fPass").click(function(){
        $("h1").text("Forgot password");
        $(".logo").css({
            "width":"190px",
            "height":"190px",
            "top":"40px"
        });
        $("#login-form").fadeOut(200);
        $("#fpass-form").delay(200).fadeIn(500);
        $(".other-options").fadeOut(200);
    });
});
