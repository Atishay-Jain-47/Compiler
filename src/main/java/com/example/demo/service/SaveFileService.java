package com.example.demo.service;

import com.example.demo.dto.RequestDto;
import com.example.demo.entity.types.Language;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class SaveFileService {
    @Value("${BASE_DIR}")
    private String BASE_DIR;
    public String saveFile(RequestDto requestDto)  {
        Language language = requestDto.getLanguage();
        String userName = requestDto.getUserName();
        String code = requestDto.getCode();
        String input = requestDto.getInput();

        System.out.println(code);

        String dirPath = BASE_DIR + userName + "/";
        String fileName = switch (language) {
            case Language.PYTHON -> "main.py";
            case Language.CPP -> "main.cpp";
            case Language.C -> "main.c";
            case Language.JAVA -> "main.java";
            case Language.GO -> "main.go";
            default -> throw new IllegalArgumentException(language + " is not supported");
        };
        String codeFilePath = dirPath + fileName;
        String inputFilePath = dirPath + "input.txt";
        save(code,dirPath, fileName);
        save(input,dirPath,"input.txt");
        return codeFilePath + ";" +inputFilePath;
    }
    private void save(String content,String dirPath ,String fileName)  {
        try {
            Files.createDirectories(Paths.get(dirPath));
            String filePath = dirPath + fileName;
            File file = new File(filePath);
            System.out.println(file);
            if (!file.exists())  {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                }
            }
            FileWriter Writer = new FileWriter(filePath);
            Writer.write(content);
            Writer.close();
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");

        }
    }

}
