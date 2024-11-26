package boj.graph.n11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11724
 * 연결 요소의 개수
 * 무방향 그래프에서 정점과 간석개수 N,M이 주어지고
 * 간선이 주어질대 연결요소의 개수를 출력하라
 */
public class Main {
    static boolean[][] graph;
    static boolean[] visited;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //1000
        M = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u][v] = true;
            graph[v][u] = true;
        }
        int count = 0;
        for(int i = 1; i <= N;i++) {
            if(!visited[i]){
                bfs(i);
                count++;
            }
        }
        System.out.println(count);
    }
    static void bfs(int root) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        visited[root] = true;
        while(!q.isEmpty()) {
            int u = q.poll();
            for(int v = 1; v <= N; v++){
                if(!visited[v] && graph[u][v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
    }

}

