package attendance.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileReaderService {

    public List<String> readFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new IllegalArgumentException("읽을 수 없는 파일 입니다.");
        }
    }

}
