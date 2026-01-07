package pairmatching.infrastructure;

import pairmatching.application.port.outbound.PairRepository;
import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;
import pairmatching.domain.Mission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPairRepository implements PairRepository {
    private final Map<DevelopType, Map<Mission, Crews>> store = new HashMap<>();


    @Override
    public void save(Crews crews) {
        DevelopType developType = crews.getDevelopType();
        Mission mission = crews.getMission();
        store.computeIfAbsent(developType, x -> new HashMap<>())
                .put(mission, crews);
    }

    @Override
    public Crews findByMissionAndDevelopType(DevelopType developType, Mission mission) {
        if (!store.containsKey(developType)) {
            throw new IllegalArgumentException("[ERROR] 해당 코스 정보는 존재하지 않습니다");
        }
        Map<Mission, Crews> missionCrewsMap = store.get(developType);
        if (!missionCrewsMap.containsKey(mission)) {
            throw new IllegalArgumentException("[ERROR] 해당 과제 정보는 존재하지 않습니다");
        }
        return missionCrewsMap.get(mission);
    }

    @Override
    public boolean isProgressMission(DevelopType developType, Mission mission) {
        if (store.containsKey(developType)) {
            return store.get(developType).containsKey(mission);
        }
        return false;
    }

    @Override
    public void removeIf(DevelopType developType, Mission mission) {
        if (store.containsKey(developType)) {
            store.get(developType).remove(mission);
        }
    }

    @Override
    public void clear() {
        store.clear();
    }

}
