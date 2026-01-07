package pairmatching.infrastructure;

import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.domain.Course;
import pairmatching.domain.Crews;

import java.util.HashMap;
import java.util.Map;

public class MemoryCrewRepository implements CrewRepository {
    private final Map<Course, Crews> store = new HashMap<>();


    @Override
    public void save(Crews crews) {
        store.put(crews.getCourse(), crews);
    }

    @Override
    public Crews findByCourse(Course course) {
        return store.get(course);
    }
}
