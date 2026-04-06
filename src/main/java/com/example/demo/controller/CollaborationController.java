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
        log.info("payload {} {}",payload.getSenderId(),payload.getUpdateBase64());
        return payload;
    }

}