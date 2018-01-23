package com.wecook.backend.responses;

import com.wecook.backend.models.Profile;
import com.wecook.backend.models.User;

public class ProfileResponse {


    private Profile profile;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
