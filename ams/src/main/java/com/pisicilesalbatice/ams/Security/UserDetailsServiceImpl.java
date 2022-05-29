package com.pisicilesalbatice.ams.Security;

import com.pisicilesalbatice.ams.Model.ERole;
import com.pisicilesalbatice.ams.Model.Role;
import com.pisicilesalbatice.ams.Model.User;
import com.pisicilesalbatice.ams.Repository.UserRepository;
import com.pisicilesalbatice.ams.Service.StudentService;
import com.pisicilesalbatice.ams.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Integer entityId = null;
        // Find the entity id associated with the student or teacher (if it exists)
        if(user.getRoles().stream().anyMatch(role -> role.getName().equals(ERole.ROLE_STUDENT)))
        {
            entityId = studentService.findStudentByUser(user);
        }
        else if(user.getRoles().stream().anyMatch(role -> role.getName().equals(ERole.ROLE_TEACHER)))
        {
            entityId = teacherService.findTeacherByUser(user);
        }

        return UserDetailsImpl.build(user, entityId);
    }
}