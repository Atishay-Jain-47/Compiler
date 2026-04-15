package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YjsPayload {
    private String senderId;
    private String updateBase64; // Used for Yjs binary data
    private String type;         // "UPDATE", "SYNC_REQUEST", "CHAT", etc.
    private String content;      // <--- Add this for Chat messages
}