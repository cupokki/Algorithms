package boj.shortestpath.n9370;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/9370
 * 미확인 도착지
 * 예술가 한쌍이 도시의 거리를 이동한다.
 * s에서 출발해 무조건 E(g,h)or E(h,g)를 1번은 지나가야한다.
 * 후보 목적지 중 가능한 경로만 오름차순으로 정렬하라
 * 예술가는 반드시 최단경로(특정 간선을 경유하여)로 이동한다. ----> 경유, 구간을 나눠야한다.
 * 더 알아보기 https://dragon-h.tistory.com/22
 *  모든 간선을 짝수로 만들고 해당 노드만 홀수로 만든다면 홀수인 x까지 최단 경로는 목적지가 될 수 있다.
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
            N = Integer.parseInt(st.nextToken()); // [2,2000] // 노드 ----> 인접행렬가능
            M = Integer.parseInt(st.nextToken()); // [1,50000] // 간선
            T = Integer.parseInt(st.nextToken()); // [1-100]
            int[][] graph = new int[N + 1][N + 1];
            int[] candidates = new int[T];

            //s,g,h, 각각 예술가출발지,
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken()); //[1,s,g,h,n]
            H = Integer.parseInt(st.nextToken()); // g != h

            //간선추가
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()); // a [1,a] (a,b) [b,n] [1,d,100]
                int v = Integer.parseInt(st.nextToken()); // b
                int w = Integer.parseInt(st.nextToken()); // d

                graph[u][v] = w;
                graph[v][u] = w;
            }
            for (int t = 0; t < T; t++) {
                //목적지 후보, 모두 s와 같지 않다.
                int x = Integer.parseInt(br.readLine());
                candidates[t] = x;
            }
            Arrays.sort(candidates);

            // GH와 HG를 경유하여 x까지가는 두 경로의 최단거리를 다익스트라로 계산해서
            // 두 경로중 작은것이 진짜 x까지 최단 경로이다.
            // 그리고 그 최솟값이 s에서 계산한 최단거리와 같다면 그 후보지는 목적지가 될 수 있다.
            // 예술가는 최단거리만이동 하므로
            int[] distanceFromS = dijkstra(graph, S);
            int[] distanceFromG = dijkstra(graph, G);
            int[] distanceFromH = dijkstra(graph, H);
            int pathGH = distanceFromS[G] + distanceFromG[H];// + distanceFromH[x_i];
            int pathHG = distanceFromS[H] + distanceFromH[G];// + distanceFromG[x_i];

            for (int x : candidates) {
                int minToX = Math.min(pathGH + distanceFromH[x], pathHG + distanceFromG[x]);
                if (minToX != Integer.MAX_VALUE && minToX == distanceFromS[x]) {
                    bw.write(x + " ");
                }
            }
            bw.write("\n");
            bw.flush(); //TODO : 루프 밖으로 옮기기
        }
        bw.close();
        br.close();
    }

    static int[] dijkstra(int[][] graph, int root) {
        Queue<int[]> q = new LinkedList<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[root] = 0;
        q.offer(new int[]{root, distance[root]});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int u = state[0];
            int curDist = state[1];
            for (int v = 1; v <= N; v++) {
                if (graph[u][v] != 0) {
                    if (distance[v] < curDist) {
                        continue;
                    }
                    if (distance[v] > graph[u][v] + curDist) {
                        distance[v] = graph[u][v] + curDist;
                        q.offer(new int[]{v, distance[v]});
                    }
                }
            }
        }
        return distance;
    }
}
