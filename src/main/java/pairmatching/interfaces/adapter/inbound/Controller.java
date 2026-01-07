package pairmatching.interfaces.adapter.inbound;

import pairmatching.application.port.inbound.PairMatchUseCase;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private final PairMatchUseCase useCase;
    private final Map<MenuOptionType, Runnable> menuOptions;
    private final InputView inputView;
    private final OutputView outputView;


    public Controller(PairMatchUseCase useCase, InputView inputView, OutputView outputView) {
        this.useCase = useCase;
        this.inputView = inputView;
        this.outputView = outputView;
        this.menuOptions = Map.of(
                MenuOptionType.MATCH, this::match,
                MenuOptionType.SEARCH, this::search,
                MenuOptionType.CLEAR, this::clear,
                MenuOptionType.QUIT, () -> {
                }
        );
    }

    public void run() {
        MenuOptionType menuOptionType;
        do {
            menuOptionType = getRetryUntilSuccess(() -> {
                String code = inputView.promptMenu();
                return MenuOptionType.findByCode(code);
            });
            menuOptions.get(menuOptionType).run();
        } while (menuOptionType.isRetry());
    }

    private void match() {
        List<String> match = getRetryUntilSuccess(() -> {
            outputView.displayHeader();
            Query query = inputView.promptQueryFirst();
            if (useCase.isMatch(query.course(), query.mission())) {
                UserStatus userStatus = inputView.promptStatusFirst();
                if (userStatus.isNo()) {
                    return includeMatchInfo();
                }
            }
            return useCase.match(query.course(), query.mission());
        });
        outputView.displayByResult(match);
    }

    private List<String> includeMatchInfo() {
        Query query = inputView.promptQuery();
        if (useCase.isMatch(query.course(), query.mission())) {
            UserStatus userStatus = inputView.promptStatus();
            if (userStatus.isNo()) {
                return includeMatchInfo();
            }
        }
        return useCase.match(query.course(), query.mission());
    }

    private void search() {
        List<String> search = getRetryUntilSuccess(() -> {
            outputView.displayHeader();
            Query query = inputView.promptQueryFirst();
            return useCase.findByMatched(query.course(), query.mission());
        });
        outputView.displayByResult(search);
    }

    private void clear() {
        useCase.clear();
        outputView.displayClear();
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
