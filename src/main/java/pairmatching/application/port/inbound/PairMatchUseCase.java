package pairmatching.application.port.inbound;

import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Mission;

import java.util.List;

public interface PairMatchUseCase {
    List<String> match(Course course, Mission mission);

    List<String> findByMatched(Course course, Mission mission);

    boolean isMatch(Course course, Mission mission);

    void clear();
}
