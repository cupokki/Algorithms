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
class Edge implements Comparable<Edge>{
    int node;
    int weight;
    Edge(int node, int weight){
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight); // 오버플로우 언더플로우 발생가능성
    }
}
public class Main {
    static int V;
    static int E;
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static int[] distance;
    static final int INF = Integer.MAX_VALUE;
    static void dijkstra(int root){
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(root, 0));
        while(!queue.isEmpty()){
            Edge edge = queue.poll();
            int u = edge.node;
            if(distance[u] < edge.weight) // prunning 우선 순위큐때문
                continue;
            for(Edge e : graph.getOrDefault(u, new ArrayList<>())){
                int v = e.node;
                int w = e.weight;
                int newDist = distance[u] + w;
                if( distance[v] > newDist){
                    distance[v] = newDist;
                    queue.offer(new Edge(v, newDist)); ///////////////////문제를 다시 이해 할 필요가 있음
                }

            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        distance = new int[V + 1];
        Arrays.fill(distance, INF);
        distance[k] = 0;
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            (graph.computeIfAbsent(u, key -> new ArrayList<>())).add(new Edge(v, w));
//            (graph.putIfAbsent(v, new ArrayList<>())).add(new Edge(u, w)); // 방향그래프임
        }
        br.close();
        dijkstra(k);

        for(int i = 1; i <= V; i++) {
            bw.write((distance[i] == INF?"INF":distance[i] + "\n"));
        }
        bw.flush();
        bw.close();
    }

}
