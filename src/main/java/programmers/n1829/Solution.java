package programmers.n1829;

import java.util.*;

public class Solution {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

         boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || picture[i][j] == 0) continue;

                numberOfArea++;
                //bfs 수행
                int color = picture[i][j];
                int size = 1;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                visited[i][j] = true;
                while (!q.isEmpty()) {
                    int[] pos = q.poll();

                    for (int d = 0; d < 4; d++) {
                        int nr = pos[0] + dr[d];
                        int nc = pos[1] + dc[d];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                        if (!visited[nr][nc] && picture[nr][nc] == color) {
                            q.offer(new int[]{nr, nc});
                            visited[nr][nc] = true;
                            picture[i][j] = 0;
                            size++;
                            maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
                        }
                    }
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {
        solution(6, 4, new int[][]{
                {1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}});
    }
}