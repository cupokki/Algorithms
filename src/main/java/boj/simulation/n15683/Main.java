package boj.simulation.n15683;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, minBlindspot;
    static int[][] room;
    static List<int[]> cctvs = new ArrayList<>();
    static final int[][] DIRECTIONS = {
            {-1, 0}, // 상
            {1, 0},  // 하
            {0, 1},  // 우
            {0, -1}  // 좌
    };
    static final int[][][] CAMERA_MODES = {
            {{0}, {1}, {2}, {3}},                    // 1번 CCTV
            {{0, 1}, {2, 3}},                       // 2번 CCTV
            {{0, 2}, {1, 3}, {0, 3}, {1, 2}},       // 3번 CCTV
            {{0, 1, 2}, {0, 2, 3}, {0, 1, 3}, {1, 2, 3}}, // 4번 CCTV
            {{0, 1, 2, 3}}                          // 5번 CCTV
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        room = new int[n][m];
        minBlindspot = Integer.MAX_VALUE;

        // 맵 초기화 및 CCTV 위치 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] >= 1 && room[i][j] <= 5) {
                    cctvs.add(new int[]{i, j});
                }
            }
        }

        // 모든 CCTV 방향 조합 탐색
        simulate(0, new int[cctvs.size()]);
        System.out.println(minBlindspot);
    }

    static void simulate(int depth, int[] directions) {
        if (depth == cctvs.size()) {
            int[][] copy = copyRoom();
            for (int i = 0; i < cctvs.size(); i++) {
                applyCCTVCoverage(cctvs.get(i), CAMERA_MODES[room[cctvs.get(i)[0]][cctvs.get(i)[1]] - 1][directions[i]], copy);
            }
            minBlindspot = Math.min(minBlindspot, countBlindspots(copy));
            return;
        }

        int type = room[cctvs.get(depth)[0]][cctvs.get(depth)[1]] - 1;
        for (int i = 0; i < CAMERA_MODES[type].length; i++) {
            directions[depth] = i;
            simulate(depth + 1, directions);
        }
    }

    static void applyCCTVCoverage(int[] pos, int[] dirs, int[][] map) {
        for (int dir : dirs) {
            int x = pos[0];
            int y = pos[1];
            while (true) {
                x += DIRECTIONS[dir][0];
                y += DIRECTIONS[dir][1];
                if (x < 0 || x >= n || y < 0 || y >= m || map[x][y] == 6) break;
                if (map[x][y] == 0) map[x][y] = -1;
            }
        }
    }

    static int countBlindspots(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) count++;
            }
        }
        return count;
    }

    static int[][] copyRoom() {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(room[i], 0, copy[i], 0, m);
        }
        return copy;
    }
}
