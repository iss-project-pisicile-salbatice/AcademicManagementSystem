package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.DTO.UserDTO;
import com.pisicilesalbatice.ams.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/users")
    public void updateUser(@RequestBody UserDTO userDto) {
        userService.updateUser(userDto.getUserId(), userDto.getEmail(), userDto.getPhoneNumber());
    }
}
