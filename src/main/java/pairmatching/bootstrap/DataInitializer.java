package pairmatching.bootstrap;

import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.domain.Crews;
import pairmatching.infrastructure.FileReader;

public class DataInitializer {
    private final CrewRepository crewRepository;
    private final FileReader reader;

    public DataInitializer(CrewRepository crewRepository, FileReader reader) {
        this.crewRepository = crewRepository;
        this.reader = reader;
    }
    public void init() {
        Crews backend = reader.readLineBackend();
        Crews front = reader.readLineFrontEnd();
        crewRepository.save(backend);
        crewRepository.save(front);
    }
}
