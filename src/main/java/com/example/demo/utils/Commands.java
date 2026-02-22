package com.example.demo.utils;

import com.example.demo.entity.types.Language;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Commands {
    public static ArrayList<String> getCommand(Language language,String codePath) {
        String executablePath = codePath+"Executable";
        System.out.println(executablePath);
        ArrayList<String> command = new ArrayList<>();
        switch (language) {
            case Language.PYTHON:
                command.add("/usr/bin/python");
                command.add(codePath);
                break;
            case Language.CPP:
                command.add("/usr/bin/sh");
                command.add("-c");
                command.add("/usr/bin/g++ " + codePath + " -o " + executablePath + " && "+executablePath);
                break;
            case Language.C:
                command.add("/usr/bin/sh");
                command.add("-c");
                command.add("/usr/bin/gcc " + codePath + " -o " + executablePath + " && " + executablePath);
            case Language.JAVA:
                command.add("/usr/bin/sh");
                command.add("-c");
                command.add("/usr/bin/java " + codePath);
            case Language.GO:
                command.add("/usr/bin/sh");
                command.add("-c");
                command.add("/usr/bin/go" +" run "+ codePath);
        }
        return command;
    }
}
