package pairmatching.interfaces.adapter.inbound;

import java.util.List;

public class OutputView {

    public void display(List<String> data) {
        System.out.println();
        System.out.println("페어 매칭 결과입니다.");
        for (String ret : data) {
            System.out.println(ret);
        }
        System.out.println();
    }
    public void displayInit() {
        System.out.println();
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }

}
