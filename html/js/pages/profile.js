if (getCookie("token") == null) {
    location.href = "login.html";

}
var fullName;
var profilePic;
var getOwnProfile = function () {
    $.get(api + "/profiles/own?token=" + getCookie("token"), {}, function (response) {
        fullName = response.profile.firstName + " " + response.profile.lastName;
        
        $('#user-name').html(fullName);
        $('#user-name-main').html(fullName);
        $('#user-name-up').html(fullName);
        $('#user-profile-pic').attr("src","img" + response.profile.profilePhoto.path);
        profilePic = response.profile.profilePhoto.path;
        $('#user-lives-in').html(response.profile.livesIn);
        
    });



}

getOwnProfile();


var getOwnPosts = function (){
     var posts = '';
     $.get(api + "/profiles/timeline?token=" + getCookie("token"), {}, function (response) {
       
       for(var i = 0; i<response.posts.length; i++){
           var post = response.posts[i];
           var friendsLove = '';
           var friendsLoveNames = '';
          
           if(post.loves.length>2){
               var numberOfLoves = post.loves.length-2;
               friendsLoveNames = '<a href="#">' + post.loves[0].user.firstName + '</a>, <a href="#">' + post.loves[1].user.firstName + "</a> and <br> " + numberOfLoves + " others loved this";
               
           }
          if(post.loves.length == 1){
               friendsLoveNames = '<a href="#">' + post.loves[0].user.firstName + '</a> loved this';
              
          }
           if(post.loves.length == 0){
                friendsLoveNames = 'There are no loves yet.';
               
           }
        console.log(post.loves.length);
           for(var j = 0; j < post.loves.length; j++){
               var love = post.loves[j];
            
               friendsLove = friendsLove +'<li> <a href="#"> <img src="img'+love.user.profile.profilePhoto.path+'" class= "img-cover" alt="'+love.user.firstName + ' ' + love.user.lastName + '"> </a> </li>';
               
               
           }
           var samplePost = '<div class="ui-block" ><!-- Post --> <article class="hentry post"> <div class="post__author author vcard inline-items"> <img src="img'+profilePic+'" alt="author"> <div class="author-date"> <a class="h6 post__author-name fn" href="profile.html">'+fullName+'</a> <div class="post__date"> <time class="published" datetime="2017-03-24T18:18">'+post.publishedAt+' </time> </div> </div> <div class="more"> <svg class="olymp-three-dots-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use> </svg> <ul class="more-dropdown"> <li> <a href="#">Edit Post</a> </li> <li> <a href="#">Delete Post</a> </li> <li> <a href="#">Turn Off Notifications</a> </li> <li> <a href="#">Select as Featured</a> </li> </ul> </div> </div> <p>'+post.text+' </p> <div class="post-additional-info inline-items"> <a href="#" class="post-add-icon inline-items"> <svg class="olymp-heart-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-heart-icon"></use> </svg> <span>'+post.loves.length+'</span> </a> <ul class="friends-harmonic">'+friendsLove+'</ul> <div class="names-people-likes">'+friendsLoveNames+'</div> <div class="comments-shared"> <a href="#" class="post-add-icon inline-items"> <svg class="olymp-speech-balloon-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-speech-balloon-icon"></use> </svg> <span>17</span> </a> <a href="#" class="post-add-icon inline-items"> <svg class="olymp-share-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-share-icon"></use> </svg> <span>24</span> </a> </div> </div> <div class="control-block-button post-control-button"> <a href="#" class="btn btn-control featured-post"> <svg class="olymp-trophy-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-trophy-icon"></use> </svg> </a> <a href="#" class="btn btn-control"> <svg class="olymp-like-post-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-like-post-icon"></use> </svg> </a> <a href="#" class="btn btn-control"> <svg class="olymp-comments-post-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-comments-post-icon"></use> </svg> </a> <a href="#" class="btn btn-control"> <svg class="olymp-share-icon"> <use xlink:href="svg-icons/sprites/icons.svg#olymp-share-icon"></use> </svg> </a> </div> </article> <!-- .. end Post --> </div>';
           
           
           posts = posts + samplePost;
           
       }
        
         $('#timeline-posts').html(posts);
    });


    
}

getOwnPosts();