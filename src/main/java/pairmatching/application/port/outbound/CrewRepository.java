package pairmatching.application.port.outbound;

import pairmatching.domain.Course;
import pairmatching.domain.Crews;

public interface CrewRepository {
    void save(Crews crews);

    Crews findByCourse(Course course);

}
