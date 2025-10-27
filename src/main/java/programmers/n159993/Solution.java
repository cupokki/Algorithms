package programmers.n159993;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    L: 로 표시된 레버를 찾아 통로를
     */
    static char[][] map;
    static int R;
    static int C;
    static int sr = 0;
    static int sc = 0;
    static int er = 0;
    static int ec = 0;
    static int lr = 0;
    static int lc = 0;
    public static int solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = maps[i].charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'S') {
                    sr = i; sc = j;
                }
                if (map[i][j] == 'E') {
                    er = i; ec = j;
                }
                if (map[i][j] == 'L') {
                    lr = i; lc = j;
                }
            }
        }
        int pathA = bfs(sr, sc, lr, lc);
        int pathB = bfs(lr, lc, er, ec);

        System.out.println(pathA);
        System.out.println(pathB);
        if (pathA == -1 || pathB == -1) {
            return -1;
        }
        return pathA + pathB;
    }
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    public static int bfs(int sr, int sc, int er, int ec) {
        int[][] visited = new int[R][C];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = 1;
        int result = -1;
        while(!q.isEmpty()) {
            int[] state = q.poll();
            if (state[0] == er && state[1] == ec) {
                result = state[2];
                return result;
            }
            for (int d = 0; d < 4; d++) {
                int nr = state[0] + dr[d];
                int nc = state[1] + dc[d];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                if (map[nr][nc] == 'X') {
                    continue;
                }
                if (visited[nr][nc] < 2) {
                    visited[nr][nc]++;
                    q.offer(new int[]{nr, nc, state[2] + 1});
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] input1 = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(solution(input1));

        String[] input2 = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
        System.out.println(solution(input2));

    }
}
