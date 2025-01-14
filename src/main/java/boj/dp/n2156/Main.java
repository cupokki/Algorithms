package boj.dp.n2156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/15988
 * 1, 2, 3 더하기 3
 *
 * 테스트케이스 t가 주어지며,
 * 정수 n을 1, 2, 3의 합으로 나타내는 방법의 수를 작성하여라
 * 방법의 수는 1_000_000_009로 나눈 수를 출력한다.
 */
public class Main {
    static final int MOD = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            long[] dp = new long[N + 1];

            dp[0] = 0;
            if (N >= 1) dp[1] = 1;
            if (N >= 2) dp[2] = 2; // dp[1]"+1", dp[0]"+2"; //  11, 2
            if (N >= 3) dp[3] = 4; // dp[2]"+1", dp[1]"+2", dp[0]"+3"
            // 111, 12, 21, 3

            for (int i = 4; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
            }

            System.out.println(dp[N]);
        }
    }
}
