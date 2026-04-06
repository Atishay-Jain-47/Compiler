package com.example.demo.controller;

import com.example.demo.dto.RunRequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.types.Session;
import com.example.demo.manager.SessionManager;
import com.example.demo.service.CodeRunnerService;
import com.example.demo.service.SaveFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(
    origins = {
        "http://localhost:5173",
        "https://compiler-frontend-six.vercel.app",
        "https://compiler-frontend.satyamvatsal.ovh",
    }
)
public class CodeController {

    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    private CodeRunnerService codeRunnerService;

    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/run")
    public ResponseEntity<ResponseDto> runCode(
        @RequestBody RunRequestDto runRequestDto
    ) {
        System.out.println(runRequestDto);
        Session session = sessionManager.createSession(runRequestDto);
        saveFileService.saveFile(session);
        codeRunnerService.execute(session);
        String output = session.getOutput();
        String error = session.getError();

        System.out.println("output: " + output);

        ResponseDto responseDto = ResponseDto.builder()
            .output(output)
            .errorCode(0)
            .error(error)
            .build();
        return ResponseEntity.status(200).body(responseDto);
    }
}
