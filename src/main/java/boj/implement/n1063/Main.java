package boj.implement.n1063;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1063
 * 킹
 * 8x8크기의 체스판
 * 킹 현재 위치가 주어진다. 킹은 8방향으로 움직인다(RLBT 조합, [수평][수직]).
 * A,1부터 H,8위치
 * 킹과 돌이 주어지고 돌은 킹의 움직임대로 움직인다.
 * N번의 이동이 주어질때
 * 돌이나 킹이 체스판 밖으로 나가면 해당회차 이동을 무시한다.
 * 마지막 킹과 돌의 위치를 구하라.
 */
public class Main {
    static final int[][] BOARD = new int[9][9];
    static int N;
    static int kr, kc; // 체스
    static int sr, sc; // 돌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String kingPos = st.nextToken();
        kc = kingPos.charAt(0) - 'A' + 1;
        kr = kingPos.charAt(1) - '0';

        String stonePos = st.nextToken();
        sc = stonePos.charAt(0) - 'A' + 1;
        sr = stonePos.charAt(1) - '0';

        N = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            move(DIR.valueOf(br.readLine()));
        }

        System.out.printf("%c%d\n", kc +'A'- 1, kr);
        System.out.printf("%c%d\n", sc +'A'- 1, sr);
    }
    enum DIR{
        R, L, B, T, RB, RT, LB, LT;
    }
    static int[] dr = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[] dc = {1, -1, 0, 0, 1, 1, -1, -1};
    static void move(DIR dir) {
        int nkr = kr + dr[dir.ordinal()];
        int nkc = kc + dc[dir.ordinal()];
        int nsr = sr + dr[dir.ordinal()];
        int nsc = sc + dc[dir.ordinal()];

        if (outOfBoundary(nkr, nkc)) {
            return;
        }


        if (nkr == sr && nkc == sc) {
            if (outOfBoundary(nsr, nsc))
                return;
            sr = nsr;
            sc = nsc;
        }
        kr = nkr;
        kc = nkc;
    }

    //
    static boolean outOfBoundary(int r, int c) {
        return r < 1 || r > 8 || c < 1 || c > 8;
    }
}
