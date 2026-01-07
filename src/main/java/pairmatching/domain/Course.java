package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");
    private final String type;

    Course(String type) {
        this.type = type;
    }
    public static Course findByType(String type) {
        return Arrays.stream(values())
                .filter(x -> x.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 그런 타입은 존재하지 않습니다"));
    }



}
