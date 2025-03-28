package boj.graph.n2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 안전 영역
 * https://www.acmicpc.net/problem/2468
 *
 * 고저를 표기하는 table
 * 비에 잠기지 않는 최대영역
 * 장마철엔
 */
public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
           map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int max = 0;
        for (int h = 0; h <= 100; h++) {
            boolean[][] flood = new boolean[N][N];
            for (int i = 0; i < N; i ++) {
                for (int j = 0; j < N; j ++) {
                    flood[i][j] = map[i][j] <= h;
                }
            }
            int cnt = 0;
            for (int i = 0; i < N; i ++) {
                for (int j = 0; j < N; j ++) {
                    if (!flood[i][j]) {
                        bfs(i, j, flood);
                        cnt++;
                    }
                }
            }
//            if (max > cnt) {
//                break;
//            } else if (max < cnt) {
//                max = cnt;
//            }
            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static void bfs(int r, int c, boolean[][] flood) {
        flood[r][c] = true;
        Queue<int[]> q = new LinkedList();
        q.offer(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || flood[nr][nc])
                    continue;
                q.offer(new int[]{nr, nc});
                flood[nr][nc] = true; // flood를 매 h루프에서 초기화 하므로 visited로 사용
            }
        }
    }
}
