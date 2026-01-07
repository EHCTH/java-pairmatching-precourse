package pairmatching.bootstrap;

import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.infrastructure.CsvReader;

import java.util.List;
import java.util.stream.Collectors;

public class DataInitializer {
    private final CrewRepository repository;
    private final CsvReader reader;

    public DataInitializer(CrewRepository repository, CsvReader reader) {
        this.repository = repository;
        this.reader = reader;
    }

    public void init() {
        Crews backend = createCrews(reader.readBackend(), Course.BACKEND);
        Crews frontend = createCrews(reader.readFront(), Course.FRONTEND);
        repository.save(backend);
        repository.save(frontend);
    }

    private Crews createCrews(List<String> data, Course course) {
        return data.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        crews -> Crews.of(course, crews)
                ));
    }
}
