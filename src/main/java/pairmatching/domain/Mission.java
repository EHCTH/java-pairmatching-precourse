package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    자동차경주(1, "자동차경주"),
    로또(1, "로또"),
    숫자야구게임(1, "숫자야구게임"),

    장바구니(2, "장바구니"),
    결제(2, "결제"),
    지하철노선도(2, "지하철노선도"),

    성능개선(4, "성능개선"),
    배포(4, "배포");
    private final String level;
    private final String name;

    Mission(int level, String name) {
        this.level = "레벨" + level;
        this.name = name;
    }

    public static Mission findByLevelAndName(String level, String name) {
        return Arrays.stream(values())
                .filter(x -> x.level.equals(level) && x.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 그런 미션은 존재하지 않습니다"));
    }


    /*
    ## 레벨
- 레벨1
- 레벨2
- 레벨3
- 레벨4
- 레벨5

## 미션
### 레벨1
자동차경주
로또
숫자야구게임
장바구니
결제
지하철노선도

### 레벨3(없음)

### 레벨4
- 성능개선
- 배포
     */
}
