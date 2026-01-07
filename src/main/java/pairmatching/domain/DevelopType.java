package pairmatching.domain;

import java.util.Arrays;

public enum DevelopType {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");
    private final String display;

    DevelopType(String display) {
        this.display = display;
    }
    public static DevelopType findByCode(String code) {
        return Arrays.stream(values())
                .filter(x -> x.display.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 데이터는 존재하지 않습니다"));
    }
}
