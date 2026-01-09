package attendance.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class FileReader {

    private FileReader() {
    }

    public List<String> readFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filePath))) {
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 읽을 수 없는 파일 입니다.");
        }
    }

}
