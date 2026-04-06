package com.example.demo.entity.types;


import lombok.*;

import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Session {
    private String sessionId;
    private String userName;
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
