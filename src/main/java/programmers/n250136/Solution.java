package programmers.n250136;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    N * M 측면도에서 수직으로 시추관을 꼽는다. 0은 땅, 1은 석유
    N, M은 500이하의 자연수
    시추관은 가장 깊은 곳까지 뚫어지며, 시추관이 지난땅이 석유가 존재한다면,
    이어진 석유는 다 시추할 수 있다.
    가장 많은 석유를 시추하는 M열을 구하라
     */
    static int N;
    static int M;
    public static int solution(int[][] land) {
        int answer = 0;

        N = land.length;
        M = land[0].length;

        for (int i = 0; i < M; i++) {
            boolean[][] visited = new boolean[N][M];
            int amount = 0;
            for (int j = 0; j < N; j++) {
                if (land[j][i] == 1 && !visited[j][i]) {
                    amount += bfs(land, visited, j, i);
                }
            }
            answer = Math.max(amount, answer);
        }
        return answer;
    }
    static final int[] dr = {0, 0, 1, -1};
    static final int[] dc = {1, -1, 0, 0};
    static int bfs(int[][] land, boolean[][] visited, int sr, int sc) {
        int amount = 1;

        Queue<int[]> q = new LinkedList<>();
        visited[sr][sc] = true;
        q.offer(new int[]{sr, sc});

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (land[nr][nc] == 1 && !visited[nr][nc]) {
                    q.offer(new int[]{nr, nc});
                    amount++;
                    visited[nr][nc] = true;
                }
            }
        }

        return amount;
    }

    public static void main(String[] args) {
        var i1 = new int[][]{
                {0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}
        };
        System.out.println(solution(i1));

        var i2 = new int[][]{
                {1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}
        };
        System.out.println(solution(i2));
    }
}
