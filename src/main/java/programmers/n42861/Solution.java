package programmers.n42861;

import java.util.Comparator;
import java.util.PriorityQueue;

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

        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0}); // 노드 0, 거리 0
        int nodeCnt = 0;

        // 모든 노드를 순회하며, 가장 작은 간선을 선택한다.
        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int cur = state[0];
            int dist = state[1];

            if (visited[cur])  continue;
            visited[cur] = true;
            answer += dist;
//            nodeCnt++;
//            if (nodeCnt == n) break;

            for (int next = 0; next < n; next++) {
                int w = adjMatrix[cur][next];
                if(w != 0 && !visited[next]) {
                    pq.offer(new int[]{next, w});
                }
            }
        }

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
