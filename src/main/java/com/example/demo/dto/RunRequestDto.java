package com.example.demo.dto;
import com.example.demo.entity.types.Language;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RunRequestDto {
    private String userName;

    private Language language;

    private String input;

    private String code;

}
