package pairmatching;

import pairmatching.bootstrap.AppConfig;
import pairmatching.interfaces.adapter.inbound.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private static Map<String, String> store = new HashMap<>();
    private static Map<String, List<String>> store2 = new HashMap<>();

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        Controller controller = appConfig.controller();
//        controller.run();2

        String s = store.putIfAbsent("x", "123123");
        System.out.println(s);
        System.out.println(store.get("x"));

        store2.computeIfAbsent("hello", (x) -> new ArrayList<>()).add("banana");
        store2.computeIfAbsent("hello", (x) -> new ArrayList<>()).add("apple");

        System.out.println(store2);
        List<String> strings = store2.computeIfPresent("hello", (a, b) -> {
            b.add("orange");
            return b;
        });
        System.out.println(strings);

    }
}
/*
        Map<String, List<Integer>> groups = new HashMap<>();

        // 키가 없으면 아무 일도 안 함
        groups.computeIfPresent("A", (k, v) -> { v.add(1); return v; });
        System.out.println(groups); // {}

        // 먼저 넣어두고
        groups.put("A", new ArrayList<>());

        // 키가 있으면 실행됨: 같은 리스트에 누적
        groups.computeIfPresent("A", (k, v) -> { v.add(1); return v; });
        groups.computeIfPresent("A", (k, v) -> { v.add(2); return v; });

        System.out.println(groups); // {A=[1, 2]}

        // null 반환하면 키 삭제
        groups.computeIfPresent("A", (k, v) -> null);
        System.out.println(groups); // {}


computeIfAbsent
if (!map.containsKey(key)) {
    map.put(new ArrayList()<>);
}
map.get(key).add(value);

computeIfPresent
if (map.containsKey(key)) {
   map.put(key).add(value);
}
 */
