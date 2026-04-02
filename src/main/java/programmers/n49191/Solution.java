package programmers.n49191;

import java.util.*;

public class Solution {
    /*
    n명의 차례로 번호가 매겨진 선수,
    result는 (a, b) 쌍, a번 선수가가 b번 선수를 항상 이겼다는 의미
    모순된 입력은 없음

    선수는 100명 이하
    경기수 n는 4500이하

    일부 경기기록이 분실된 results가 주어질때, 순위가 확실한 사람의 수를 출력

    노드 i가 이긴 수와, 진 수를 합하여 n - 1이면 순위가 확정인 노드이다.
    */
    public static int solution(int n, int[][] results) {
        int answer = 0;

//        int m = results.length;
//        int[][] graph = new int[n + 1][n + 1];
//
//        for (int[] edge : results) {
//            graph[edge[0]][edge[1]] = 1; // a는 b를 이김
//            graph[edge[1]][edge[0]] = -1; // b는 a에게 짐
//        }
//
//        for (int i = 1; i <= n; i++) {
//
//            boolean[] visited = new boolean[n + 1];
//            visited[i] = true;
//            int winCnt = bfs(graph, visited, n, i, -1); // i가 진 것(상행)
//            int loseCnt = bfs(graph, visited, n, i, 1); // i가 이긴 것(하행)
//
//            if (winCnt + loseCnt == n - 1) {
//                answer++;
//            }
//        }

        int[][] graph = new int[n + 1][n + 1];
        for (int[] edge : results) {
            graph[edge[0]][edge[1]] = 1;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1; // i가 k를 이기고 k가 j에게 이기면 -> i는 j를 이기는 것이 성립
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == 1 || graph[j][i] == 1) cnt++; // 이기거나 진 사실이 확실하면
            }
            if (cnt == n - 1) answer++;
        }

        return answer;
    }
    static int bfs(int[][] graph, boolean[] visited, int n, int s, int checker) {
        visited[s] = true;
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next = 1; next <= n; next++) {
                if (!visited[next] && graph[cur][next] == checker) {
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }
        return cnt; // 방문한 노드 = 사용한 간선
    }

    public static void main(String[] args) {

        System.out.println(solution(5, new int[][] {{4, 3}, {4, 2}, {3,2}, {1, 2}, {1, 2}, {2, 5}}));
    }
}