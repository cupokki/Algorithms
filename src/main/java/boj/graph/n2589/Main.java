package boj.graph.n2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 보물섬
 * https://www.acmicpc.net/problem/2589
 * NM크기의 지도, L과 W로 육지와 바다 표기
 * 이웃한 육지로 이동가능(상하좌우) 1시간소요
 * 가장 긴 경로의 양 끝에 보물이 있다. 가장 긴 경로는 두 지점의 최단경로이어야한다.
 * 플로이드와셜?
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j]= str.charAt(j) == 'L';
            }
        }


        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]) {
                    max = Math.max(bfs(map, N, M, i, j), max);
                }
            }
        }
        System.out.println(max);
    }
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int bfs(boolean[][] map, int N, int M,int i, int j) {

        int[] root = new int[]{i, j, 0}; // 각각 r, c, dist
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[i][j] = true;
        q.offer(root);
        int max = 0;
        while(!q.isEmpty()) {
            int[] prev = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = prev[0] + dr[d];
                int nc = prev[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                if (!visited[nr][nc] && map[nr][nc]) { // 방문하지 않은 땅
                    int dist = prev[2] + 1;
                    max = Math.max(dist, max);
                    q.offer(new int[]{nr, nc, dist});
                    visited[nr][nc] = true;
                }
            }
        }
        return max;
    }

}
