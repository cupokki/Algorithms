package programmers.n118668;

import java.util.*;
import static java.lang.Math.min;

public class Solution {
    /*
    알고력, 코딩력은 정수로 표현
    문제를 풀기위해 두 능력이 요구치 이상으로 필요하다.

    공부 (cost = 1) : 알고력과 코딩력을 기를 수 있다.
    문제풀기 (cost = problem[5]) 알고력과 코딩력이 정해진 수치만큼 오른다.
        problems[i] : [alp_req, cop_req, alp_rwd, cop_rwd, cost];

    모든 문제를 풀수 있는 최단 시간을 출력하라.
    * 한 문제를 여러번 풀 수 있다.
    * 모든 문제를 1번 이상 풀 필요는 없다.

    어려운순으로 오름차순 정리?

    다익스트라?
    우선 순위큐로 최초에 -1로 초기화 된 최단거리배열 갱신해가면서, 완성된 최단거리 배열 i번 인덱스가 결괏값이다.
    다익스트라는 bfs랑 비슷하나 우선순위큐를 사용한다는점, 갱신규칙이 다르다는 점이 다르다.




    /
     */
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        var comp = Comparator.comparing((int[] a) -> a[0]) // algReq
                .thenComparing(a -> a[1]) // copReq
                .thenComparing(a -> a[2]) // algRwd
                .thenComparing(a -> a[3]) // copRwd
                .thenComparing(a -> a[4]); // cost)
                // a[5] : idx;

        PriorityQueue<int[]> pq = new PriorityQueue<>(comp);
//
//        for (int i = 0; i < problems[0].length; i++) {
//            pq.offer(new int[]{problems[i][0], problems[i][1], problems[i][2], problems[i][3],  problems[i][4], problems[i][5], i});
//        }

        int maxAlp = 0;
        int maxCop = 0;

        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }


        int[][] dist = new int[maxAlp][maxCop];
        for (int i = 0; i < dist.length; i++) Arrays.fill(dist[i], -1); // -1 = INF

        pq.offer(new int[]{alp, cop, 0});
        dist[alp][cop] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.peek();

            // alp 공부

            // cop 공부

            // 문제 풀기
            for (;;) {}
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}})); // 15
        System.out.println(sol.solution(0, 0, new int[][]{{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}})); // 13
    }
}
