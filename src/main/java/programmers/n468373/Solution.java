package programmers.n468373;

import java.util.*;

public class Solution {
    /*
    1번부터 n번까지 n개의 배양체를 n-1개의 파이프로 이어 하나의 트리모양을 만들었다.
    각 파이프는 A,B,C 3종. 초기에 모든 파이프는 닫힘

    하나의 감염된 배양체. 열린 파이프를 통해 인접배양체 감염
    한 종의 같은 파이프는 모두 여닫을 수 있음.
    단, 한 종의 파이프를 연 후, 다시 닫기 전까지 다른 파이프를 열 수 없음
    여닫는 행동을 최대 k번 반복해, 최대한 많이 감염시키고자한다.
    감염체 최댓값을 구하라.

    파이프 = 간선
    노드는 최대 100개, 최소 두개
    */
    static int[][] graph;
    static int N, K;
    static int max;
    static int Infection;
    static int[] result;
    public static int solution(int n, int infection, int[][] edges, int k) {
        N = n;
        K = k;
        graph = new int[n + 1][n + 1];
        Infection = infection;
        max = 1; // 최솟값.
        result = new int[k];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int type = edges[i][2]; // 1, 2, 3은 각각 A, B, C를 의미함
             graph[u][v] = graph[v][u] = type;

        }

        List<Integer> init = new ArrayList<>();
        init.add(infection);
        dfs(0);

        return max;
    }
    static void dfs(int depth) {
        if (depth == K) {
            max = Math.max(max, bfs()); // solve는 bfs
            return;
        }
        for (int type = 1; type <= 3; type++) {
            result[depth] = type;
            dfs(depth + 1);
        }
    }
    static int bfs() {
        int cnt = 0;

        boolean[] infected = new boolean[N + 1];
        infected[Infection] = true;

        for (int type : result) {
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (infected[i]) q.offer(i);
            }
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v = 1; v <= N; v++) {
                    if (!infected[v] && graph[u][v] == type) {
                        infected[v] = true;
                        q.offer(v);
                    }
                }
            }
        }

        for (int i = 1 ; i <= N; i++) {
            if (infected[i]) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
         System.out.println(solution(10, 1, new int[][]{{1, 2, 1}, {1, 3, 1}, {1, 4, 3}, {1, 5, 2}, {5, 6, 1}, {5, 7, 1}, {2, 8, 3}, {2, 9, 2}, {9, 10, 1}}, 2));
         System.out.println(solution(7, 6, new int[][]{{1, 2, 3}, {1, 4, 3}, {4, 5, 1}, {5, 6, 1}, {3, 6, 2}, {3, 7, 2}}, 3));
    }
}