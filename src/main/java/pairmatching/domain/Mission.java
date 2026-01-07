package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Mission {
    자동차경주("1", "자동차경주"),
    로또("1","로또"),
    숫자야구게임("1","숫자야구게임"),


    장바구니("2","장바구니"),
    결제("2","결제"),
    지하철노선도("2","지하철노선도"),


    성능개선("4","성능개선"),
    배포("4","배포"),
    EMPTY(null, null);


    private final String level;
    private final String mission;
    private static final Map<String, List<Mission>> GROUPING_BY_MISSION =
            Arrays.stream(values()).collect(Collectors.groupingBy(
                            Mission::getLevel,
                            Collectors.toList()
                    )
            );


    Mission(String level, String mission) {
        this.level = "레벨" + level;
        this.mission = mission;
    }
    public static Mission findByMission(String level, String mission) {
        List<Mission> missions = findByLevel(level);
        return missions.stream()
                .filter(x -> x.mission.equals(mission))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 미션은 존재하지 않습니다"));
    }

    public String getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
    private static List<Mission> findByLevel(String level) {
        if (!GROUPING_BY_MISSION.containsKey(level)) {
            throw new IllegalArgumentException("[ERROR] 해당하는 레벨은 존재하지 않습니다");
        }
        return GROUPING_BY_MISSION.get(level);
    }
}
