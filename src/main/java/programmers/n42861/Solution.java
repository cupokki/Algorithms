package programmers.n42861;

import java.util.Arrays;

public class Solution {
    /*
    섬 사이 다리 건설비용 costs 가 주어질때 최소비용으로 모든 섬이 통행되게한다.

    섬은 100개 이하 자연수
    costs[i] : {u, v, w} 양방향 그래프
    모든 섬은 연결되어있고, 중복된 입력은 없음

    건설비용이 음수라는 이야기는 또 없네.. 이러면 곤란한데

    한 노드에서 모든 노드를 거치는 최단 경로
    최소신장트리
     */
    public static int solution(int n, int[][] costs) {

        int answer = 0;
        int[][] adjMatrix = new int[n][n];



        for (int i = 0; i < costs.length; i++) {
            int u = costs[i][0];
            int v = costs[i][1];
            int w = costs[i][2];
            adjMatrix[u][v] = adjMatrix[v][u] = w;
            // adjMatrix[u][v] = w;

        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        // 모든 노드를 순회하며, 가장 작은 간선을 선택한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (adjMatrix[i][j] == 0) continue;
                dist[j] = Math.min(dist[j], adjMatrix[i][j]);
            }
        }

        answer = Arrays.stream(dist).sum();

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[][]{
                {0, 1, 1},
                {0, 2, 2},
                {1, 2, 5},
                {1, 3, 1},
                {2, 3, 8}
        }));
    }
}
