package com.wecook.backend.repositories;

import com.wecook.backend.models.Photo;
import com.wecook.backend.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {

    public static Photo getById(int photoId){

        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photos WHERE id=?");
            preparedStatement.setInt(1,photoId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
             Photo photo = new Photo();
                photo.setId(photoId);
                photo.setPath(rs.getString(2));

                return photo;

            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public static List<Photo> getUserPhotos(int userId){
        Connection connection = DBConnection.getConnection();
        List<Photo> photos = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Photos WHERE user_id=? ORDER BY id DESC");
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Photo photo = new Photo();
                photo.setId(rs.getInt(1));
                photo.setPath(rs.getString(2));
                photo.setCreatedAt(rs.getTimestamp(5));
                photo.setRecipeId(rs.getInt(6));
                photos.add(photo);

            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return photos;


    }


}
