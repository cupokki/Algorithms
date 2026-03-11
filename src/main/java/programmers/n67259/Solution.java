package programmers.n67259;

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
        int answer = 0;
        int n = board.length;
        Queue<int[]> q = new LinkedList<>();
        // q.offer(new int[] {r, c, dir, cost});
//        q.offer(new int[] {0, 0, 1, 0});
        q.offer(new int[] {0, 0, 2, 0});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int r = state[0];
            int c = state[1];
            int head = state[2];
            int cost = state[3];

            if (answer > cost) continue;
            int d = 0;
            while (checkBorder(n, r + dir[head][0] * (d + 1),  c + dir[head][1] * (d + 1))
                    && board[r + dir[head][0] * (d + 1)][c + dir[head][1] * (d + 1)] == 0) {
                d++;
            }
            if (d == 0) continue;
            int nr = r + dir[head][0] * d;
            int nc = c + dir[head][1] * d;
            int nextCost = cost + (d * 100);
            if (nr == n - 1&& nc == n - 1) {
                answer = nextCost;
            }
            q.offer(new int[]{nr, nc, (head + 1) % 4, nextCost + 500});
            q.offer(new int[]{nr, nc, (4 + head - 1) % 4, nextCost + 500});
        }

        return answer;
    }

    static boolean checkBorder(int n, int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }

    public static void main (String[] args) {
        System.out.println(solution(new int[][]{
                {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
        }));

        System.out.println(solution(new int[][]{
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,1,0,0,1},
                {0,0,0,1,0,0,1,0},
                {0,0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0,0},
                {1,0,0,0,0,0,0,0}
        }));

        System.out.println(solution(new int[][]{
                {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}
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
