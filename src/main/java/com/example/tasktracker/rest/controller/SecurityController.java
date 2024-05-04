package com.example.tasktracker.rest.controller;

import com.example.tasktracker.rest.dto.UserLoginDto;
import com.example.tasktracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/hr-rest/security")
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;

    /*@PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateEmployee(@RequestBody UserLoginDto userLoginDto) {
        String jwtToken = userService.loginUser(userLoginDto);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }*/
}
