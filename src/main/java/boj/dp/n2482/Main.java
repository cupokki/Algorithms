package boj.dp.n2482;

import java.io.*;

/**
 * 색상환
 * https://www.acmicpc.net/problem/2482
 *
 *  색을 고리모양으로 표현, N색 색상환
 *  색상환의 인접한 두 색을 동시에 사용하지 않도록 K개의 색을 선택하는 경우의 수를 구하라.
 */
public class Main {
    static final int MOD = 1_000_000_003;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine()); // [4, 1000]
        int k = Integer.parseInt(br.readLine()); // [1, N]

        int[][] dp = new int[n + 1][k + 1]; // n개의 색에서 k개의 색깔을 뽑는 경우의 수

        //ex) dp[20][1] = 20;
        //ex) dp[20][2] = ;
        //ex) dp[20][10] = 2;
        //ex) dp[20][11] = 0; // 조건에 해당하는 경우의 수는 없다.

        // dp[4][1] = 4; // A, B, C, D
        // dp[4][2] = 2; // AC, BD

        // dp[5][1] = 5; // A/B/C/D/E
        // dp[5][2] = 10; // count({AC, AD}) * 5
        // dp[5][2] = 0;

        // dp[6][1] = 6; // A/B/C/D/E/F
        // dp[6][2] = 18; // AC, AD, AE, * 6
        // dp[6][3] = 2; // ACE, BDF

        // dp[7][1] = 7; // A/B/C/D/E/F/G
        // dp[7][2] = 28; // AC, AD, AE, AF * 7
        // dp[7][3] = 21; //ACE, ACF, ADF, * 7
        // dp[7][4] = 0;


        for (int i = 1; i <= n; i++) {
            // base condition
            dp[i][0] = 1; // 0개를 뽑는 경우
            dp[i][1] = i; // 한개 선택하는 경우
            for (int j = 2; j <= i / 2; j++) { // n/2 이하만 가능하다.
                if (i > 3){
                    // 하나를 뽑으면 그와 인접한 두개 모두 사용 불가, 따라서 i-3
                    // 먼저 첫번째 i를 선택한다.
                    // 다음 i - 3개 중에 j - 1개를 고르는 경우의 수를 구한다. => dp[i - 3][j - 1]?
                    if(dp[i - 3][j - 1] == 0) {
                        dp[i][j] = i / 2;
                    }
                    else
                        dp[i][j] = i * dp[i - 3][j - 1];
                }

            }
        }


        sb.append(dp[n][k]);
        System.out.println(sb);
    }
}

