package pairmatching.interfaces.adapter.inbound;

import pairmatching.domain.Course;
import pairmatching.domain.Mission;

public record Query(Course course, Mission mission) {
}
