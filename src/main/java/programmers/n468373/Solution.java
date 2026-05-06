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

    그리디나, 그래프같은데..
    일단 문제에 사이클은 없는것 같고,

    아 k최댓값 k인데... 이걸로 가능한가.. dfs
    */
    static int[][] graph;
    static int N, K;
    static int max;
    static int Infection;
    public static int solution(int n, int infection, int[][] edges, int k) {
        N = n;
        K = k;
        graph = new int[n + 1][n + 1];
        result = new int[k];
        Infection = infection;
        max = 1; // 최솟값.
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][0];
            int type = edges[i][0]; // 1, 2, 3은 각각 A, B, C를 의미함
            // graph[u][v] = graph[v][u] = type; // 사이클 없음, 무방향 처리가능?
            graph[u][v] = type;

        }

        dfs(0);

        return max;
    }
    static int[] result;
    static void dfs(int depth) {
        if (depth == K) {
            Math.max(max, solve());
            return;
        }

        for (int type = 1; type <= 3; type++) {
            result[depth] = type; // 같은걸 연속해서 뽑는 경우를 제거해야한다.
            dfs(depth + 1);
        }
    }

    static int solve() {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> infected = new HashSet<>();
        infected.add(Infection);
        for (int type: result) {
            while(!q.isEmpty()) {
                int u = q.poll();
                for (int v = 0; v < N; v++) {
                    if (graph[u][v] == type) {
                        if (infected.contains(v)) { // 단방향 및 사이클 없으므로 재방문 없음.
                            infected.add(v);
                        }
                        q.offer(v);
                    }
                }
            }
        }
        return infected.size();
    }



     public static void main(String[] args) {
         System.out.println(solution(10, 1, new int[][]{{1, 2, 1}, {1, 3, 1}, {1, 4, 3}, {1, 5, 2}, {5, 6, 1}, {5, 7, 1}, {2, 8, 3}, {2, 9, 2}, {9, 10, 1}}, 2));
         System.out.println(solution(7, 6, new int[][]{{1, 2, 3}, {1, 4, 3}, {4, 5, 1}, {5, 6, 1}, {3, 6, 2}, {3, 7, 2}}, 3));
     }
}