package pairmatching.application.port.outbound;

import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;
import pairmatching.domain.Mission;

import java.util.List;

public interface PairRepository {
    void save(Crews crews);
    Crews findByMissionAndDevelopType(DevelopType developType, Mission mission);

    boolean isProgressMission(DevelopType developType, Mission mission);

    void removeIf(DevelopType developType, Mission mission);

    void clear();
}
