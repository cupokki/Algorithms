package boj.shortestpath.n1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1956
 * 운동
 * V개의 마을 E개의 도로(일방통행)
 * 1부터 V까지 번호를 가진 노드
 * 1에서 출발해 1로 돌아오는 최소크기의 사이클을 찾아라
 * *두 마을을 왕복하는 경우도 사이클에 포함이 된다!!!!!!!!!!
 */
public class Main {
    static int V;
    static int E;
    static int[][] graph;
    //    static final int INF = Integer.MAX_VALUE;
    static final int INF = 4_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());    // [2, 400]
        E = Integer.parseInt(st.nextToken());

        graph = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
//                if (i == j)
//                    graph[i][j] = 0;
//                else
                graph[i][j] = INF;
            }
        }

        for (int i = 0; i < E; i++) {
            String[] tokens = br.readLine().split(" ");
            int u = Integer.parseInt(tokens[0]);
            int v = Integer.parseInt(tokens[1]);
            int w = Integer.parseInt(tokens[2]); // 10_000
            graph[u][v] = w; //방향 그래프
        }
        int result = solve();

        System.out.println(result == INF? "-1" : result);
    }

    /**
     *
     * 어떻게 사이클을 찾을 것인가?
     * @return 가장 작은 사이클의 길이
     */
    static int solve(){
        int[][] predecessor= new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                predecessor[i][j] = graph[i][j];
            }
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (predecessor[i][k] != INF && predecessor[k][j] != INF)
                        predecessor[i][j] = Math.min(predecessor[i][j], predecessor[i][k] + predecessor[k][j]);
                }
            }
        }

        //16000개의 조합을 다 비교하나?
        int dist = INF;

//        for(int r = 1; r <= V; r++) {
//            for (int i = 1; i <= V; i++) {
//                if(predecessor[i][r] != INF && predecessor[r][i] != INF) {
//                    dist = Math.min(dist, predecessor[i][r] + predecessor[r][i]);
//                }
//            }
//        }
        // 대각선 (i -> i) 값이 갱신된 경우, 사이클 존재
        for (int i = 1; i <= V; i++) {
            if (predecessor[i][i] != INF) {
                dist = Math.min(dist, predecessor[i][i]);
            }
        }
        return dist;
    }
}
