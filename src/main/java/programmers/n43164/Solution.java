package programmers.n43164;

import java.sql.Array;
import java.util.*;

public class Solution {
    /*
    주어진 항공권을 모두 이용하는 여행 경로를 짠다. (모두 방문가능한 경우만 주어진다.)
    ICN에서 항상 출발
    공항의 수는 3이상 1만이하
    항공권 모두사용 (단 방향)
    경로가 2개 이상일 경우 알파벳 순으로 앞서는 경로

    사이클이 있나? 예제 2를 보면 사이클이 있다.

    지금 bfs는 안된다.
    우선 순위가 무조건 사전순부터라 경로를 만들 수 있음에도 조기에 종료된다.

    백트래킹으로 단 하나의 경로를 찾는다?
     */
    static List<String> list;
    static Map<String, PriorityQueue<String>> graph;
    public static String[] solution(String[][] tickets) {
        String[] answer;
        list = new ArrayList<>();
        graph = new HashMap<>();
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(
                    ticket[0],
                    k -> new PriorityQueue<>()
                    ).add(ticket[1]);
            graph.computeIfAbsent(
                    ticket[1],
                    k -> new PriorityQueue<>()
            );
        }
        dfs("ICN");
        Collections.reverse(list);
        answer = list.toArray(String[]::new);
        return answer;
    }

    static void dfs(String cur) {
        var pq = graph.get(cur);
//        if (pq.isEmpty()) {
//            list.add(cur);
//            return;
//        }

        while (!pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }

        list.add(cur);
    }

    public static void main(String[] args) {
        Arrays.stream(solution(new String[][]{
                {"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}
        })).forEach((i) -> System.out.print(i + " "));
        System.out.println();

        Arrays.stream(solution(new String[][]{
                {"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}
        })).forEach((i) -> System.out.print(i + " "));
        System.out.println();
    }

}
