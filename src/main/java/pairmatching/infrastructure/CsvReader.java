package pairmatching.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvReader {
    private static final String BACKEND_PATH = "/backend-crew.md";
    private static final String FRONTEND_PATH = "/frontend-crew.md";


    public List<String> readBackend() {
        return readFile(BACKEND_PATH);
    }

    public List<String> readFront() {
        return readFile(FRONTEND_PATH);
    }

    private static List<String> readFile(String path) {
        InputStream resourceAsStream = CsvReader.class.getResourceAsStream(path);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("[ERROR] 해당 파일이 존재하지 않습니다");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8))) {
            return br.lines()
                    .map(String::trim)
                    .filter(line -> !line.isBlank())
                    .toList();
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 해당 파일이 존재하지 않습니다", e);

        }
    }

}
