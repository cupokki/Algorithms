package programmers.n12905;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[][] Board;
    static boolean[][] visited;
    static int R, C;
    public static int solution(int [][]board) {
        Board = board;
        R = board.length;
        C = board[0].length;
        visited = new boolean[R][C];
        int answer = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visited[r][c] && Board[r][c] == 1) {
                    int h = 0;
                    int w = 0;
                    while(r + h < R && Board[r + h][c] == 1) h++;
                    while(c + w < C && Board[r][c + w] == 1) w++;
                    int len = Math.min(h, w);
                    if (check(r, c, len)) {
                        answer = Math.max(answer, len * len);
                    }


//                    answer = Math.max(answer, bfs(r, c));

                }
            }
        }

        return answer;
    }
    static boolean check(int r, int c, int len) {
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if(Board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    static int[] dr = {1, 0}; // 정사각형으로 움직이고 항상 좌상단에서 접근하므로, 좌, 하, 좌하만 고려한다.
    static int[] dc = {0, 1};
    static int bfs(int sr, int sc) {
        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 1});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] state = q.poll();
            size = Math.max(size, state[2]); // 갱신
            for (int d = 0; d < 2; d++) {
                int nr = state[0] + dr[d];
                int nc = state[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }
                if (!visited[nr][nc] && Board[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, state[2] + 1});
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {0,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {0,0,1,0}
        }));
        System.out.println(solution(new int[][] {
                {0, 0, 1, 1},
                {1, 1, 1, 1}
        }));
    }
}
