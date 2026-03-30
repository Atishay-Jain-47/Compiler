package com.example.demo.service;

import com.example.demo.dto.RequestDto;
import com.example.demo.entity.types.Language;
import com.example.demo.entity.types.Session;
import com.example.demo.utils.Commands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CodeRunnerService {
    public void execute(Session session) {
        Language language = session.getLanguage();
        String codePath = session.getCodePath();
        String inputPath = session.getInputPath();
        ArrayList<String> commands = Commands.getCommand(language,codePath);
        String output = "";
        String errors = "";

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.redirectInput(new File(inputPath));
        try{
            Process process = pb.start();
            output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            errors = new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
            process.waitFor();
        } catch (Exception e) {
            log.error("e: ", e);
            throw new RuntimeException(e);
        }
        session.setOutput(output);
        session.setError(errors);

    }
}
