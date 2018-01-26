package com.wecook.backend.endpoints;

import com.wecook.backend.models.Love;
import com.wecook.backend.models.Post;
import com.wecook.backend.repositories.LoveRepository;
import com.wecook.backend.repositories.PostRepository;
import com.wecook.backend.repositories.TokenRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Controller
public class PostService {

    @RequestMapping(value = "/v1/posts/delete", produces = {"application/json"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> deletePost(@RequestParam(value = "token", required = true) String tokenCode
            , @RequestParam(value = "post-id", required=true) int postId

    ) {
        System.out.println("Delete post called { token: " + tokenCode + " , post-id: " + postId + " }");

        if(TokenRepository.verifyTokenCode(tokenCode)) {

            PostRepository.removePost(postId);


        }

        return ResponseEntity.ok(new Post());

    }
}
