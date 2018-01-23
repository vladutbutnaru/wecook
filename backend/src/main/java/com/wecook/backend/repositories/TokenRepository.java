package com.wecook.backend.repositories;

import com.wecook.backend.models.Token;
import com.wecook.backend.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public static boolean verifyTokenCode(String token){

        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Tokens WHERE code = ? AND status = 1 ");
            preparedStatement.setString(1,token);
            if(preparedStatement.executeQuery().next()){
                preparedStatement = connection.prepareStatement("UPDATE Tokens SET refreshed_at = CURRENT_TIMESTAMP WHERE code = ?");
                preparedStatement.setString(1,token);
                preparedStatement.executeUpdate();
                return true;

            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;



    }

    public static int getUserForToken(String token){

        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id FROM Tokens WHERE code = ?");
            preparedStatement.setString(1,token);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return rs.getInt(1);

            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;



    }
}
