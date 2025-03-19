package boj.simulation.n17114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    static int R, C, T;
    static int[][] grid, temp;
    static int[] purifier;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        grid = new int[R][C];
        purifier = new int[2];

        int idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == -1) {
                    purifier[idx++] = i;
                }
            }
        }

        for (int t = 0; t < T; t++) {
            spreadDust();
            run();
        }

        System.out.println(getDustAmount());
    }


    static void spreadDust() {
        temp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] > 0) {
                    int spreadAmount = grid[i][j] / 5;
                    int spreadCount = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && ny >= 0 && nx < R && ny < C && grid[nx][ny] != -1) {
                            temp[nx][ny] += spreadAmount;
                            spreadCount++;
                        }
                    }
                    temp[i][j] += grid[i][j] - (spreadAmount * spreadCount);
                }
            }
        }

        // 확산 결과를 원래 맵에 반영
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] != -1) {
                    grid[i][j] = temp[i][j];
                }
            }
        }
    }

    static void run() {
        int top = purifier[0]; // 위쪽
        int bottom = purifier[1];

        // 위쪽 공기청정기 (반시계 방향)
        for (int i = top - 1; i > 0; i--) grid[i][0] = grid[i - 1][0];
        for (int j = 0; j < C - 1; j++) grid[0][j] = grid[0][j + 1];
        for (int i = 0; i < top; i++) grid[i][C - 1] = grid[i + 1][C - 1];
        for (int j = C - 1; j > 1; j--) grid[top][j] = grid[top][j - 1];
        grid[top][1] = 0;

        // 아래쪽 공기청정기 (시계 방향)
        for (int i = bottom + 1; i < R - 1; i++) grid[i][0] = grid[i + 1][0];
        for (int j = 0; j < C - 1; j++) grid[R - 1][j] = grid[R - 1][j + 1];
        for (int i = R - 1; i > bottom; i--) grid[i][C - 1] = grid[i - 1][C - 1];
        for (int j = C - 1; j > 1; j--) grid[bottom][j] = grid[bottom][j - 1];
        grid[bottom][1] = 0;
    }


    static int getDustAmount() {
        int amount = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] > 0) {
                    amount += grid[i][j];
                }
            }
        }
        return amount;
    }
}
