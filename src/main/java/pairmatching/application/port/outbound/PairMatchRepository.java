package pairmatching.application.port.outbound;

import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Mission;

public interface PairMatchRepository {
    void save(Crews crews);
    Crews findByMatchingInfo(Course course, Mission mission);
    void clear();

    boolean isMatch(Course course, Mission mission);
}
