package com.example.demo.dto;

import com.example.demo.entity.types.Language;
import com.example.demo.entity.types.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollaborationDto {
    private String userName;
    private String code;
    private Language language;
    private String input;
}
