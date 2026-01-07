package pairmatching.interfaces.adapter.inbound;

import java.util.Arrays;

public enum UserStatus {
    YES("네") {
        @Override
        public boolean isNo() {
            return false;
        }
    },
    NO("아니오");
    private final String code;

    UserStatus(String code) {
        this.code = code;
    }
    public static UserStatus findByCode(String code) {
        return Arrays.stream(values())
                .filter(x -> x.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 그런 코드는 존재하지 않습니다"));
    }
    public boolean isNo() {
        return true;
    }

}
