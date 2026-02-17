package com.example.demo.dto;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private String language;

    private String input;

    private String code;

}
