package pairmatching.interfaces.adapter.inbound;

import pairmatching.application.port.inbound.MatchingUseCase;
import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;
import pairmatching.domain.Mission;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private final MatchingUseCase useCase;
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<MenuOptionType, Runnable> optionTypeRunnableMap;

    public Controller(MatchingUseCase useCase, InputView inputView, OutputView outputView) {
        this.useCase = useCase;
        this.inputView = inputView;
        this.outputView = outputView;
        this.optionTypeRunnableMap = Map.of(
                MenuOptionType.MATCHING, this::match,
                MenuOptionType.SEARCH, this::search,
                MenuOptionType.INIT, this::clear,
                MenuOptionType.QUIT, () -> {
                }
        );
    }
    public void run() {
        MenuOptionType menuOptionType;
        do {
            menuOptionType = getRetryUntilSuccess(inputView::menuOptionType);
            optionTypeRunnableMap.get(menuOptionType).run();

        } while(menuOptionType.isRetry());
    }

    private void match() {
        List<String> success = getRetryUntilSuccess(() -> {
            List<String> strings = inputView.promptData();
            DevelopType developType = DevelopType.findByCode(strings.getFirst());
            Mission mission = Mission.findByMission(strings.get(1), strings.getLast());
            if (useCase.isContainsMatchingInfo(developType, mission)) {
                String s = inputView.promptAsk();
                if (s.equals("네")) {
                    return useCase.matching(developType, mission);
                }
                return retry();
            }
            return useCase.matching(developType, mission);
        });
        outputView.display(success);
    }

    private List<String> retry() {
        List<String> strings = inputView.retryAsk();
        DevelopType developType = DevelopType.findByCode(strings.getFirst());
        Mission mission = Mission.findByMission(strings.get(1), strings.getLast());
        if (useCase.isContainsMatchingInfo(developType, mission)) {
            String s = inputView.retryAskRetry();
            if (s.equals("네")) {
                return useCase.matching(developType, mission);
            }
            return retry();
        }
        return useCase.matching(developType, mission);
    }

    private void search() {
        List<String> success = getRetryUntilSuccess(() -> {
            List<String> strings = inputView.promptData();
            DevelopType developType = DevelopType.findByCode(strings.getFirst());
            Mission mission = Mission.findByMission(strings.get(1), strings.getLast());
            return useCase.search(developType, mission);
        });
        outputView.display(success);
    }


    private void clear() {
        useCase.clear();
        outputView.displayInit();
    }

    private <T> T getRetryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
