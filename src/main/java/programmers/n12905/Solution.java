package programmers.n12905;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
//    static int[][] Board;
//    static boolean[][] visited;
//    static int R, C;

    public static int solution(int[][] board) {
//        Board = board;
        int R = board.length;
        int C = board[0].length;
        int answer = 0;

        int[][] dp = new int[R][C];

        for (int r = 0; r < R; r++) {
            dp[r][0] = board[r][0];
        }

        for (int c = 0; c < C; c++) {
            dp[0][c] = board[0][c];
        }

        for (int r = 1; r < R; r++) {
            for (int c = 1; c < C; c++) {
                dp[r][c] = board[r][c];
                if (board[r][c] != 0
                        && dp[r][c - 1] != 0
                        && dp[r - 1][c] != 0
                        && dp[r - 1][ c- 1] != 0
                ) {

                    dp[r][c] = Math.min(dp[r][c - 1], Math.min(dp[r -  1][c], dp[r - 1][c - 1])) + 1;
                }
                answer = Math.max(dp[r][c] * dp[r][c], answer);
            }
        }


        return answer;
    }

//    static boolean func(int[][] board, int r, int c) {
//        int num = board[r][c];
//        if (r + 1 >= R || c + 1 >= C) {
//            return false;
//        }
//        return num == board[r][c + 1]
//                && num == board[r + 1][c]
//                && num == board[r + 1][c + 1];
//    }
//
//    static boolean check(int r, int c, int len) {
//        for (int i = r; i < r + len; i++) {
//            for (int j = c; j < c + len; j++) {
//                if (Board[i][j] == 0) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    static int[] dr = {1, 0}; // 정사각형으로 움직이고 항상 좌상단에서 접근하므로, 좌, 하, 좌하만 고려한다.
//    static int[] dc = {0, 1};
//
//    static int bfs(int sr, int sc) {
//        int size = 1;
//        Queue<int[]> q = new LinkedList<>();
//        q.offer(new int[]{sr, sc, 1});
//        visited[sr][sc] = true;
//
//        while (!q.isEmpty()) {
//            int[] state = q.poll();
//            size = Math.max(size, state[2]); // 갱신
//            for (int d = 0; d < 2; d++) {
//                int nr = state[0] + dr[d];
//                int nc = state[1] + dc[d];
//
//                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
//                    continue;
//                }
//                if (!visited[nr][nc] && Board[nr][nc] == 1) {
//                    visited[nr][nc] = true;
//                    q.offer(new int[]{nr, nc, state[2] + 1});
//                }
//            }
//        }
//        return size;
//    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 1, 0}
        }));
        System.out.println(solution(new int[][]{
                {0, 0, 1, 1},
                {1, 1, 1, 1}
        }));
        System.out.println(solution(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        }));
        System.out.println(solution(new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        }));
        System.out.println(solution(new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}
        }));
    }
}
