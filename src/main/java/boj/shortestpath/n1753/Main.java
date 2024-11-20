package boj.shortestpath.n1753;

import java.io.*;
import java.util.*;
/**
 * https://www.acmicpc.net/problem/1753
 * 방향그래프, 시작점에서 모든정점까지 최단 경로를 구하라. 모든 가중치는 10이하
 * Dijkstra
 * 정점의 개수 v와 간선의 대수 e 각 1부터 20,000 300,000이하
 * 1부터 V까지 번호를 가진 노드
 */
class Edge implements Comparable<Edge> {
    int node;
    int weight;

    Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {
    static int V, E;
    static List<Edge>[] graph;
    static int[] distance;
    static final int INF = Integer.MAX_VALUE;

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.node;

            // 큐에서 꺼낸 현재 노드의 거리가 이미 갱신된 거리보다 크면 무시
            if (current.weight > distance[u]) continue;

            // 인접한 노드 탐색
            for (Edge edge : graph[u]) {
                int v = edge.node;
                int w = edge.weight;

                // 더 짧은 경로를 찾았을 경우
                if (distance[v] > distance[u] + w) {
                    distance[v] = distance[u] + w; // newDist
                    pq.offer(new Edge(v, distance[v]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        // 거리 배열 초기화
        distance = new int[V + 1];
        Arrays.fill(distance, INF);

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        // 다익스트라 알고리즘 수행
        dijkstra(k);

        // 결과 출력
        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                sb.append("INF\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}
