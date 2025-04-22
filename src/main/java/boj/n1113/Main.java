package boj.n1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 수영장 만들기
 * https://acmicpc.net/problem/1113
 *
 * NM크기에다 높이가 다른 직육면체가 있다.
 * 각 칸은 직육면체의 높이가 쓰여있다.
 * 높이가 0이명 땅이다. 땅은 물을 흡수한다
 *
 * 배치가 주어질때 최대 물 양을 구하라.
 *
 * 모든칸에서 bfs를 수행한다.
 * 최저지점을
 */

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfsWithPQ());
    }

    static int bfsWithPQ() {
        // height 기준 오름차순 정렬 (최소 힙)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // 외곽 테두리 셀을 우선순위 큐에 삽입
        for (int i = 0; i < N; i++) {
            pq.add(new int[]{i, 0, map[i][0]});
            pq.add(new int[]{i, M - 1, map[i][M - 1]});
            visited[i][0] = true;
            visited[i][M - 1] = true;
        }
        for (int j = 1; j < M - 1; j++) {
            pq.add(new int[]{0, j, map[0][j]});
            pq.add(new int[]{N - 1, j, map[N - 1][j]});
            visited[0][j] = true;
            visited[N - 1][j] = true;
        }

        int totalWater = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], height = cur[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;

                int nh = map[nx][ny];
                if (nh < height) {
                    totalWater += height - nh;
                    pq.add(new int[]{nx, ny, height}); // 물 높이로 덮어서 넣음
                } else {
                    pq.add(new int[]{nx, ny, nh});
                }
            }
        }

        return totalWater;
    }
}
