package pairmatching.bootstrap;

import pairmatching.application.port.inbound.MatchingUseCase;
import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.application.port.outbound.PairRepository;
import pairmatching.application.service.MatchingService;
import pairmatching.infrastructure.FileReader;
import pairmatching.infrastructure.MemoryCrewsRepository;
import pairmatching.infrastructure.MemoryPairRepository;
import pairmatching.infrastructure.RandomShufflePolicy;
import pairmatching.interfaces.adapter.inbound.Controller;
import pairmatching.interfaces.adapter.inbound.InputView;
import pairmatching.interfaces.adapter.inbound.OutputView;

public class AppConfig {
    private final CrewRepository crewRepository = new MemoryCrewsRepository();
    private final PairRepository pairRepository = new MemoryPairRepository();
    private final DataInitializer initializer = new DataInitializer(crewRepository, new FileReader());
    private final MatchingUseCase matchingUseCase = new MatchingService(new RandomShufflePolicy(), crewRepository, pairRepository);

    public Controller controller() {
        initializer.init();
        return new Controller(matchingUseCase, new InputView(), new OutputView());
    }

}
