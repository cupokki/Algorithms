package boj.graph.n16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 인구 이동
 * https://www.acmicpc.net/problem/16234
 * N × N 크기의 땅, 칸 하나당 나라 하나. 나라마다 사람이 산다.
 * 인구 이동
 *  1. 국경선을 공유하는 두 나라의 차이가 L명이상 R명 이하라면, 두나라 공유 국경선 오늘 하루 동안 연다.
 *  2. 열려야할 국경선이 모두 열렸다면 이동
 *  3. 국경 선이 열려있어 인접한 칸만 이동가능, 하루 동안 연합이된다.
 *  4. 엽합을 이루는 칸의 인구수는 (연합의 인구수) / (연합의 칸 수) 소수점은 버림
 *  5. 연합해체하고 모든 국경을 닫는다.
 *
 *  며칠동안 인구이동이 발생하는가.
 */

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int days = 0;
        while (true) {
            visited = new boolean[N][N];
            boolean isMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) isMoved = true;
                    }
                }
            }

            if (!isMoved) break; // 더 이상 이동이 없으면 종료
            days++;
        }

        System.out.println(days);
    }

    static boolean bfs(int r, int c) {
        List<int[]> union = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        union.add(new int[]{r, c});
        visited[r][c] = true;

        int sum = map[r][c];

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                int gap = Math.abs(map[pos[0]][pos[1]] - map[nr][nc]);
                if (gap >= L && gap <= R) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    union.add(new int[]{nr, nc});
                    sum += map[nr][nc];
                }
            }
        }

        if (union.size() == 1) return false; // 연합이 하나뿐이면 이동 없음

        int population = sum / union.size();
        for (int[] pos : union) {
            map[pos[0]][pos[1]] = population; // 인구 통일
        }

        return true;
    }
}