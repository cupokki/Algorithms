package boj.shortestpath.n1503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1504
 * 특정한 최단 경로
 * 무방향 그래프. 1번정점에서 n번 정점으로 최단 거리
 * 조건 1) 주어진 두 정점을 반드시 경유 (두 정점은 최대 1개의 간선이 존재한다.)
 * 조건 2) 방문한 간선을 다시 방문해도 된다.
 */

public class Main {
    static class Edge {
        int node;
        int weight;

        public Edge(int value, int weight) {
            this.node = value;
            this.weight = weight;
        }
    }

    static final int INF = -1;
    static int N; // 2 <= N <= 800
    static int E; // 0 < E <= 200_000
    static int[][] graph;
    static int v1; // v1 != v2, v1 != N
    static int v2; // v2 != 1

    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        for (int[] arr : graph) {
            Arrays.fill(arr, INF);
        }
        distance = new int[N + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = w;
            graph[v][u] = w;
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int result = solve();
        System.out.println(result);
    }

    static int solve() {
        dijkstra(1);
        int path1 = 0;
        int path2 = 0;
        path1 += distance[v1];
        path2 += distance[v2];
        // 모두 루트에서 도달 할 수 있어야한다.
        if (distance[v1] == INF || distance[v2] == INF)
            return -1;

        // 1 -> v1 -> v2 -> N
        dijkstra(v1);
        path1 += distance[v2];
        dijkstra(v2);
        path1 += distance[N];

        // 1 -> v2 -> v1 -> N
        dijkstra(v2);
        path2 += distance[v1];
        dijkstra(v1);
        path2 += distance[N];

        return Math.min(path1, path2);
    }


    static void dijkstra(int root) {
        //목표 노드와 함께  우선순위 큐
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); // 거리 기준 정렬
        Arrays.fill(distance, INF);
        distance[root] = 0;
        que.offer(new int[]{root, 0});

        while (!que.isEmpty()) {
            int[] edge = que.poll();
            int u = edge[0];
            int curDist = edge[1];

            //prunning
            //노드의 거리 curDist가 이미 기록된 최단 거리 distance[u]보다 크다면 말이 안됨
            if (curDist > distance[u])
                continue;

            for (int v = 1; v <= N; v++) {
                if (graph[u][v] != INF) {
                    int newDist = distance[u] + graph[u][v];
                    if (distance[v] == INF || distance[v] > newDist) {
                        distance[v] = newDist;
                        que.offer(new int[]{v, newDist});
                    }
                }
            }
        }
//        for (int i = 1; i <= N; i++) {
//            System.out.print(distance[i] + " ");
//        }
//        System.out.println();

    }
}
