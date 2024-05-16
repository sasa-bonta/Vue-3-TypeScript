package org.example.fancy_project.classes.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public User createUser(@RequestBody CreateUserRequest createUserRequest){
        User user = User.builder()
                .email(createUserRequest.getEmail())
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .build();
        return userService.create(user);
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.getAll();
    }
}
