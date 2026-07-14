package programmers.n60063;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    2*1크기의 로봇이 1,1에서 N*N로 로봇을 움직인다.(각 변의 길이는 100이하)
    지도는 0, 1로 이루어져 있고, 1은 벽이다.
    로봇은 벽이나 지도 밖으로 이동 할 수 없다.
    로봇은 회전가능, 상하좌우 이동가능
    */
    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};

    int h, w;
    int[][] map;
    public int solution(int[][] board) {
        h = board.length;
        w = board[0].length;
        map = board;
        Queue<int[]> q = new LinkedList<>(); // r, c, wingDir, cost

        boolean[][][] visited = new boolean[h][w][2];
        q.offer(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int r = state[0], c = state[1];
            int dir = state[2];
            int cost = state[3];
            int r2 = (dir == 0) ? r : r + 1;
            int c2 = (dir == 0) ? c + 1: c; // wing pos

            // 탐색 종료
            if ((r == h - 1 && c == w - 1) || (r2 == h - 1 && c2 == w - 1)) {
                return cost;
            }

            // move
            for (int i = 0; i < 4; i++) {
                int nr1 = r + dr[i], nc1 = c + dc[i];
                int nr2 = r2 + dr[i], nc2 = c2 + dc[i];
                if(isValid(nr1, nc1, nr2, nc2) && !visited[nr1][nc1][dir]) {
                    visited[nr1][nc1][dir] = true;
                    q.offer(new int[]{nr1, nc1, dir, cost + 1});
                }
            }

            // rotate
            if (dir == 0) { // 가로 -> 세로
                // 축(좌상단)을 기준으로 우하단 확인후, 윙을 아래로 옮긴다.
                if (isValid(r + 1, c, r + 1, c + 1)) { // 아래 두칸이 빔
                    if (!visited[r][c][1]) {
                        visited[r][c][1] = true;
                        q.offer(new int[]{r, c, 1, cost + 1});
                    }
                    if (!visited[r][c + 1][1]) {
                        visited[r][c + 1][1] = true;
                        q.offer(new int[]{r, c + 1, 1, cost + 1});
                    }
                }
                if (isValid(r - 1, c, r - 1, c + 1)) { // 상단 두칸이 비어있음
                    if (!visited[r - 1][c][1]) {
                        visited[r - 1][c][1] = true;
                        q.offer(new int[]{r - 1, c, 1, cost + 1});
                    }
                    if (!visited[r - 1][c + 1][1]) {
                        visited[r - 1][c + 1][1] = true;
                        q.offer(new int[]{r - 1, c + 1, 1, cost + 1});
                    }
                }
            } else {// 세로 -> 가로
                // 축(좌상단)을 기준으로 우하단 확인후, 윙을 우측으로 옮긴다.
                if (isValid(r, c + 1, r + 1, c + 1)) {
                    if (!visited[r][c][0]) { visited[r][c][0] = true;
                        q.offer(new int[]{r, c, 0, cost + 1});
                    }
                    if (!visited[r + 1][c][0]) {
                        visited[r + 1][c][0] = true;
                        q.offer(new int[]{r + 1, c, 0, cost + 1});
                    }
                }
                // 왼쪽 두 칸이 비어있는 경우 (좌측으로 회전)
                if (isValid(r, c - 1, r + 1, c - 1)) {
                    if (!visited[r][c - 1][0]) {
                        visited[r][c - 1][0] = true;
                        q.offer(new int[]{r, c - 1, 0, cost + 1});
                    }
                    if (!visited[r + 1][c - 1][0]) {
                        visited[r + 1][c - 1][0] = true;
                        q.offer(new int[]{r + 1, c - 1, 0, cost + 1});
                    }
                }
            }


        }
        return -1;
    }
    public boolean isValid(int r1, int c1, int r2, int c2) {
        if (r1 < 0 || c1 < 0 || r1 >= h || c1 >= w) return false;
        if (r2 < 0 || c2 < 0 || r2 >= h || c2 >= w) return false;
        if (map[r1][c1] == 1 || map[r2][c2] == 1) return false;

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