package pairmatching.interfaces.adapter.inbound;

import pairmatching.domain.Course;
import pairmatching.domain.Mission;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);
    public String promptMenu() {
        System.out.println("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료");
        return sc.nextLine();
    }
    public Query promptQueryFirst() {
        System.out.println("과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");
        return parse(sc.nextLine());
    }

    public Query promptQuery() {
        System.out.println("\n과정, 레벨, 미션을 선택하세요.\n" +
                "ex) 백엔드, 레벨1, 자동차경주");
        return parse(sc.nextLine());
    }

    public UserStatus promptStatusFirst() {
        System.out.println("\n매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" +
                "네 | 아니오");
        return UserStatus.findByCode(sc.nextLine());
    }

    public UserStatus promptStatus() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" +
                "네 | 아니오");
        return UserStatus.findByCode(sc.nextLine());
    }

    private Query parse(String data) {
        List<String> list = Arrays.stream(data.split(","))
                .map(String::trim)
                .toList();
        Course course = Course.findByType(list.getFirst());
        Mission mission = Mission.findByLevelAndName(list.get(1), list.getLast());
        return new Query(course, mission);

    }
}
