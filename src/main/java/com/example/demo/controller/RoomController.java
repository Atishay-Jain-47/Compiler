package com.example.demo.controller;

import com.example.demo.dto.CollaborationDto;
import com.example.demo.dto.CreateSessionResponseDto;
import com.example.demo.dto.JoinRoomDto;
import com.example.demo.entity.types.Session;
import com.example.demo.manager.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "https://compiler-frontend-six.vercel.app",
                "https://compiler-frontend.satyamvatsal.ovh",
        }
)
public class RoomController {

    private final SessionManager sessionManager;

    @PostMapping("/collab/info")
    public ResponseEntity<CreateSessionResponseDto> createNewRoom(@RequestBody CollaborationDto collaborationDto) {
        log.info("Hello world");
        Session session = sessionManager.createSession(collaborationDto);
        CreateSessionResponseDto createSessionResponseDto = CreateSessionResponseDto.builder()
                .roomId(session.getSessionId())
                .message("Room Created Successfully")
                .build();
        log.info("create session {}",createSessionResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createSessionResponseDto);
    }
    @PostMapping("/collab/join")
    public ResponseEntity<CreateSessionResponseDto> joinRoom(@RequestBody JoinRoomDto joinRoomDto) {
        sessionManager.joinUserToRoom(joinRoomDto);
        CreateSessionResponseDto createSessionResponseDto = CreateSessionResponseDto.builder()
                .roomId(joinRoomDto.getRoomId())
                .message("Connected to Room "+ joinRoomDto.getRoomId())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(createSessionResponseDto);
    }


}
