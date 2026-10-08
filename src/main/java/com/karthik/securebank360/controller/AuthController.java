package com.karthik.securebank360.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.karthik.securebank360.dto.UserResponse;
import com.karthik.securebank360.entity.User;
import com.karthik.securebank360.dto.RegisterRequest;
import com.karthik.securebank360.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UserService userService;

	AuthController(UserService userService) {
		this.userService = userService;
		}
	
	
	@PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        User savedUser = userService.registerUser(user);

        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getRole().name());
        return ResponseEntity.ok(response);
    }
	
	
		
	

}
