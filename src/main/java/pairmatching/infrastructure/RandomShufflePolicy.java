package pairmatching.infrastructure;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.ShufflePolicy;

import java.util.List;

public class RandomShufflePolicy implements ShufflePolicy {
    @Override
    public List<String> shuffle(List<String> list) {
        return Randoms.shuffle(list);
    }
}
