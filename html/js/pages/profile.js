if (getCookie("token") == null) {
    location.href = "login.html";

}
var fullName;
var profilePic;
var ownUserId;
var getOwnProfile = function () {
    $.get(api + "/profiles/own?token=" + getCookie("token"), {}, function (response) {
        fullName = response.profile.firstName + " " + response.profile.lastName;

        $('#user-name').html(fullName);
        $('#user-name-main').html(fullName);
        $('#user-name-up').html(fullName);
        $('#user-profile-pic').attr("src", "img" + response.profile.profilePhoto.path);
        profilePic = response.profile.profilePhoto.path;
        $('#user-lives-in').html(response.profile.livesIn);

    });



}

getOwnProfile();


var getOwnPosts = function () {
        var posts = '';

        $.ajax({
                type: "GET",
                url: api + "/profiles/timeline?token=" + getCookie("token"),
                data: {},
                success: function (response) {
                    for (var i = 0; i < response.posts.length; i++) {
                        ownUserId = response.posts[0].userId;
                        var post = response.posts[i];
                        var friendsLove = '';
                        var friendsLoveNames = '';

                        if (post.loves.length > 2) {
                            var numberOfLoves = post.loves.length - 2;
                            friendsLoveNames = '<a href="#">' + post.loves[0].user.firstName + '</a>, <a href="#">' + post.loves[1].user.firstName + "</a> and <br> " + numberOfLoves + " others loved this";

                        }
                        if (post.loves.length == 1) {
                            friendsLoveNames = '<a href="#">' + post.loves[0].user.firstName + '</a> loved this';

                        }
                        if (post.loves.length == 0) {
                            friendsLoveNames = 'There are no loves yet.';

                        }

                        var likedByMe = false;
                        for (var j = 0; j < post.loves.length; j++) {
                            var love = post.loves[j];

                            friendsLove = friendsLove + '<li> <a href="#"> <img src="img' + love.user.profile.profilePhoto.path + '" class= "img-cover" alt="' + love.user.firstName + ' ' + love.user.lastName + '"> </a> </li>';
                            if (love.userId == ownUserId)
                                likedByMe = true;

                        }

                        var samplePost = '<div class="ui-block" ><!-- Post --> <article class="hentry post"> <div class="post__author author vcard inline-items"> <img src="img' + profilePic + '" alt="author"> <div class="author-date"> <a class="h6 post__author-name fn" href="profile.html">' + fullName + '</a> <div class="post__date"> <time class="published" datetime="2017-03-24T18:18">' + post.publishedAt + ' </time> </div> </div> <div class="more"> <svg class="olymp-three-dots-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use> </svg> <ul class="more-dropdown"> <li> <a href="#">Edit Post</a> </li> <li> <a href="#" onClick="deletePost('+post.id+')">Delete Post</a> </li> <li> <a href="#">Turn Off Notifications</a> </li> <li> <a href="#">Select as Featured</a> </li> </ul> </div> </div> <p>' + post.text + ' </p> <div class="post-additional-info inline-items"> <a href="#" class="post-add-icon inline-items"> <svg class="olymp-heart-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-heart-icon"></use> </svg> <span>' + post.loves.length + '</span> </a> <ul class="friends-harmonic">' + friendsLove + '</ul> <div class="names-people-likes">' + friendsLoveNames + '</div> <div class="comments-shared"> <a href="#" class="post-add-icon inline-items"> <svg class="olymp-speech-balloon-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-speech-balloon-icon"></use> </svg> <span>'+ post.comments.length+'</span> </a> <a href="#" class="post-add-icon inline-items"> <svg class="olymp-share-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-share-icon"></use> </svg> <span>24</span> </a> </div> </div> <div class="control-block-button post-control-button">  </a> ';

                        if (likedByMe == true)
                            samplePost += '<a href="#" class="btn btn-control" style="background-color:#ff5e3a" onClick="lovePost(' + post.id + ')">  <svg class="olymp-like-post-icon"  > <use xlink:href="svg-icons/sprites/icons.svg#olymp-like-post-icon"></use> </svg> </a> <a href="#" class="btn btn-control"> <svg class="olymp-comments-post-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-comments-post-icon"></use> </svg> </a> <a href="#" class="btn btn-control"> <svg class="olymp-share-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-share-icon"></use> </svg> </a> </div> </article> <!-- .. end Post -->';
                        else
                            samplePost += '<a href="#" class="btn btn-control" onClick="lovePost(' + post.id + ')">  <svg class="olymp-like-post-icon"  > <use xlink:href="svg-icons/sprites/icons.svg#olymp-like-post-icon"></use> </svg> </a> <a href="#" class="btn btn-control"> <svg class="olymp-comments-post-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-comments-post-icon"></use> </svg> </a> <a href="#" class="btn btn-control"> <svg class="olymp-share-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-share-icon"></use> </svg> </a> </div> </article> <!-- .. end Post -->';

                        var comments = '<ul class="comments-list">';
                        var hasComments = false;
                        for (var x = 0; x < post.comments.length; x++) {
                            hasComments = true;
                            var comment = post.comments[x];
                            comments = comments + '<li> <div class="post__author author vcard inline-items"> <img src="img' + comment.user.profile.profilePhoto.path + '" alt="author"> <div class="author-date"> <a class="h6 post__author-name fn" href="#">' + comment.user.firstName + ' ' + comment.user.lastName + '</a> <div class="post__date"> <time class="published" datetime="2017-03-24T18:18">' + comment.createdAt + ' </time> </div> </div>  </div> <p>' + comment.text + '</p> <a href="#" class="post-add-icon inline-items"> <svg class="olymp-heart-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-heart-icon"></use> </svg> <span>8</span> </a> <a href="#" class="reply">Reply</a> </li>';

                        }
                        comments = comments + '</ul>';
                        posts = posts + samplePost;
                        if (hasComments == true) {
                            posts = posts + comments;
                        }
                        posts = posts + "</div>";
                    }


                        $('#timeline-posts').html(posts);


                    },
                    // vvv---- This is the new bit
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("Error, status = " + textStatus + ", " +
                            "error thrown: " + errorThrown
                        );
                    }
                });





        }

        getOwnPosts();

        var lovePost = function (postId) {



            $.ajax({
                type: "POST",
                url: api + "/love",
                data: {
                    "token": getCookie("token"),
                    "post-id": postId
                },
                success: function (data) {
                    getOwnPosts();

                },
                // vvv---- This is the new bit
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error, status = " + textStatus + ", " +
                        "error thrown: " + errorThrown
                    );
                }
            });

        }
        var deletePost = function(postId){
            
            $.ajax({
                type: "POST",
                url: api + "/posts/delete",
                data: {
                    "token": getCookie("token"),
                    "post-id": postId
                },
                success: function (data) {
                    getOwnPosts();

                },
                // vvv---- This is the new bit
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error, status = " + textStatus + ", " +
                        "error thrown: " + errorThrown
                    );
                }
            });
            
        }
