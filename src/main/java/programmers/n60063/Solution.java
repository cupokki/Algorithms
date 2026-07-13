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

    int[] rr = {0, -1, 0, 1}; // rotate r
    int[] rc = {1, 0, -1, 0}; // rotate c

    int h, w;
    int[][] map;
    public int solution(int[][] board) {
        int answer = 0;
        h = board.length;
        w = board[0].length;
        map = board;
        Queue<int[]> q = new LinkedList<>(); // r, c, wingDir, cost

        boolean[][][] visited = new boolean[h][w][2];
        q.offer(new int[]{0, 0, 0, 1, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int direction = state[3];
            int cost = state[4];
            int nr1, nc1, nr2, nc2;

            // vertical to horizontal

            // horizontal to vertical

            // move
            for (int i = 0; i < 4; i++) {
                nr1 = state[0] + dr[i]; nc1 = state[1] + dc[i];
                nr2 = state[2] + dr[direction]; nc2 = state[3] + dc[direction];
                if(isValid(nr1, nc1, nr2, nc1) && !visited[nr1][nc1][direction]) {
                    visited[nr1][nc1][direction] = true;
                    q.offer(new int[]{nr1, nc1, nr2, nc2, cost + 1});
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
    public boolean isGoal(int r1, int c1, int r2, int c2) {
        if (r1 == h - 1 && c1 == w - 1) return true;
        if (r2 == h - 1 && c2 == w - 1) return true;
        return false;
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