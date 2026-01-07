package pairmatching.infrastructure;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.ShufflePolicy;

import java.util.ArrayList;
import java.util.List;

public class RandomShufflePolicy implements ShufflePolicy {
    @Override
    public List<String> shuffle(List<String> data) {
        List<String> list = new ArrayList<>(data);
        return Randoms.shuffle(list);
    }
}
