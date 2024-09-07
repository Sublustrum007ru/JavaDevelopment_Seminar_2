package controller.impl;

import controller.Operation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperation implements Operation {
    private static final String PATH_PREFICX = "src/Logs/";

    @Override
    public void createFile(String path) {
        try {
            checkFolfer();
            File file = new File(PATH_PREFICX + path);
            if (!file.createNewFile()) {
                System.out.println("Create file");
                createFile(PATH_PREFICX + path);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> readFile(String path) {
        List<String> result = new ArrayList<>();
        try {
            File file = new File(PATH_PREFICX + path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                result.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            createFile(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void writeFile(String path, String message) {
        checkFolfer();
        try (FileWriter wFile = new FileWriter(PATH_PREFICX + path, true)) {
            wFile.append(message + '\n');
            wFile.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkFolfer() {
        File theDir = new File(PATH_PREFICX);
        if (!theDir.exists()) {
            theDir.mkdir();
        }
    }
}
