package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Slf4j
@RestController
public class CodeController {

    @PostMapping("/run")
    public void runCode(@RequestBody RequestDto requestDto){
        System.out.println(requestDto.getCode());
        System.out.println(requestDto.getLanguage());
        System.out.println(requestDto.getInput());

        saveFile(requestDto.getCode(), "main.py");
        saveFile(requestDto.getInput(), "input.txt");

        String codePath = "/home/satyam/test/main.py";
        String inputPath = "/home/satyam/test/input.txt";
        String output = runCode(codePath, inputPath);

        saveFile(output, "output.txt");
        System.out.println(output);
    }

    public void saveFile(String code, String fileName){
        try {
            String path = "/home/satyam/test/" + fileName;
            File Obj = new File(path);

            // Creating File
            if (Obj.createNewFile()) {
                System.out.println("File created: " + Obj.getName());
            }
            else {
                System.out.println("File already exists.");
            }

            FileWriter Writer = new FileWriter(path);

            // Writing File
            Writer.write(code);
            Writer.close();

        }

        // Exception Thrown
        catch (IOException e) {
            System.out.println("An error has occurred.");
        }
    }

    public String runCode(String codePath, String inputPath){

        String output = "";

        ProcessBuilder pb = new ProcessBuilder("/usr/bin/python", codePath);
        pb.redirectInput(new File(inputPath)); // This handles the "<" functionality


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
