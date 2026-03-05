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
     */
    public static String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];

        Map<String, PriorityQueue<String>> graph = new HashMap<>();
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

        Queue<String> q = new LinkedList<>();
        Set<String> usedEdge = new HashSet<>();
        q.offer("ICN");
        answer[0] = "ICN";
        int idx = 1;
        while (!q.isEmpty()) {
            String cur = q.poll();

            if(graph.get(cur).size() == 0) break;
            String next = graph.get(cur).poll();
            q.offer(next);
            answer[idx++] = next;
        }
        return answer;
    }

    public static void main(String[] args) {
//        Arrays.stream(solution(new String[][]{
//                {"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}
//        })).forEach((i) -> System.out.print(i + " "));
//        System.out.println();

        Arrays.stream(solution(new String[][]{
                {"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}
        })).forEach((i) -> System.out.print(i + " "));
        System.out.println();
    }

}
