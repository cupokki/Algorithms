package programmers.n67259;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    격자판에서 1은 벽
    0,0에서 n,n까지 경주로를 만든다.
    직선도로 한 칸: 100월
    코너: 500원 추가
    경주로를 건설하는 최소비용을 출력한다.
    직진을 우선으로 처리한다.

    교차로가 발생 할 수 있는가?
    */
    static int[][] dir = {
            {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    public static int solution(int[][] board) {
        int n = board.length;

        int answer = Math.min(bfs(n, 1, board), bfs(n, 2, board));
        return answer;
    }
    static int bfs(int n, int initDir, int[][] board) {
        int result = Integer.MAX_VALUE;
        Queue<int[]> q = new LinkedList<>();
//        boolean[][] visited = new boolean[n][n];
//        visited[0][0] = true;

        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        q.offer(new int[] {0, 0, initDir, 0});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int r = state[0];
            int c = state[1];
            int head = state[2];
            int cost = state[3];
            if (result < cost) continue;
            if (r == n - 1 && c == n - 1) {
                result = cost;
                continue;
            }
            cost += 100;
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (checkBorder(n, nr, nc) && cost < costs[nr][nc] && board[nr][nc] == 0) {
//                    visited[nr][nc] = true;
                    costs[nr][nc] = cost;
                    q.offer(new int[] {nr, nc, d, cost + (d != head ? 500 : 0)});
                }
            }
        }
        return result;
    }

    static boolean checkBorder(int n, int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }

    public static void main (String[] args) {
//        System.out.println(solution(new int[][]{
//                {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
//        }));
//
//        System.out.println(solution(new int[][]{
//                {0,0,0,0,0,0,0,1},
//                {0,0,0,0,0,0,0,0},
//                {0,0,0,0,0,1,0,0},
//                {0,0,0,0,1,0,0,0},
//                {0,0,0,1,0,0,0,1},
//                {0,0,1,0,0,0,1,0},
//                {0,1,0,0,0,1,0,0},
//                {1,0,0,0,0,0,0,0}
//        }));

        System.out.println(solution(new int[][]{
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 0, 0}
        }));

        System.out.println(solution(new int[][]{
                {0,0,0,0,0,0},
                {0,1,1,1,1,0},
                {0,0,1,0,0,0},
                {1,0,0,1,0,1},
                {0,1,0,0,0,1},
                {0,0,0,0,0,0},
        }));
    }
}
