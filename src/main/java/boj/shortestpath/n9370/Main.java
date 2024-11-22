package boj.shortestpath.n9370;

import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/9370
 * 미확인 도착지
 * 예술가 한쌍이 도시의 거리를 이동한다.
 * s에서 출발해 무조건 E(g,h)를 1번은 지나가야한다.
 * 후보 목적지 중 가능한 경로만 오름차순으로 정렬하라
 * 예술가는 반드시 최단경로로 이동한다.
 */
public class Main {
    static int N, M, T, S, G, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC = Integer.parseInt(br.readLine()); // 1~100

        StringTokenizer st;
        for (int test = 0; test < TC; test++) {
            st = new StringTokenizer(br.readLine());
            //n m t, 각각 교차로, 도로, 목적지 후보 개수
            N = Integer.parseInt(st.nextToken()); // [2,2000] // 노드
            M = Integer.parseInt(st.nextToken()); // [1,50000] // 간선
            T = Integer.parseInt(st.nextToken()); // [1-100]
            int[][] graph = new int[N + 1][N + 1];
            int[] candidates = new int[T];

            //s,g,h, 각각 예술가출발지,
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken()); //[1,s,g,h,n]
            H = Integer.parseInt(st.nextToken()); // g != h

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()); // a [1,a] (a,b) [b,n] [1,d,100]
                int v = Integer.parseInt(st.nextToken()); // b
                int w = Integer.parseInt(st.nextToken()); // d

                graph[u][v] = w;
                graph[v][u] = w;
//                graph[u][v] = w * 2;
//                graph[v][u] = w * 2;
            }
//            graph[G][H] -= 1;
//            graph[H][G] -= 1;
            for (int t = 0; t < T; t++) {
                //목적지 후보, 모두 s와 같지 않다.
                int x = Integer.parseInt(br.readLine());
                candidates[t] = x;
            }
            Arrays.sort(candidates);

            // x까지 최단거리는 두 경로 GH와 HG를 경유하는 다익스트라로 계산해서
            // 둘중 작은것이 진짜 x까지 최단 경로이다. 그리고 그 최솟값이
            // s에서 계산한 최단거리와 같다면 그 후보지는 목적지가 될 수 있다.
            int[] distanceFromS = dijkstra(graph, S);
            int[] distanceFromG = dijkstra(graph, G); // GH와 HG가 간선정렬이 없다면 결과가 같다.
            int[] distanceFromH = dijkstra(graph, H);
            int path1 = distanceFromS[G] + distanceFromG[H];// + distanceFromH[x];
            int path2 = distanceFromS[H] + distanceFromH[G];// + distanceFromG[x];

            for (int x : candidates) {
                int min = Math.min(path1 + distanceFromH[x], path2 + distanceFromG[x]);
                if (min != Integer.MAX_VALUE && min == distanceFromS[x]) {
                    bw.write(x + " ");
                }
            }

//            int[] distance = dijkstra(graph, S);
//            for (int x : candidates) {
//                if (distance[x] % 2 == 1) {
//                    bw.write(x + " ");
//                }
//            }

            bw.write("\n");
            bw.flush(); //TODO : 루프 밖으로 옮기기
        }
        bw.close();
        br.close();
    }

    static int[] dijkstra(int[][] graph, int root) {

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N + 1];
        distance[root] = 0;
        visited[root] = true;
        q.offer(new int[]{root, distance[root]});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int u = state[0];
            int curDist = state[1];
            for (int v = 1; v <= N; v++) {
                if (graph[u][v] == 0)
                    continue;
                if (visited[v])
                    continue;
                visited[v] = true;
                if (distance[v] > graph[u][v] + curDist) {
                    distance[v] = graph[u][v] + curDist;
                    q.offer(new int[]{v, distance[v]});
                }
            }
        }
        return distance;
    }
}
