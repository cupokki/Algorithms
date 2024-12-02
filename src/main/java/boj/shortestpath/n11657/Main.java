package boj.shortestpath.n11657;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11657
 * 타임머신
 * 한 경로에서 다른 점으로 가는 경로 중 가장 최단 경로를 찾아라
 * 간선의 가중치는 음수일 수도 있으며, 경로 중간에 사이클이 존재할 수 있다.
 * 만약 사이클이 존재한다면 -1을 반환하고, 아니라면 정점까지 최단거리를 순서대로 출력한다.
 * 시작 노드는 항상 1이다.
 * 다익스트라는 간선이 음수라면 사용 할 수 없다. -> 벨만 포드를 사용한다.
 * 벨만 포드 알고리즘은 간선 리스트가 효율적이다.
 */
public class Main {
    static List<int[]> edges = new ArrayList<>(); // 간선리스트
    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드(도시) 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수

        // 간선추가.
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new int[]{u, v, w}); //방향그래프
        }

        long[] result = bellmanFord(1); // 시작 도시 1번

        if (result == null) {
            bw.write(-1 + "\n");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                sb.append(result[i] == INF ? -1 : result[i]).append("\n");
            }
            bw.write(sb.toString());

        }
        bw.flush();
        bw.close();
        br.close();
    }

    static long[] bellmanFord(int root) {
        long[] distance = new long[N + 1]; // 노드1 까지 거리
        Arrays.fill(distance, INF);
        distance[root] = 0;

        // (사이클이 아닌)최단경로는 최대 N - 1개의 간선을 포함
        for (int i = 1; i < N; i++) { // 정점 개수 - 1 만큼 반복
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (distance[u] == INF)
                    continue;
                if (distance[u] + w < distance[v]) //간선완화
                    distance[v] = distance[u] + w;
            }
        }

        //사이클탐지
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (distance[u] == INF)
                continue;
            if (distance[u] + w < distance[v]) // 여기서 더 적어진다면 사이클이 존재하는 것
                return null;

        }
        return distance;
    }
}