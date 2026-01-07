package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Crews {
    private final Course course;
    private final Mission mission;
    private final List<String> crews;
    private final Map<Boolean, UnaryOperator<List<String>>> decision;

    private Crews(Course course, Mission mission, List<String> crews) {
        this.course = course;
        this.mission = mission;
        this.crews = crews;
        this.decision = Map.of(
                true, this::evenConvert,
                false, this::oddConvert
        );
    }

    public static Crews of(Course course, List<String> crews) {
        return new Crews(course, null, crews);
    }

    public Crews withMission(Mission mission) {
        return new Crews(course, mission, crews);
    }

    public Crews shuffle(ShufflePolicy shufflePolicy) {
        List<String> shuffle = shufflePolicy.shuffle(crews);
        return new Crews(course, mission, shuffle);
    }

    public List<String> getPair() {
        return decision.get(isEven()).apply(crews);
    }

    public Course getCourse() {
        return course;
    }

    public List<String> getCrews() {
        return crews;
    }

    public Mission getMission() {
        return mission;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Crews crews1)) return false;
        return course == crews1.course && mission == crews1.mission && Objects.equals(crews, crews1.crews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, mission, crews);
    }

    private List<String> oddConvert(List<String> data) {
        List<List<String>> ret = init(data.size());
        int idx = 0;
        for (int i = 0; i < data.size() - 3; i += 2) {
            ret.get(idx).add(data.get(i));
            ret.get(idx++).add(data.get(i + 1));
        }
        List<String> subString = data.subList(data.size() - 3, data.size());
        ret.get(idx).addAll(subString);
        return toJoiner(ret);
    }

    private List<String> toJoiner(List<List<String>> data) {
        return data.stream()
                .map(x -> String.join(" : ", x))
                .toList();

    }

    private List<String> evenConvert(List<String> data) {
        List<List<String>> ret = init(data.size());
        int idx = 0;
        for (int i = 0; i < data.size(); i += 2) {
            ret.get(idx).add(data.get(i));
            ret.get(idx++).add(data.get(i + 1));
        }
        return toJoiner(ret);
    }

    private List<List<String>> init(int size) {
        List<List<String>> ret = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            ret.add(new ArrayList<>());
        }
        return ret;
    }

    private boolean isEven() {
        return crews.size() % 2 == 0;
    }
}
