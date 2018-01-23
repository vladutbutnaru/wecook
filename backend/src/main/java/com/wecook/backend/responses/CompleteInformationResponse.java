package com.wecook.backend.responses;

import com.wecook.backend.models.Notification;
import com.wecook.backend.models.Photo;
import com.wecook.backend.models.Post;
import com.wecook.backend.models.User;

import java.util.List;

public class CompleteInformationResponse {

    private List<Post> posts;
    private List<User> friends;
    private List<Notification> notifications;
    private List<Photo> photos;


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
