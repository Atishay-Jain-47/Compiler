package com.example.demo.service;

import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.UserAuthRequestDto;
import com.example.demo.entity.types.User;
import com.example.demo.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Executable;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    public LoginResponseDto login(UserAuthRequestDto loginRequestDto) {
        log.info("Login attempt for user: {}", loginRequestDto.getUserName());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUserName(),
                            loginRequestDto.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            String token = authUtil.generateAccessToken(user);
            log.info("Login successful for user: {}", user.getUsername());

            return LoginResponseDto.builder()
                    .userName(user.getUsername())
                    .token(token)
                    .build();

        } catch (Exception e) {
            log.warn("Authentication failed for user: {} | Reason: {} | Type: {}",
                    loginRequestDto.getUserName(),
                    e.getMessage(),
                    e.getClass().getSimpleName());
            return LoginResponseDto.builder()
                    .userName("")
                    .token("")
                    .build();
        }
    }
}