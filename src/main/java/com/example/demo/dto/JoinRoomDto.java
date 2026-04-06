package com.example.demo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class JoinRoomDto {
    private String userName;
    private String roomId;
}
