package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.CodeRunnerService;
import com.example.demo.service.SaveFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Slf4j
@RestController
public class CodeController {
    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    private CodeRunnerService codeRunnerService;

    @PostMapping("/run")
    public ResponseEntity<ResponseDto> runCode(@RequestBody RequestDto requestDto){
        String codeAndInputPath = saveFileService.saveFile(requestDto);
        String[] splitArray = codeAndInputPath.split(";");
        String codePath = splitArray[0];
        String inputPath = splitArray[1];
        String output = codeRunnerService.execute(requestDto.getLanguage(),codePath,inputPath);
        System.out.println(output);

        ResponseDto responseDto = ResponseDto
                .builder()
                .output(output)
                .errorCode(0)
                .error("")
                .build();
        return ResponseEntity.status(200).body(responseDto);
    }

}
