package pairmatching.application.port.outbound;

import pairmatching.domain.Crews;
import pairmatching.domain.DevelopType;

import java.util.List;

public interface CrewRepository {
    void save(Crews crews);

    Crews findByDevelopType(DevelopType developType);

    List<Crews> findAll();
}
