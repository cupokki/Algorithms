package boj.graph.n1941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 소문난 칠공주
 * https://www.acmicpc.net/problem/1941
 */
public class Main {
    static char[][] seat = new char[5][5];
    static boolean[][] sevenPrincess = new boolean[5][5];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            seat[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sevenPrincess[i][j] = true;
                backtracking(seat[i][j] == 'S' ? 1 : 0, seat[i][j] == 'Y' ? 1 : 0, i * 5 + j);
                sevenPrincess[i][j] = false;
            }
        }

        System.out.println(count);
    }
    static int lastR = 0;
    static int lastC = 0;
    static void backtracking(int s, int y, int start) {
        if (y >= 4)
            return;
        if (s + y == 7) {
            if (s >= 4 && check())
                count++;
            return;
        }

        for (int i = start; i < 25; i++) {
            int r = i / 5;
            int c = i % 5;
            if (!sevenPrincess[r][c]) {
                sevenPrincess[r][c] = true;
                lastR = r;
                lastC = c;
                backtracking(
                        s + (seat[r][c] == 'S' ? 1 : 0),
                        y + (seat[r][c] == 'Y' ? 1 : 0),
                        i
                );
                sevenPrincess[r][c] = false;
            }
        }
    }
    // bfs
    static boolean check() {
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{lastR, lastC});
        visited[lastR][lastC] = true;
        int maxConnect = 1;
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];
                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5)
                    continue;
                if (!visited[nr][nc] && sevenPrincess[nr][nc]) {
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    maxConnect++;
                }
            }
        }
        return maxConnect == 7;
    }
}