package com.example.demo.entity.types;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Session {
    private String sessionId;
    private String userId;
    private Language language;
    private String code;
    private String codePath;
    private String input;
    private String inputPath;
    private String output;
    private String error;
    private double timeTaken;
    private double memoryUsed;
}
