package pairmatching.infrastructure;

import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileReader {
    private static final String BACKEND_PATH = "/backend-crew.md";
    private static final String FRONT_PATH = "/frontend-crew.md";

    public Crews readLineBackend() {
        InputStream resourceAsStream = FileReader.class.getResourceAsStream(BACKEND_PATH);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("[ERROR] 파일이 존재하지 않습니다");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            return br.lines()
                    .map(x -> new Crew(DevelopType.BACKEND, x))
                    .collect(Collectors.collectingAndThen(
                            Collectors.toList(),
                            x -> Crews.of(DevelopType.BACKEND, x)
                    ));

        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일이 존재하지 않습니다");
        }

    }
    public Crews readLineFrontEnd() {
        InputStream resourceAsStream = FileReader.class.getResourceAsStream(FRONT_PATH);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("[ERROR] 파일이 존재하지 않습니다");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            return br.lines()
                    .map(x -> new Crew(DevelopType.FRONTEND, x))
                    .collect(Collectors.collectingAndThen(
                            Collectors.toList(),
                            x -> Crews.of(DevelopType.FRONTEND, x)
                    ));

        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 파일이 존재하지 않습니다");
        }
    }


}
