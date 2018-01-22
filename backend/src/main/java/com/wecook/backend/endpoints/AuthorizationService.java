package com.wecook.backend.endpoints;


import com.wecook.backend.models.User;
import com.wecook.backend.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Controller
public class AuthorizationService {
    @RequestMapping(value = "/v1/users/login", produces = {"application/json"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestParam(value = "email", required = true) String email
            , @RequestParam(value = "password", required=true) String password

    ) {



            return ResponseEntity.ok(UserRepository.login(email,password));

    }

}
