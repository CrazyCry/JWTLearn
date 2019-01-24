package meng.learn.userservice.controller;

import meng.learn.userservice.dto.UserLoginDTO;
import meng.learn.userservice.entity.User;
import meng.learn.userservice.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceDetail userServiceDetail;

    @PostMapping("/register")
    public User postUser(@RequestParam("username")String username,@RequestParam("password") String password){
        return userServiceDetail.insertUser(username,password);
    }
    @RequestMapping("/login")
    public UserLoginDTO login(@RequestParam("username")String username,@RequestParam("password") String password){
        return userServiceDetail.login(username,password);
    }
}
