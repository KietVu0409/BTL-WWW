package com.project.courseRegistration.services;

import com.project.courseRegistration.dtos.StudentDTO;
import com.project.courseRegistration.dtos.UserDTO;
import com.project.courseRegistration.models.Role;
import com.project.courseRegistration.models.User;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserService {
    User insertUser(UserDTO userDTO) throws Exception;
    void saveUser(StudentDTO studentDTO) ;

    String forgotPass(String email);

    String resetPass(String token, String password);

    static String generateToken() {
        StringBuilder token = new StringBuilder();
        while (token.length() < 5) {
            token.append(UUID.randomUUID().toString().replace("-", ""));
        }
        return token.substring(0, 5);
    }

    boolean isTokenExpired(final LocalDateTime tokenCreationDate);
}
