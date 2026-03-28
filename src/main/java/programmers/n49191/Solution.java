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
    방향 그래프로 연결하고, 말단 노드부터 검사?



    */
    public static int solution(int n, int[][] results) {
        int answer = 0;

        int m = results.length;
        boolean[][] graph = new boolean[n + 1][n + 1];

        for (int[] edge : results) {
            graph[edge[1]][edge[0]] = true;
        }

        for (int i = 1; i <= n; i++) {
            if (bfs(graph, n, i) == n - 1) { // 현 노드를 기점으로 모든 간선을 사용한다면, 순위가 확정?
                answer++;
            }
        }


        return answer;
    }
    static int bfs(boolean[][] graph, int n, int s) {
        boolean[] visited = new boolean[n + 1];
        visited[s] = true;
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next = 1; next <= n; next++) {
                if (!visited[next] && graph[cur][next]) {
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