package com.example.demo.manager;

import com.example.demo.dto.RequestDto;
import com.example.demo.entity.types.Session;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

@Service
public class SessionManager {
    private final HashMap<String, Session> userToSessionMap = new HashMap<>();
    private final HashSet<Integer> sessionIdSet = new HashSet<>();

    public Session getSessionByUserId(String userId) {
        if (!userToSessionMap.containsKey(userId)) {
            return null;
        }
        return userToSessionMap.get(userId);
    }

    public Session createSession(RequestDto requestDto) {
        Session session = Session.builder()
                .sessionId(getNewSessionId())
                .userId(requestDto.getUserName())
                .code(requestDto.getCode())
                .language(requestDto.getLanguage())
                .codePath("")
                .input(requestDto.getInput())
                .inputPath("")
                .output("")
                .error("")
                .timeTaken(0.0)
                .memoryUsed(0.0)
                .build();

        userToSessionMap.put(requestDto.getUserName(),session);
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
        sessionIdSet.add(sessionId);
        return String.valueOf(sessionId);
    }


}
