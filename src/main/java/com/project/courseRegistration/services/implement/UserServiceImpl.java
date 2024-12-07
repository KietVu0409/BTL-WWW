package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.dtos.StudentDTO;
import com.project.courseRegistration.dtos.UserDTO;
import com.project.courseRegistration.exceptions.DataNotFoundException;
import com.project.courseRegistration.models.Role;
import com.project.courseRegistration.models.User;
import com.project.courseRegistration.repositories.RoleRepository;
import com.project.courseRegistration.repositories.UserRepository;
import com.project.courseRegistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private static final long EXPIRE_TOKEN=30;

    @Override
    public User insertUser(UserDTO userDTO) throws Exception{

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }

        User newUser = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(Arrays.asList(role))
                .build();
        return userRepository.save(newUser);
    }
    @Override
    public void saveUser(StudentDTO studentDTO) {
        User user = new User();
        user.setUsername(studentDTO.getId().toString());
        user.setEmail(studentDTO.getEmail());
        user.setPassword(passwordEncoder.encode("1111"));
        Role role = roleRepository.findByName("ROLE_STUDENT");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_STUDENT");
        return roleRepository.save(role);
    }

    @Override
    public String forgotPass(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setToken(UserService.generateToken());
        user.setTokenCreationDate(LocalDateTime.now());
        user = userRepository.save(user);

        return user.getToken();
    }

    @Override
    public String resetPass(String token, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByToken(token));

        if (!userOptional.isPresent()) {
            return "Invalid token";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();
        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(password));
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);

        return "Your password successfully updated.";
    }

    @Override
    public boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);
        return diff.toMinutes() >= EXPIRE_TOKEN;
    }




}
