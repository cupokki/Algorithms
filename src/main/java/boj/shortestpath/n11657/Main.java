package boj.shortestpath.n11657;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11657
 * 타임머신
 * N개의 도시. 한 도시에서 출발하여 다른 도시에 도착하는 버스 M개
 * A 시작도시,B 도착도시,C 버스를 타고 이동하는 시간(음수도 ok)
 * "1번 도시"에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하라
 * <p>
 * 어떤 도시로 가는 과정에서 무한히 시간을 되돌릴 수 있다면 -1만 출력?
 * 다익스트라는 최소값만 찾아가는데, 방문한 노드를갔는데 값이 더 줄었다? 그럼 -1
 */
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int M;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //그래프 선언 및 초기화
        graph = new int[N + 1][N + 1];
        for (int[] arr : graph)
            Arrays.fill(arr, INF);

        //간선(버스) 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // a
            int v = Integer.parseInt(st.nextToken()); // b
            int w = Integer.parseInt(st.nextToken()); // c
            graph[u][v] = w;
        }
        br.close();

        int[] result = dijkstra(1);

        if (result == null) {
            bw.write("-1\n");
        } else {
            for (int i = 2; i <= N; i++)
                bw.write(result[i] != INF ? result[i] + "\n" : "-1\n");
        }
        bw.flush();
        bw.close();
    }

    /**
     * @param root
     * @return 만약 루프 발생시 null출력
     */
    static int[] dijkstra(int root) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[root] = 0;
        boolean[] visited = new boolean[N + 1];
        visited[root] = true;
        pq.offer(new int[]{root, 0});

        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int u = state[0];
            int curDist = state[1];

            if (distance[u] < curDist) continue;

            for (int v = 1; v <= N; v++) {
                if(graph[u][v] == INF)
                    continue;
                //타임머신
                // 이미 방문한 적이 있는데, 현거리가 음수고, 갈 거리도 음수라면
                //
                if (visited[v] && graph[u][v] < 0 && curDist < 0){
                    return null;
                }
                if (distance[v] > graph[u][v] + curDist) {
                    distance[v] = graph[u][v] + curDist;
                    pq.offer(new int[]{v, distance[v]});
                    visited[v] = true;
                }



            }
        }

        return distance;
    }
}
