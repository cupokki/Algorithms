package programmers.n12978;

import java.security.interfaces.EdECKey;
import java.util.*;

public class Solution {
    /*
    N개의 마을 나라 각마을은 번호가 있고 양방향 가중그래프
    1번 마을에서 출발하여 K시간 이하로 배달 가능한 마을에서만 주문을 받는다.
    가능한 주문의 수를 반환하라.

    road는 u,v,w 배열

    두 노드간 간선은 유일하지않다. -> 인접행렬 불가
     */
    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
//        int[][] graph = new int[N + 1][N + 1];
        HashMap<Integer, List<int[]>> graph = new HashMap();
        for (int[] r : road) {
//            graph[r[0]][r[1]] = graph[r[1]][r[0]] = r[2];
            graph.computeIfAbsent(r[0], k -> new ArrayList<>())
                    .add(new int[]{r[1], r[2]});
            graph.computeIfAbsent(r[1], k -> new ArrayList<>())
                    .add(new int[]{r[0], r[2]});
        }


        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(new int[]{1, 0});
        visited[1] = true;
        answer ++;

        while(!q.isEmpty()) {
            int[] state = q.poll();
            int u = state[0];
            int dist = state[1];
//            for (int next = 1; next <= N; next++) {
//                if (!visited[next] && graph[cur][next] > 0) { // 가중치가 0인 케이스는 주어지지않음
//                    q.offer(new int[]{next, w + graph[cur][next]});
//                    visited[next] = true;
//                }

            for (int[] next : graph.get(u)) {
                int v = next[0];
                int w = dist + next[1];
                if(!visited[next[0]] && w <= K) {
                    q.offer(new int[]{v, w});
                    visited[v] = true;
                    answer++;
                }
            }
        }
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        int[][] road1 = {
                {1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}
        };
        solution(5, road1, 3);

        int[][] road2 = {
                {1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}
        };
        solution(6, road2, 4);
    }
}
