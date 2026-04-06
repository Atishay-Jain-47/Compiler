package com.example.demo.manager;

import com.example.demo.dto.CollaborationDto;
import com.example.demo.dto.JoinRoomDto;
import com.example.demo.dto.RunRequestDto;
import com.example.demo.entity.types.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

@Slf4j
@Service
public class SessionManager {
    private final HashMap<String, Session> sessionIdToSession = new HashMap<>();
    private final HashMap<String, Session> userToSessionMap = new HashMap<>();
    private final HashSet<String> sessionIdSet = new HashSet<>();
    private final HashMap<String, ArrayList<String>> sessionIdToUsers = new HashMap<>();

    public Session getSessionByUserId(String userId) {
        if (!userToSessionMap.containsKey(userId)) {
            return null;
        }
        return userToSessionMap.get(userId);
    }

    public Session createSession(CollaborationDto collaborationDto) {
        Session session = Session.builder()
                .sessionId(getNewSessionId())
                .userName(collaborationDto.getUserName())
                .code(collaborationDto.getCode())
                .language(collaborationDto.getLanguage())
                .codePath("")
                .input(collaborationDto.getInput())
                .inputPath("")
                .output("")
                .error("")
                .timeTaken(0.0)
                .memoryUsed(0.0)
                .build();
        userToSessionMap.put(collaborationDto.getUserName(),session);
        sessionIdToSession.put(session.getSessionId(),session);
        sessionIdSet.add(session.getSessionId());
        sessionIdToUsers.putIfAbsent(session.getSessionId(), new ArrayList<>());
        sessionIdToUsers.get(session.getSessionId()).add(collaborationDto.getUserName());
        log.info("session {}",session);
        return session;
    }
    public void joinUserToRoom(JoinRoomDto joinRoomDto) {
        Session session = sessionIdToSession.get(joinRoomDto.getRoomId());
        sessionIdToUsers.get(joinRoomDto.getRoomId()).add(joinRoomDto.getUserName());
    }
    public Session createSession(RunRequestDto runRequestDto) {
        Session session = Session.builder()
                .sessionId(getNewSessionId())
                .userName(runRequestDto.getUserName())
                .code(runRequestDto.getCode())
                .language(runRequestDto.getLanguage())
                .codePath("")
                .input(runRequestDto.getInput())
                .inputPath("")
                .output("")
                .error("")
                .timeTaken(0.0)
                .memoryUsed(0.0)
                .build();

        userToSessionMap.put(runRequestDto.getUserName(),session);
        sessionIdToSession.put(session.getSessionId(),session);
        sessionIdSet.add(session.getSessionId());
        sessionIdToUsers.putIfAbsent(session.getSessionId(), new ArrayList<>());
        sessionIdToUsers.get(session.getSessionId()).add(runRequestDto.getUserName());
        return session;
    }

    private String getNewSessionId() {
        int min = 100000;
        int max = 999999;
        Random random = new Random();
        int sessionId;
        while(true) {
            sessionId = random.nextInt((max - min) + 1) + min;
            if(!sessionIdSet.contains(sessionId)) {
                break;
            }
        }
        sessionIdSet.add(String.valueOf(sessionId));
        return String.valueOf(sessionId);
    }
}
