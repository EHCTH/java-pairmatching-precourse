package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Crews {
    private final DevelopType developType;
    private final Mission mission;
    private final List<Crew> crews;
    private final Map<Boolean, Supplier<List<List<String>>>> decision;


    private Crews(DevelopType developType, Mission mission, List<Crew> crews) {
        this.developType = developType;
        this.mission = mission;
        this.crews = crews;
        this.decision =  Map.of(true, this::even, false, this::odd);
    }

    public static Crews of(DevelopType developType, List<Crew> crews) {
        return new Crews(developType, Mission.EMPTY, crews);
    }

    public Crews withMission(Mission mission) {
        return new Crews(this.developType, mission, this.crews);
    }

    public DevelopType getDevelopType() {
        return developType;
    }

    public Mission getMission() {
        return mission;
    }

    public Crews shuffle(ShufflePolicy shufflePolicy) {
        List<String> nameData = crews.stream()
                .map(Crew::getName)
                .toList();
        return shufflePolicy.shuffle(nameData)
                .stream()
                .map(name -> new Crew(developType, name))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        x -> new Crews(developType, mission, x)));
    }


    public List<String> convertResponse() {
        List<List<String>> data = decision.get(isEven()).get();
        return convert(data);
    }

    private boolean isEven() {
        return crews.size() % 2 == 0;
    }
    private List<String> convert(List<List<String>> data) {
        return data.stream()
                .map(x -> String.join(" : ", x))
                .toList();
    }

    private List<List<String>> even() {
        List<List<String>> ret = new ArrayList<>();
        for (int i = 0; i < crews.size() / 2; i++) {
            ret.add(new ArrayList<>());
        }
        int idx = 0;
        for (int i = 0; i < crews.size(); i += 2) {
            ret.get(idx).add(crews.get(i).getName());
            ret.get(idx++).add(crews.get(i + 1).getName());
        }
        return ret;
    }

    private List<List<String>> odd() {
        List<List<String>> ret = new ArrayList<>();
        for (int i = 0; i < crews.size() / 2; i++) {
            ret.add(new ArrayList<>());
        }
        int idx = 0;
        for (int i = 0; i < crews.size() - 3; i += 2) {
            ret.get(idx).add(crews.get(i).getName());
            ret.get(idx++).add(crews.get(i + 1).getName());
        }
        List<String> collect = crews.
                stream()
                .map(Crew::getName)
                .toList();

        List<String> strings = collect.subList(crews.size() - 3, crews.size());
        ret.get(idx).addAll(strings);
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Crews crews1)) return false;
        return developType == crews1.developType && mission == crews1.mission && Objects.equals(crews, crews1.crews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developType, mission, crews);
    }

}
