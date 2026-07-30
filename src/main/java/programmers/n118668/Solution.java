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
     */
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

//        Arrays.sort(problems, Comparator
//                .comparing((int[] a) -> a[0]) // algReq
//                .thenComparing(a -> a[1]) // copReq
//                .thenComparing(a -> a[2]) // algRwd
//                .thenComparing(a -> a[3]) // copRwd
//                .thenComparing(a -> a[4]) // cost
//        );

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

    }
}
