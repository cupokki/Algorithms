package programmers.n60063;

import java.util.*;

public class Solution {
    /*
    2*1크기의 로봇이 N*N지도에서 로봇을 움직인다.(각 변의 길이는 100이하)
    지도는 0, 1로 이루어져 있고, 1은 벽이다.
    로봇은 벽이나 지도 밖으로 이동 할 수 없다.
    로봇은 회전가능, 상하좌우 이동가능
    */
    public int solution(int[][] board) {
        int answer = 0;
        int[] robot = new int[]{0, 0, 0}; // r, c, direction

        Queue<int[]> q = new LinkedList<>();
        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};
        boolean[][][] visited = new boolean[board.length][board[0].length][4];

        while (!q.isEmpty()) {
            int[] state = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = state[0];
                int nc = state[1];
                if (validPos(board, nr, nc, d)) {
                    visited[nr][nc][d] = true;
                    q.offer(new int[]{});
                }
            }
        }
        return answer;
    }

    public boolean validPos(int[][] board, int r, int c, int dir) {
        return false;
    }

     public static void main(String[] args){
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