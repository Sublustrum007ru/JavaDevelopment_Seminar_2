package controller;

import java.util.List;

public interface Operation {
    void createFile(String path);

    List<String> readFile(String path);

    void writeFile(String path, String message);
} 
