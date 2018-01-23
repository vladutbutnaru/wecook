$('#login-button').on('click', function () {
    $.post(api + "/users/login", {
        "email": $('#login-email').val(),
        "password": $('#login-password').val()
    }, function (response) {
        if (response.status == "success") {
            setCookie("token", response.token.code, 7);
            location.href = "profile.html";

        } else {
            alert("The login information you provided is incorrect!");
        }

    });








});
