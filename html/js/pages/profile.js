if (getCookie("token") == null) {
    location.href = "login.html";

}

var getOwnProfile = function () {
    $.get(api + "/profiles/own?token=" + getCookie("token"), {}, function (response) {
        $('#user-name').html(response.profile.firstName + " " + response.profile.lastName);
        $('#user-name-main').html(response.profile.firstName + " " + response.profile.lastName);
        $('#user-name-up').html(response.profile.firstName + " " + response.profile.lastName);
        $('#user-profile-pic').attr("src","img" + response.profile.profilePhoto.path);
    });



}

getOwnProfile();
