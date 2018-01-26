package com.wecook.backend.repositories;

import com.wecook.backend.models.Comment;
import com.wecook.backend.models.Love;
import com.wecook.backend.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoveRepository {

    public static List<Love> getLovesForPost(int postId){
        List<Love> loves = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Loves WHERE post_id = ?");
            preparedStatement.setInt(1,postId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Love love = new Love();
                love.setId(rs.getInt(1));
                love.setUserId(rs.getInt(2));
                love.setUser(UserRepository.getUserById(love.getUserId()));
                love.setPostId(rs.getInt(3));
                love.setCreatedAt(rs.getTimestamp(4));

                loves.add(love);
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return loves;



    }
    public static void registerLove(int userId, int postId){
        Connection connection = DBConnection.getConnection();
        boolean alreadyLoved = false;
        for(Love l : getLovesForPost(postId)){
            if(l.getUserId() == userId){
                alreadyLoved = true;
            }

        }
        if(alreadyLoved)
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM Loves WHERE post_id = ? AND user_id = ?");
            preparedStatement.setInt(1,postId);
            preparedStatement.setInt(2,userId);

            preparedStatement.executeUpdate();



        }
        catch(Exception e){
            e.printStackTrace();
        }
        else{
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Loves(user_id, post_id) VALUES (?,?)");
                preparedStatement.setInt(1,userId);
                preparedStatement.setInt(2,postId);

                preparedStatement.executeUpdate();



            }
            catch(Exception e){
                e.printStackTrace();
            }


        }



    }
}
