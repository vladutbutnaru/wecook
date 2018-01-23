package com.wecook.backend.repositories;

import com.wecook.backend.models.Post;
import com.wecook.backend.models.Token;
import com.wecook.backend.models.User;
import com.wecook.backend.responses.CompleteInformationResponse;
import com.wecook.backend.responses.LoginResponse;
import com.wecook.backend.utils.DBConnection;
import com.wecook.backend.utils.TokenGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {



    public static LoginResponse login(String email, String password){
        Connection connection = DBConnection.getConnection();
        LoginResponse response = new LoginResponse();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT id FROM Users WHERE email = ? AND password = ?");
            stmt.setString(1,email);
            stmt.setString(2,password);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                response.setStatus("success");
                //generate token
                Token loginToken = new Token();
                loginToken.setCode(TokenGenerator.generateToken());
                loginToken.setStatus(1);
                User user = new User();
                user.setId(rs.getInt(1));
                loginToken.setUser(user);
                //insert token
                TokenRepository.insertToken(loginToken);

                //return token
                response.setToken(loginToken);

                return response;


            }
            else{
                //incorrect login info
                response.setStatus("incorrect");
                return response;

            }






        }
        catch(Exception e){
            e.printStackTrace();

            //return error message

            response.setStatus(e.getMessage());

            return response;

        }


    }


    public static User getUserById(int userId){
User u = new User();
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
            preparedStatement.setInt(1,userId);


            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                u.setId(userId);
                u.setEmail(rs.getString(2));
                u.setCountry(rs.getString(4));
                u.setBirthDate(rs.getDate(5));
                u.setFirstName(rs.getString(6));
                u.setLastName(rs.getString(7));
                u.setProfile(ProfileRepository.get(u.getId()).getProfile());

            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return u;

    }


    public static List<User> getUserFriends(int userId){
        List<User> friends = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Friend_Requests WHERE id_requester = ? AND status = 2");
            preparedStatement.setInt(1,userId);


            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                friends.add(getUserById(rs.getInt(3)));

            }

            preparedStatement = connection.prepareStatement("SELECT * FROM Friend_Requests WHERE id_receiver = ? AND status = 2");
            preparedStatement.setInt(1,userId);


             rs = preparedStatement.executeQuery();
            while(rs.next()){
                friends.add(getUserById(rs.getInt(2)));

            }





        }
        catch(Exception e){
            e.printStackTrace();
        }
        return friends;



    }

    public static CompleteInformationResponse getCompleteInformation(int userId){
        CompleteInformationResponse response = new CompleteInformationResponse();
        response.setFriends(getUserFriends(userId));
        response.setPhotos(PhotoRepository.getUserPhotos(userId));
        response.setPosts(PostRepository.getUserPosts(userId));

        return response;


    }
}
