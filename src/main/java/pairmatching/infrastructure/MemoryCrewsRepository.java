package pairmatching.infrastructure;

import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryCrewsRepository implements CrewRepository {
    private final Map<DevelopType, Crews> store = new HashMap<>();

    @Override
    public void save(Crews crews) {
        store.put(crews.getDevelopType(), crews);
    }

    @Override
    public Crews findByDevelopType(DevelopType developType) {
        if (!store.containsKey(developType)) {
            throw new IllegalArgumentException("[ERROR] 해당 정보는 존재하지 않습니다");
        }
        return store.get(developType);
    }

    @Override
    public List<Crews> findAll() {
        return List.copyOf(store.values());
    }
}
