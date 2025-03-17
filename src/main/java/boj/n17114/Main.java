package boj.n17114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 미세먼지 안녕!
 * https://www.acmicpc.net/problem/17144
 *
 *  r,c 크기 격자
 *     - 2*1크기의 공청기가 항상 1열에 존재
 *     - 방에는 미세먼지가 자연수로 존재
 *     - 매 초마다 인접한 4방향으로 floor(current/5)확산
 *     - 확산후 current - floor(current/5) * d(가용방향)
 *     - 인접한 위치에 공청기가 있으면 확산x
 *
 * 공청기 1행
 *     - c열까지 도달하여 바람 반시계로 순환
 * 공청기 2행
 *      - c열까지 도달하여 바람이 시계로 순환
 *
 *  T초 경과후 방에 남아있는 미세먼지 양을 구하라.
 */
public class Main {
    static int R;
    static int C;
    static int[][] grid;
    static int[][] purifier = new int[2][2]; // 공청기 위치
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        grid = new int[R][C];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == -1) {
                    purifier[idx++][0] = i;
                    purifier[idx++][0] = j;
                }
            }
        }

        for (int t = 0; t < T; t++) {
            diffuseDust();
            circulateAir(purifier[0][0], purifier[0][1], true); // 반시계
            circulateAir(purifier[1][0], purifier[1][1], false); // 시계

        }
        System.out.println(getDustAmount());
    }
    static void diffuseDust() {
        boolean[][] origin = new boolean[R][C]; // 확산 근원지
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] > 0 )
                    origin[i][j] = true;
            }
        }
    }

    static void circulateAir(int pr, int pc, boolean isTop) { // purifier_r, c, isTop
        int[] clockwise = {};
    }

    static int getDustAmount() {
        int amount = 2; // 공청기값을 미리 계산
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                amount += grid[i][j];
            }
        }
        return amount;
    }
}
