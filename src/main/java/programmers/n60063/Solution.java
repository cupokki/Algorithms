package programmers.n60063;

import java.util.*;

public class Solution {
    /*
    2*1크기의 로봇이 1,1에서 N*N로 로봇을 움직인다.(각 변의 길이는 100이하)
    지도는 0, 1로 이루어져 있고, 1은 벽이다.
    로봇은 벽이나 지도 밖으로 이동 할 수 없다.
    로봇은 회전가능, 상하좌우 이동가능
    */
    int h, w;
    int[][] map;
    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};

    public int solution(int[][] board) {
        int answer = 0;
        h = board.length;
        w = board[0].length;
        map = board;

        Queue<int[]> q = new LinkedList<>(); // r, c, wingDir, dist

        boolean[][][] visited = new boolean[board.length][board[0].length][4];
        q.offer(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int r = state[0], c = state[1]; // 피벗 좌표
            int wingDir = state[2]; // 윙 방향
            int dist = state[3]; // 이동 거리

            // 회전;
            for (int i = 1; i < 4; i++) {
                int nw = (wingDir + i) % 4;
                int wr = r + dr[nw];
                int wc = c + dc[nw];
//                if () continue;// 회전 장애물 확인
                if (visited[r][c][nw]) continue;
                if (wr < 0 || wc < 0 || wr >= h || wc >= w) continue;
                if (board[wr][wc] == 0) {
                    visited[r][c][nw] = true;
                    q.offer(new int[]{r, c, nw, dist + 1});
                    if (wr == h - 1 && wc == w - 1) {
                        return dist + 1;
                    }
                }
            }

            // 이동
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                int nwr = nr + dr[wingDir];
                int nwc = nc + dc[wingDir];

                if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                if (nwr < 0 || nwc < 0 || nwr >= h || nwc >= w) continue;
                if (board[nr][nc] == 1 || board[nwr][nwc] == 1) continue;
                if (!visited[nr][nc][wingDir]) {
                    visited[nr][nc][wingDir] = true;
                    q.offer(new int[]{nr, nc, wingDir, dist + 1});
                    if ((nr == h - 1 && nc == w - 1) || (nwr == h - 1 && nwc == w - 1)) {
                        return dist + 1;
                    }
                }

            }
        }
        return -1;
    }

    public boolean validPos(int r, int c, int wingDir) {
        // 피벗 유효한지
        if (r < 0 || c < 0 || r >= h || c >= w) return false;
        if (map[r][c] == 1) return false;

        // 피벗이 아닌 부위가 범위 유효한지.
        int wr = r + dr[wingDir];
        int wc = c + dc[wingDir];
        if (wr < 0 || wc < 0 || wr >= h || wc >= w) return false;
        if (map[wr][wc] == 1) return false;

        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[][]{
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        })); // 7
    }
}