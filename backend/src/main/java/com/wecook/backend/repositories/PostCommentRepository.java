package com.wecook.backend.repositories;

import com.wecook.backend.models.Comment;
import com.wecook.backend.models.Post;
import com.wecook.backend.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostCommentRepository {

    public static List<Comment> getCommentsForPost(int postId){
        List<Comment> comments = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post_Comments WHERE post_id = ?");
            preparedStatement.setInt(1,postId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setUserId(rs.getInt(2));
                comment.setUser(UserRepository.getUserById(comment.getUserId()));
                comment.setPostId(rs.getInt(3));
                comment.setCreatedAt(rs.getTimestamp(4));
                comment.setText(rs.getString(5));
                comments.add(comment);
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return comments;



    }

}
