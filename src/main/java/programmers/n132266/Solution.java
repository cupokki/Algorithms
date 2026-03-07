package programmers.n132266;

import java.util.*;

public class Solution {
    /*
    - 지도에 부대가 위치한 지역을 포함한 각 지역은 유일한 번호로 구분
    - 두 지역을 이동하는데 필요한 시간은 1
    - 최단시간 부대로 복귀한다.
    - n: 지역의 수
    - roads: 지역 연결정보 (왕복)
    - source: 각 부대의 위치
    - destination: 목적지
    - 복귀불가 -1;
    - 최단시간으로 복귀하는 시간을 배열로

    각 부대별로 bfs? 하지만 roads최대가 50만
    모든 부대의 목적지가 단 하나이므로, 한 점에서 모든점까지 최단거리, 다익스트라 사용?
    근데 모든 거리의 가중치가 1인데? 굳이 안해도 되었을 듯
    */
    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};

//        boolean[][] adj = new boolean[n][n];

        List<Integer>[] adj = new List[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < roads.length; i++) {
            int u = roads[i][0] - 1; // 0-based index
            int v = roads[i][1] - 1; // 0-based index
//            adj[u][v] = adj[v][u] = true;

            adj[u].add(v);
            adj[v].add(u);
        }

        int[] min = new int[n];
        Arrays.fill(min, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[n];
        visited[destination - 1] = true;
        min[destination - 1] = 0;
        pq.offer(new int[]{destination - 1, 0}); // next, dist
        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int cur = state[0];
            int dist = state[1];

//            for (int next = 0; next < n; next++) {
//                if (adj[cur][next] && !visited[next]) {
            for (int next : adj[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    pq.offer(new int[]{next, dist + 1});
                    if (min[next] == -1) {
                        min[next] = dist + 1;
                    } else {
                        min[next] = Math.min(min[next], min[cur] + 1);
                    }
                }
            }
        }
        answer = Arrays.stream(sources).map(
                i -> min[i - 1]
                ).toArray();
        return answer;
    }

    public static void main(String[] args) {
        Arrays.stream(solution(
                3,
                new int[][] {{1, 2}, {2, 3}},
                new int[] {2, 3},
                1
        )).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(solution(
                5,
                new int[][] {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}},
                new int[] {1, 3, 5},
                5
        )).forEach(i -> System.out.print(i + " "));
        System.out.println();

    }
}