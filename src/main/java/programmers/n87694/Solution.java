package programmers.n87694;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    좌표평면에 직사각형이 여러개 주어지며,
    그 사각형들을 겹쳐만든 다각형의 테두리를 따라 움직인다.
    다각형의 위에 좌표에서, 목표 좌표까지 최소 길이를 구한다.

    모든 직사각형은 반드시 겹치지만, 꼭짓점만 공유하거나, 변만 공유하지 않으며
    완전히 안에 들어간 경우도 없다.

    직사각형이 표현되는 좌표는 50이하 자연수

    baba
    */
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;

        boolean[][] grid = new boolean[51][51];

        for (int[] pos : rectangle) {
            for (int i = pos[0]; i <= pos[2]; i++) {
                for (int j = pos[1]; j <= pos[3]; j++) {
                    grid[i][j] = true;
                }
            }
        }

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};


        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[51][51];
        q.offer(new int[]{characterY, characterX, 0});
        visited[characterY][characterX] = true;
        while (!q.isEmpty()) {
            int[] state = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = state[0] + dr[d];
                int nc = state[1] + dc[d];

                // 4방향 모두 불가일때,
                if (grid[nr][nc]) {
                    continue;
                }

                if (nr == itemY && nr == itemX) {
                    answer = Math.min(answer, state[2] + 1);
                }
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, state[2] + 1});
                }

            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1, 3, 7, 8)); // 17
        System.out.println(solution(new int[][]{{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}}, 9, 7, 6, 1)); // 11
        System.out.println(solution(new int[][]{{1, 1, 5, 7}}, 1, 1, 4, 7)); // 9
        System.out.println(solution(new int[][]{{2, 1, 7, 5}, {6, 4, 10, 10}}, 3, 1, 7, 10)); // 15
        System.out.println(solution(new int[][]{{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}}, 1, 4, 6, 3)); // 10
    }
}
