package boj.simulation.n14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14502
 * 연구소
 *
 * 0 : 빈칸
 * 1 : 벽 ()
 * 2 : 바이러스 위치 (2개 이상, 10이하)
 *
 * 3 : candidate, 바이러스 주변이나 벽, 가장자리 주변만 탐색하면 된다.
 */
public class Main {
    static int N, M;
    static int empty = 0;
    static int[][] room;
    static int[][] virus = new int[10][2];
    static int virusCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == 2) {
                    virus[virusCount++] = new int[]{i, j};
                }else if(room[i][j] == 1) {

                }else {
                    empty++;
                }

            }
        }

        dfs(0);

        System.out.println(empty - 3 - min);

    }
    static int min = Integer.MAX_VALUE;
    static void dfs(int depth) {
        if (depth == 3) {
            int[][] copiedRoom = new int[N][M];
            for (int i = 0; i < N; i++ ){
                for (int j = 0; j < M; j++) {
                    copiedRoom[i][j] = room[i][j];
                }
            }

            int infected = 0;
            for (int i = 0; i < virusCount; i++) {
                infected += bfs(copiedRoom, virus[i][0], virus[i][1]);
            }
            min = Math.min(infected, min);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(room[i][j] == 0) {
                    room[i][j] = 1;
                    dfs(depth + 1);
                    room[i][j] = 0;
                }
            }
        }
    }
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int bfs(int[][] room, int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        int infected = 0;
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = pos[0] + dx[i];
                int nc = pos[1] + dy[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;
                // 방문하지 않은 빈칸만
                if (room[nr][nc] == 0) {
                    infected++;
                    q.offer(new int[]{nr, nc});
                    room[nr][nc] = 2;
                }
            }
        }
        return infected;
    }
}
