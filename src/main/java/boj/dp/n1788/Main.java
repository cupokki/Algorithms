package boj.dp.n1788;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 피보나치 수의 확장
 * https://www.acmicpc.net/problem/1788
 *
 * 피보나치 수의 점화식은 0이상 n에 대해서만 정의된다.
 * 만약 피보나치를 음수로도 확장할 수 있다면?
 * 절댓값이 백만 이하인 정수가 주어질 때 F(n)을 고려한다.
 */
public class Main {
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[Math.abs(N) + 1];

        // 초기식
        dp[0] = 0;
        // f(1) = f(0) + f(-1)
        // f(-1) = f(1) - f(0) = 1 - 0 = 1;
        if (Math.abs(N) >= 1) dp[1] = 1;

        if(N > 0) {
            for(int i = 2; i <= Math.abs(N); i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            }
        } else {
            for(int i = 2; i <= Math.abs(N); i++) {
                // 실제론 음의 방향이지만 구현은 양의 방향임
                // dp[i] = (dp[i + 2] - dp[i + 1]) % MOD;
                dp[i] = (dp[i - 2] - dp[i - 1] ) % MOD;
            }
        }
        // 둘째줄에 절댓값
        if (dp[Math.abs(N)] > 0) {
            System.out.println(1);
        } else if (dp[Math.abs(N)] < 0) {
            System.out.println(-1);
        } else {
            System.out.println(0);
        }
        // 양수면 1, 음수면 -1, 0은 0을 출력한다.
        System.out.println(Math.abs(dp[Math.abs(N)]));
    }
}
