package boj.dp.n11727;

import java.util.Scanner;

/**
 * https://acmicpc.net/problem/11727
 * 2 * N 크기의 직사각형에 1*2 ,2*2타일을 채우는 경우의 수
 *
 * 마지막 부분이 어떻게 바뀌는가
 * 1 ) 2 * 1블록으로 마무리
 * 2 ) 1 * 2블록으로 2 * 2 크기로 마무리
 * 3 ) 2 * 2블록으로 2 * 2 크기로 마무리
 */
//public class Main {
//    static final int MOD = 10_007;
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt(); // [1, 1000]
//        int[] dp = new int[N + 1];
//
//        dp[0] = 0;
//        if (N >= 1) dp[1] = 1; // 세로로 하나
//        if (N >= 2) dp[2] = 3; // 세로로 두줄, 가로로 두줄, 2by2하나
//        for (int i = 3; i <= N; i++) {
//            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 2]) % MOD;
//        }
//
//        System.out.println(dp[N]);
//    }
//}
public class Main {
    static final int MOD = 10_007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // [1, 1000]

        //다음 계산을 위해 필요한 값이 i-1일때와 i-2일때 2개라는 것을 알 수 있다.
        //메모리를 최적화 할 수 있다.
        int[] dp = new int[3];

        dp[0] = 0;
        if (N >= 1) dp[1] = 1; // 세로로 하나
        if (N >= 2) dp[2] = 3; // 세로로 두줄, 가로로 두줄
        for (int i = 3; i <= N; i++) {
            dp[0] = (dp[1] + dp[1] + dp[2]) % MOD;
            dp[1] = dp[2];
            dp[2] = dp[0];
        }

        int result;
        if (N < 3)
            result = dp[N];
        else
            result = dp[0];
        System.out.println(result);
    }
}
