package pairmatching.interfaces.adapter.inbound;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);
    public MenuOptionType menuOptionType() {
        System.out.println("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료");
        String data = sc.nextLine();
        return MenuOptionType.findByCode(data);
    }
    public String promptAsk() {
        System.out.println();
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
        return getReadLine();
    }

    public List<String> retryAsk() {
        System.out.println();
        System.out.println("과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");
        String data = sc.nextLine();
        return Arrays.stream(data.split(","))
                .map(String::trim)
                .toList();
    }
    public String retryAskRetry() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
        return sc.nextLine();
    }
    public List<String> promptData() {
        System.out.println();
        System.out.println("#############################################\n" +
                "과정: 백엔드 | 프론트엔드\n" +
                "미션:\n" +
                "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
                "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
                "  - 레벨3: \n" +
                "  - 레벨4: 성능개선 | 배포\n" +
                "  - 레벨5: \n" +
                "############################################\n" +
                "과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");

        String data = sc.nextLine();
        return Arrays.stream(data.split(","))
                .map(String::trim)
                .toList();
    }

    private static String getReadLine() {
        return Console.readLine();
    }

}
