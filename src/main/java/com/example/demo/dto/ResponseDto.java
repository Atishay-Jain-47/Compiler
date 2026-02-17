package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String output;

    private String error;

    private Integer errorCode;

}
