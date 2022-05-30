package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.User;
import com.pisicilesalbatice.ams.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Long uId, String email, String phoneNumber){
        User user = userRepository.getById(uId);
        user.setEmail(email);
        user.setPhone(phoneNumber);
        userRepository.save(user);
    }
}
