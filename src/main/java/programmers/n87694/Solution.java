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

    */
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;

        boolean[][] grid = new boolean[102][102];
        boolean[][] visited = new boolean[102][102];

        for (int[] r : rectangle) {
            int x1 = r[0] * 2; int y1 = r[1] * 2;
            int x2 = r[2] * 2; int y2 = r[3] * 2;

            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    // 테두리인 경우에만 true
                    if (y == y1 || y == y2 || x == x1 || x == x2) {
                        grid[y][x] = true;
                    }
                }
            }
        }

        for (int[] r : rectangle) {
            int x1 = r[0] * 2; int y1 = r[1] * 2;
            int x2 = r[2] * 2; int y2 = r[3] * 2;

            for (int y = y1 + 1; y < y2; y++) {
                for (int x = x1 + 1; x < x2; x++) {
                    grid[y][x] = false;
                }
            }
        }

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{characterY * 2, characterX * 2, 0});
        visited[characterY * 2][characterX * 2] = true;
        while (!q.isEmpty()) {
            int[] state = q.poll();

            if (state[0] == (itemY * 2) && state[1] == (itemX * 2)) {
                return state[2] / 2;
            }

            for (int d = 0; d < 4; d++) {
                int nr = state[0] + dr[d];
                int nc = state[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= 102 || nc >= 102) {
                    continue;
                }

                if (!visited[nr][nc] && grid[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{ nr, nc, state[2] + 1 });
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1, 3, 7, 8)); // 17
        System.out.println(solution(new int[][]{{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}}, 9, 7, 6, 1)); // 11
        System.out.println(solution(new int[][]{{1, 1, 5, 7}}, 1, 1, 4, 7)); // 9
        System.out.println(solution(new int[][]{{2, 1, 7, 5}, {6, 4, 10, 10}}, 3, 1, 7, 10)); // 15
        System.out.println(solution(new int[][]{{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}}, 1, 4, 6, 3)); // 10
    }
}
