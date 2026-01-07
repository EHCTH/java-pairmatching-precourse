package pairmatching.interfaces.adapter.inbound;

import java.util.Arrays;

public enum MenuOptionType {
    MATCH("1"),
    SEARCH("2"),
    CLEAR("3"),
    QUIT("Q") {
        @Override
        public boolean isRetry() {
            return false;
        }
    };
    private final String code;

    MenuOptionType(String code) {
        this.code = code;
    }
    public static MenuOptionType findByCode(String code) {
        return Arrays.stream(values())
                .filter(x -> x.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 옵션은 존재하지 않습니다"));
    }
    public boolean isRetry() {
        return true;
    }
}
