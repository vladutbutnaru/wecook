package com.wecook.backend.repositories;

import com.wecook.backend.models.Post;
import com.wecook.backend.models.Profile;
import com.wecook.backend.responses.ProfileResponse;
import com.wecook.backend.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    public static List<Post> getUserPosts(int userId){
        List<Post> posts = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Timeline_Posts WHERE user_id = ?");
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setUserId(rs.getInt(2));
                p.setPublishedAt(rs.getTimestamp(3));
                p.setPostType(rs.getInt(4));
                p.setText(rs.getString(5));
                p.setLink(rs.getString(6));
                p.setPhotoId(rs.getInt(7));
                p.setOriginalPostId(rs.getInt(8));
                p.setRecipeId(rs.getInt(9));
                p.setLoves(LoveRepository.getLovesForPost(p.getId()));
                p.setComments(PostCommentRepository.getCommentsForPost(p.getId()));

                posts.add(p);
            }



        }
        catch(Exception e){
            e.printStackTrace();
        }
        return posts;



    }
}
