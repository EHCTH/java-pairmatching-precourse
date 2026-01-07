package pairmatching.infrastructure;

import pairmatching.application.port.outbound.PairMatchRepository;
import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Mission;

import java.util.*;

public class MemoryPairMatchRepository implements PairMatchRepository {
    private final Map<Course, Map<Mission, Crews>> store = new HashMap<>();

    @Override
    public void save(Crews crews) {
        store.computeIfAbsent(crews.getCourse(), (x) -> new HashMap<>()).put(crews.getMission(), crews);
    }

    @Override
    public Crews findByMatchingInfo(Course course, Mission mission) {
        return Optional.ofNullable(store.get(course))
                .map(x -> x.get(mission))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 정보는 존재하지 않습니다"));
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public boolean isMatch(Course course, Mission mission) {
        if (store.containsKey(course)) {
            return store.get(course).containsKey(mission);
        }
        return false;
    }
}
