package com.example.demo.controller;

import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.UserAuthRequestDto;
import com.example.demo.dto.CreateUserResponseDto;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "https://compiler-frontend-six.vercel.app",
                "https://compiler-frontend.satyamvatsal.ovh",
        }
)
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseDto> createNewUser(@RequestBody UserAuthRequestDto userAuthRequestDto) {
        CreateUserResponseDto createUserResponseDto =  userService.createNewUser(userAuthRequestDto);
        return ResponseEntity.ok(createUserResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody UserAuthRequestDto userAuthRequestDto) {
        LoginResponseDto loginResponseDto =  authService.login(userAuthRequestDto);
        System.out.println(userAuthRequestDto.getUserName());
        return ResponseEntity.ok(loginResponseDto);
    }

}
