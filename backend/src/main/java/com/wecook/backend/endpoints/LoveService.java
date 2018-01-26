package com.wecook.backend.endpoints;

import com.wecook.backend.models.Love;
import com.wecook.backend.repositories.LoveRepository;
import com.wecook.backend.repositories.TokenRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Controller
public class LoveService {
    @RequestMapping(value = "/v1/love", produces = {"application/json"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> love(@RequestParam(value = "token", required = true) String tokenCode
            , @RequestParam(value = "post-id", required=true) int postId

    ) {
        System.out.println("Love called { token: " + tokenCode + " , post-id: " + postId + " }");

        if(TokenRepository.verifyTokenCode(tokenCode)) {
            int userId = TokenRepository.getUserForToken(tokenCode);

            LoveRepository.registerLove(userId, postId);

        }

        return ResponseEntity.ok(new Love());

    }

}
