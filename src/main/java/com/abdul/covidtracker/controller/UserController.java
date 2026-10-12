package com.abdul.covidtracker.controller;

import com.abdul.covidtracker.entity.User;
import com.abdul.covidtracker.dto.LoginRequest;
import com.abdul.covidtracker.dto.RegisterRequest;
import com.abdul.covidtracker.dto.UserDto;
import com.abdul.covidtracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.registerUser(
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getFirstName(),
                    request.getLastName()
            );
            return ResponseEntity.ok(new RegisterResponse("User registered successfully", userService.convertToDto(user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userService.loginUser(request.getUsername(), request.getPassword());
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(new LoginResponse("Login successful", userService.convertToDto(userOpt.get())));
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("Invalid username or password"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userService.convertToDto(userOpt.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            User updatedUser = userService.updateUser(id, userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
            return ResponseEntity.ok(new UpdateResponse("User updated successfully", userService.convertToDto(updatedUser)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Update failed: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ErrorResponse("User deleted successfully"));
    }

    // Inner classes for responses
    public static class RegisterResponse {
        private String message;
        private UserDto user;

        public RegisterResponse(String message, UserDto user) {
            this.message = message;
            this.user = user;
        }

        public String getMessage() { return message; }
        public UserDto getUser() { return user; }
    }

    public static class LoginResponse {
        private String message;
        private UserDto user;

        public LoginResponse(String message, UserDto user) {
            this.message = message;
            this.user = user;
        }

        public String getMessage() { return message; }
        public UserDto getUser() { return user; }
    }

    public static class UpdateResponse {
        private String message;
        private UserDto user;

        public UpdateResponse(String message, UserDto user) {
            this.message = message;
            this.user = user;
        }

        public String getMessage() { return message; }
        public UserDto getUser() { return user; }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() { return message; }
    }
}
