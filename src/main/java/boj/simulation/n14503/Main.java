package boj.simulation.n14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/14503
 * 로봇 청소기
 * 로봇 청소기가 총소하는 영역의 개수를 구하라
 * N * M 크기의 방, 방안에 1으로 표기된 벽이 있다.(청소대상은 0)
 * 1. 현재칸이 청소 되지않았다면 청소
 * 2. 주변 4칸 중 빈칸이 없다면,
 *      바라보는 방향 반대로 후진 후 11번
 *          후진 시 벽이라면 정지
 * 3. 주변에 청소되지않은 칸이 존재한다면
 *      반시계 방향 90도 회전
 *      바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸이면 전진
 *      1번으로 돌아간다.
 *
 *  d=0 : 북, 동 남 서 순
 */
public class Main {
    static final int[][] delta = new int[][]{ // 시계 방량
            {-1, 0}, // 북
            {0, 1},  // 동
            {1, 0}, // 남
            {0, -1} // 서
    };
    static int N;
    static int M;
    static int[][] room;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]); // 3<= N, M <= 50
        M = Integer.parseInt(tokens[1]);
        room = new int[N][M]; // 1: 벽  / 0: 청소대상 / -1: 청소함
        tokens = br.readLine().split(" ");
        int r = Integer.parseInt(tokens[0]); // 3<= N, M <= 50
        int c = Integer.parseInt(tokens[1]);
        int dir = Integer.parseInt(tokens[2]);

        // 방 선언 및 초기화
        for (int i = 0; i < N; i++) {
            room[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        cleanRoom(r, c, dir);

        System.out.println(count);

    }

    // dfs
    static void cleanRoom(int r, int c, int dir) {
        // 현재 바닥 청소
        if(room[r][c] == 0 ) {
            room[r][c] = -1;
            count++;
        }

        // 4방향 탐색
        int newDir;
        for (int i = 0; i < 4; i++) {
//            int newDir = (dir - i + delta.length - 1) % delta.length;
            newDir = (dir + 3 - i) % 4;
            int nr = r + delta[newDir][0];
            int nc = c + delta[newDir][1];

            // 방 밖으로 이동하려고 한다면
            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                continue;
            if (room[nr][nc] == 0) {
                cleanRoom(nr, nc, newDir); // 다음위치로 탐색
                return;
            }
        }

        // 청소 할 곳이 없어 뒤로 후진
        int nr = r - delta[dir][0];
        int nc = c - delta[dir][1];
        if (nr < 0 || nc < 0 || nr >= N || nc >= M)
            return;
        if(room[nr][nc] != 1){ // 청소대상이나, 청소한 바닥이면
            cleanRoom(nr, nc, dir);
        }
        // 벽이면 종료됨
    }
}
