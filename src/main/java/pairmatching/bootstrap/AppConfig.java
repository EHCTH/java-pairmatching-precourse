package pairmatching.bootstrap;

import pairmatching.application.port.inbound.PairMatchUseCase;
import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.application.port.outbound.PairMatchRepository;
import pairmatching.application.service.PairMatchService;
import pairmatching.domain.ShufflePolicy;
import pairmatching.infrastructure.CsvReader;
import pairmatching.infrastructure.MemoryCrewRepository;
import pairmatching.infrastructure.MemoryPairMatchRepository;
import pairmatching.infrastructure.RandomShufflePolicy;
import pairmatching.interfaces.adapter.inbound.Controller;
import pairmatching.interfaces.adapter.inbound.InputView;
import pairmatching.interfaces.adapter.inbound.OutputView;

public class AppConfig {
    private final CrewRepository crewRepository = new MemoryCrewRepository();
    private final PairMatchRepository pairMatchRepository = new MemoryPairMatchRepository();
    private final ShufflePolicy shufflePolicy = new RandomShufflePolicy();
    private final PairMatchUseCase pairMatchUseCase = new PairMatchService(crewRepository, pairMatchRepository, shufflePolicy);
    private final DataInitializer dataInitializer = new DataInitializer(crewRepository, new CsvReader());

    public Controller controller() {
        dataInitializer.init();
        return new Controller(pairMatchUseCase, new InputView(), new OutputView());
    }
}
