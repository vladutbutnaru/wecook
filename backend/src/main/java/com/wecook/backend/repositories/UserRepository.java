package com.wecook.backend.repositories;

import com.wecook.backend.models.Token;
import com.wecook.backend.models.User;
import com.wecook.backend.responses.LoginResponse;
import com.wecook.backend.utils.DBConnection;
import com.wecook.backend.utils.TokenGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
