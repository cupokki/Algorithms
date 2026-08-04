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

    우선순위 큐 정렬기준을 어떡하는가.
        - 요구 알고력과 코딩력은 우선순위를 매기면 답에 문제가 생기지 않는가.
        - 가령 현재 [0, 3]이고, 우선순위에 [1, 3, 0, 0], [1, 4, 5, 5], [6, 7, 5, 5]인 문제가 있다면
          알고력을 공부하는게 다음 문제를 푸는데 이득인지만,
            코딩력을 공부해서 바로 문제를 풀수있지만,

    /
     */
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator
                .comparing((int[] a) -> a[0]) // algReq
                .thenComparing(a -> a[1]) // copReq
                .thenComparing(a -> a[2]) // algRwd
                .thenComparing(a -> a[3]) // copRwd
                .thenComparing(a -> a[4]) // cost);
        );

        for (int[] problem : problems) pq.offer(problem);

        while (!pq.isEmpty()) {
            int[] cur = pq.peek();
            if (alp >= cur[0] && cop >= cur[1]) {
                alp += cur[2];
                cop += cur[3];
                answer += cur[4];
                pq.poll();
            } else if (alp < cur[0]) {
                alp++;
                answer++;
                alp++;
            } else {
                cop++;
                answer++;
                cop++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}})); // 15
        System.out.println(sol.solution(0, 0, new int[][]{{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}})); // 13
    }
}
