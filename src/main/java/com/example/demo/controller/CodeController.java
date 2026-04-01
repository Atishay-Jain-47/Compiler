package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.types.Session;
import com.example.demo.manager.SessionManager;
import com.example.demo.service.CodeRunnerService;
import com.example.demo.service.SaveFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Slf4j
@RestController
@CrossOrigin(origins = {"https://compiler-frontend-six.vercel.app/","http://localhost:5173","https://compiler.satyamvatsal.ovh"})
public class CodeController {
    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    private CodeRunnerService codeRunnerService;
    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/run")
    public ResponseEntity<ResponseDto> runCode(@RequestBody RequestDto requestDto){
        Session session = sessionManager.createSession(requestDto);
        saveFileService.saveFile(session);
        codeRunnerService.execute(session);
        String output = session.getOutput();
        String error = session.getError();

        System.out.println("output: "+output);

        ResponseDto responseDto = ResponseDto
                .builder()
                .output(output)
                .errorCode(0)
                .error(error)
                .build();
        return ResponseEntity.status(200).body(responseDto);
    }

}
