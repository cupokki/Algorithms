package boj.dp.n1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1520
 * 내리막 길
 * 직사각형 표 지도의 한 지점에 고도가 적혀있다.
 * 각 지점에서 상하좌우로 이동가능하다.
 * 시작점 : 좌상단
 * 도착점 : 우하단
 *
 * 이동시에 현 위치보다 낮은 점으로만 이동한다.
 * 이동 경로의 수를 구하라.
 */
public class Main {
    static int N;
    static int M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로 각 500이하 자연수


        map = new int[M + 1][N + 1];// 고도는 10000이하
        ex = M;
        ey = N;
        for (int m = 1; m <= M; m++) {
            st =  new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++) {
                map[m][n] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M + 1][N + 1]; // (0,0)에서 (M,N)까지 경우의 수

        dp[1][1] = 1; // (1,1) to (1,1)의 경우 하나
        // 일단 순차적으로 초기화 하되
        // 4방향 중에 초기화 되지않은 걸 참조하려 하면 계산을 큐에 넣어 보류?
        // 다시 계산하다 큐의 헤드에 위치한 값.. <- 다시 계산이 말도 안됨 이미 초기화 되어버려서

//        dfs(new int[]{1,1}, new int[]{M, N});

        dfs1(M, N);
//        dfs2(1, 1);
        // dfs로 모든 경로를 구한다. 그럼 그냥 끝인데? 왜?
        // 메모리 초과 발생-> 500 * 500 개의 재귀스택이 발생해서인가?
        //

//        System.out.println(h);
//        System.out.println(dp[1][1]);
        System.out.println(dp[M][N]);
//        System.out.println(count);
    }
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int count = 0;
    static int ex;
    static int ey;
    static int[][] dp;
    static void dfs1(int x, int y) {
        if (x == 1 && y == 1) {
            count++;
            return;
        }


        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 1 || nx > M || ny < 1 || ny > N || map[x][y] >= map[nx][ny]) {
                continue;
            }
            // 옮겨진 위치가 이미 갱신 기록이 있다면 (dp[x][y] != 0이 아니라면) 메모이제이션
            if (dp[nx][ny] != 0) {
                dp[ex][ey] += dp[nx][ny];
                continue;
            }
            dp[x][y] += dp[nx][ny];
            dfs1(nx, ny);
        }
    }

}
