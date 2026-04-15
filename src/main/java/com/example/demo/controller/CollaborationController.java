package com.example.demo.controller;

import com.example.demo.dto.YjsPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CollaborationController {

    @MessageMapping("/editor.sync/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public YjsPayload handleYjsSync(@DestinationVariable String roomId, YjsPayload payload) {

        // Log based on type to keep your console clean
        if ("CHAT".equals(payload.getType())) {
            log.info("Chat in Room {}: [{}] says {}", roomId, payload.getSenderId(), payload.getContent());
        } else {
            log.info("Sync Update in Room {}: Type {} from {}", roomId, payload.getType(), payload.getSenderId());
        }

        // Return the payload: Spring broadcasts this to all subscribers of /topic/room/{roomId}
        return payload;
    }
}    