package pairmatching.application.port.inbound;

import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;
import pairmatching.domain.Mission;

import java.util.List;

public interface MatchingUseCase {
    List<String> matching(DevelopType developType, Mission mission);

    List<String> search(DevelopType developType, Mission mission);

    void clear();

    boolean isContainsMatchingInfo(DevelopType developType, Mission mission);
}
