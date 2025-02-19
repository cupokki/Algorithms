package boj.dp.n2482;

import java.io.*;

/**
 * 색상환
 * https://www.acmicpc.net/problem/2482
 * <p>
 * 색을 고리모양으로 표현, N색 색상환
 * 색상환의 인접한 두 색을 동시에 사용하지 않도록 K개의 색을 선택하는 경우의 수를 구하라.
 */
public class Main {
    static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine()); // [4, 1000]
        int k = Integer.parseInt(br.readLine()); // [1, N]

        int[][] dp = new int[n + 1][k + 1]; // n개의 색에서 k개의 색깔을 뽑는 경우의 수

        // base condition
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[2][0] = 1;
        dp[2][1] = 2;
        dp[3][0] = 1;
        dp[3][1] = 3;

        for (int i = 4; i <= n; i++) {
            dp[i][0] = 1; // base condition
            dp[i][1] = i;
            for (int j = 2; j <= k; j++) { // n/2 이하만 가능하다.
                // 먼저 첫번째 i를 선택한다.
                // 다음 i - 3(선택한 하나와 인접한 두개, 총 3개를 다음 선택에서 사용할 수 없다.)개 중에 j - 1개를 고르는 경우의 수를 구한다. => dp[i - 3][j - 1].
                // 중복 제거 1/2
                    /*
                    AC, AD, AE, AF
                    BD, BE, BF, BG
                    CE, CF, CG, *CA
                    DF, DG, *DA, *DB
                    EG, *EA, *EB, *EC - > 14개
                     */
                if (j * 2 == i) {
                    dp[i][j] = i / j % MOD;
                    break;
                } else
                    dp[i][j] = i * dp[i - 3][j - 1] % MOD; // 소숫점에 대해 어떻게?
            }
        }

        sb.append(dp[n][k]);
        System.out.println(sb);
    }
}

