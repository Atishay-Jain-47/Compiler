package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YjsPayload {
    private String senderId;
    private String updateBase64;
    private String type;
}