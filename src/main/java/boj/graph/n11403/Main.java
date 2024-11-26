package boj.graph.n11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11403
 * 경로 찾기
 * i에서 j로 가는 경로가 존재하는지 확인
 * bfs를 N번 실행해야함
 * O((V+E)*V)
 * TODO: 플로이드 와셜 적용하기
 */
public class Main {
    static boolean[][] graph;
    static int N;
    static boolean[] visited;
    static boolean[][] connected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N + 1][N + 1];
        connected = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = st.nextToken().equals("1");
            }
        }


        for (int i = 1; i <= N; i++){
            bfs(i);
        }
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++){
                System.out.print(connected[i][j]? "1 ": "0 ");
            }
            System.out.println();
        }

    }
    static void bfs(int root){
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        Arrays.fill(visited, false);

//        visited[root] = true;

        while(!q.isEmpty()){
            int u = q.poll();
            for(int v = 1; v <= N; v++){
                if(!visited[v] && graph[u][v]){
                    visited[v]= true;
                    q.offer(v);
                    connected[root][v] = true;
                }
            }
        }

    }
}
