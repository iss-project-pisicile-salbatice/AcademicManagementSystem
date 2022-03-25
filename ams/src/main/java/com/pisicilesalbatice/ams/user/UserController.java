package com.pisicilesalbatice.ams.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        userService.addUser(new User("userName1", "password1", 1, "Pop Andrei", "A student"));
        userService.addUser(new User("userName1", "password2", 1, "Popan Andreea", "Another student"));
        return userService.getUsers();
    }
}
