package com.wecook.backend.repositories;

import com.wecook.backend.responses.ProfileResponse;
import com.wecook.backend.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.wecook.backend.models.Profile;

public class ProfileRepository {

    public static ProfileResponse get(int userId){

        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Profiles WHERE user_id = ?");
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){

                Profile profile = new Profile();
                profile.setId(rs.getInt(1));
                profile.setProfilePhoto(PhotoRepository.getById(rs.getInt(3)));
                profile.setAbout(rs.getString(4));
                profile.setBirthPlace(rs.getString(5));
                profile.setLivesIn(rs.getString(6));
                profile.setOccupation(rs.getString(7));
                profile.setJoinedDate(rs.getDate(8));
                profile.setStatus(rs.getString(9));
                profile.setWebsite(rs.getString(10));
                profile.setPhoneNumber(rs.getString(11));
                profile.setFacebookLink(rs.getString(12));
                profile.setTwitterLink(rs.getString(13));
                profile.setCoverPhoto(PhotoRepository.getById(rs.getInt(14)));
                profile.setFirstName(rs.getString(15));
                profile.setLastName(rs.getString(16));
                ProfileResponse response = new ProfileResponse();
                response.setProfile(profile);

                return response;
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;



    }
}
