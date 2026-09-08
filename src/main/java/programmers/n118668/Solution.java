package programmers.n118668;

import java.util.*;

public class Solution {
    /*
    알고력, 코딩력은 정수로 표현
    문제를 풀기위해 두 능력이 요구치 이상으로 필요하다.

    1의 시간을 소모해 각각을 공부 할 수 있다.
    문제를 풀면 알고력과 코딩력이 정해진 수치만큼 오은다.
    문제마다 풀이시간이 존재한다.
    같은 문제를 여러번 풀 수 있다.
    -> [alp_req, cop_req, alp_rwd, cop_rwd, cost];

    모든 문제를 푸는 최단 시간을 출력하라.

    어려운순으로 오름차순 정리?

    1. 문제가 해결할 수 있는게 있다면 해결한다.
    2. 해결 할 수 없으면, 해결 할 수 있을 만큼 공부한다.?

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

        for (int i = 0; i < problems[0].length; i++) {
            pq.offer(new int[]{problems[i][0], problems[i][1], problems[i][2], problems[i][3],  problems[i][4], problems[i][5], i});
        }

        int[] dist = new int[problems[0].length];

        Arrays.fill(dist, -1); // -1 = INF

        while (!pq.isEmpty()) {
            int[] cur = pq.peek();
            int idx = cur[5];
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}})); // 15
        System.out.println(sol.solution(0, 0, new int[][]{{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}})); // 13
    }
}
