package boj.graph.n2644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 촌수계산
 * https://www.acmicpc.net/problem/2644
 *
 *
 */
public class Main {
    static boolean[][] ilchon;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ilchon = new boolean[N + 1][N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine()); // 간선 수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            ilchon[u][v] = ilchon[v][u] = true;
        }

        System.out.println(bfs(s, e));

    }
    static int bfs(int e, int s) {
        int result = -1;
        boolean[] visited = new boolean[N + 1];
        visited[s] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{s, 0});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int u = state[0];
            int chon = state[1] + 1;
            for (int v = 1; v <= N; v++) {
                if (!visited[v] && ilchon[u][v]) {
                    visited[v] = true;
                    q.offer(new int[]{v, chon});
                    if (v == e) {
                        result = chon;
                    }
                }
            }
        }
        return result;
    }
}
