package com.example.demo.service;

import com.example.demo.dto.RequestDto;
import com.example.demo.entity.types.Language;
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
    public String execute(Language language,String codePath, String inputPath) {
        ArrayList<String> commands = Commands.getCommand(language,codePath);
        String output = "";

        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.redirectInput(new File(inputPath));
        try{
            Process process = pb.start();
            output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            process.waitFor();
        } catch (Exception e) {
            log.error("e: ", e);
            throw new RuntimeException(e);
        }
        return output;

    }
}
