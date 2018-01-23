package com.wecook.backend.endpoints;

import com.wecook.backend.repositories.ProfileRepository;
import com.wecook.backend.repositories.TokenRepository;
import com.wecook.backend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Controller
public class ProfileService {

    @RequestMapping(value = "/v1/profiles/own", produces = {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getProfile(@RequestParam(value = "token", required = true) String tokenCode

    ) {
        if(TokenRepository.verifyTokenCode(tokenCode)){
            int userId = TokenRepository.getUserForToken(tokenCode);
            if(userId>0)
                return ResponseEntity.ok(ProfileRepository.get(userId));
            else
                return ResponseEntity.ok("not found");

        }

        return ResponseEntity.ok("incorrect token");


    }

    @RequestMapping(value = "/v1/profiles/timeline", produces = {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getTimeline(@RequestParam(value = "token", required = true) String tokenCode

    ) {
        if(TokenRepository.verifyTokenCode(tokenCode)){
            int userId = TokenRepository.getUserForToken(tokenCode);
            if(userId>0)
                return ResponseEntity.ok(UserRepository.getCompleteInformation(userId));
            else
                return ResponseEntity.ok("not found");

        }

        return ResponseEntity.ok("incorrect token");


    }

}
