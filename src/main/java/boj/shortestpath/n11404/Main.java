package boj.shortestpath.n11404;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11404
 * 플로이드
 * 2개이상 100개이하의 노드, 최대 10000개의 간선, 가중치는 100,000이하.
 * 시작도시와 도착시는 같지 않다
 */
public class Main {
    static int N;
    static int M;
    private static int[][] graph;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];

        // 그래프 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i == j) {
                    graph[i][j] = 0;
                }else {
                    graph[i][j] = INF;
                }
            }
        }
        for (int i = 1; i <= M; i++) {
            StringTokenizer st=  new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (graph[u][v] > w) { // 같은 경로의 간선이 존재할 수 있음 그 경우 작은 간선을 포함하게 한다.
                graph[u][v] = w;
            }
        }

        int[][] result = floydwharshall();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(result[i][j] == INF)
                    bw.write("0 ");
                else
                    bw.write(result[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
    static int[][] floydwharshall() {
        int[][] distance = new int[N + 1][N + 1]; // 현재까지 계산된 최소비용

        // 그래프 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                distance[i][j] = graph[i][j];
            }
        }

        // k노드를 경유하는
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][k] != INF && distance[k][j] != INF) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }
        return distance;
    }
}
