package boj.dp.n9095;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/9095
 * 1,2,3 더하기
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 7가지
 * 정수를 n개 주어졌을 떄, n을 123합으로 나타내어라
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 0; t < T; t++) {
            int N = sc.nextInt();

            int[] dp = new int[N + 1];

            dp[0] = 0;
            if(N >= 1) dp[1] = 1; // 합을 나타낼때 수 1개 이상을 사용해야함 즉, 1
            if(N >= 2) dp[2] = 2;
            if(N >= 3) dp[3] = 4;
//            dp[4] = 7;

            for (int i = 4; i <= N; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
            System.out.println(dp[N]);
        }
    }
}
