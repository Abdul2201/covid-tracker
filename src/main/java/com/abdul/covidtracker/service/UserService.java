package com.abdul.covidtracker.service;

import com.abdul.covidtracker.entity.User;
import com.abdul.covidtracker.dto.UserDto;
import com.abdul.covidtracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String email, String password, 
                            String firstName, String lastName) throws Exception {
        if (userRepository.existsByUsername(username)) {
            throw new Exception("Username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new Exception("Email already exists");
        }

        User user = new User(username, email, password, firstName, lastName);
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, String firstName, String lastName, String email) throws Exception {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new Exception("User not found");
        }

        User user = userOpt.get();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        
        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new Exception("Email already in use");
        }
        user.setEmail(email);
        user.setUpdatedAt(LocalDateTime.now());
        
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getIsActive()
        );
    }
}
