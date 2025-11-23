package programmers.n169199;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
     보드게임에서 말의 최소 이동횟수를 구한다.(도달불가시 -1)_
     - 상하좌우 이동
     - 이동시에 벽이나 장애물이 닿을때까지 움직임
     - .: 빈곳, R: 로봇, "D": 장애물, "G": 목표
     */
    public static int solution(String[] board) {

        int R = board.length, C = board[0].length();
        int gr = -1, gc = -1;
        int sr = -1, sc = -1;
        boolean[][] walls = new boolean[R][C];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'G') {
                    gr = i;
                    gc = j;
                }
                if (board[i].charAt(j) == 'R') {
                    sr = i;
                    sc = j;
                }
                if (board[i].charAt(j) == 'D') {
                    walls[i][j] = true;
                }
            }
        }

        // searching (bfs)
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 0});// previous r, c, steps
        visited[sr][sc] = true;
        int min = Integer.MAX_VALUE;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while(!q.isEmpty()) {
            int[] state = q.poll();
            int r = state[0]; int c = state[1]; int steps = state[2];

            if (steps > min) {
                continue;
            }

            if (r == gr && c == gc) {
                min = Math.min(min, steps);
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                while(nr  >= 0 && nr < R && nc >= 0 && nc < C && !walls[nr][nc]) {
                    nr += dr[d];
                    nc += dc[d];
                }
                nr -= dr[d];
                nc -= dc[d];
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, steps + 1});
                }
            }

        }

        int answer = min != Integer.MAX_VALUE ? min : -1;
        return answer;
    }

    public static void main(String[] args) {
        String[] b1 = {
                "...D..R", ".D.G...", "....D.D", "D....D.", "..D...."
        };
        System.out.println(solution(b1));

        String[] b2 = {
                ".D.R", "....", ".G..", "...D"
        };
        System.out.println(solution(b2));
    }
}
