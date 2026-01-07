package pairmatching.domain;

import java.util.List;

public interface ShufflePolicy {
    List<String> shuffle(List<String> data);
}
