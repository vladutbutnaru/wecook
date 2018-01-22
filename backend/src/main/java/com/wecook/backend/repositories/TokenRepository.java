package com.wecook.backend.repositories;

import com.wecook.backend.models.Token;
import com.wecook.backend.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TokenRepository {

    public static void insertToken(Token token){
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Tokens(user_id, created_at, status, refreshed_at, code) VALUES (?,CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, ?)");
            preparedStatement.setInt(1,token.getUser().getId());
            preparedStatement.setString(2,token.getCode());
            preparedStatement.executeUpdate();

        }
        catch(Exception e){
            e.printStackTrace();
        }




    }
}
