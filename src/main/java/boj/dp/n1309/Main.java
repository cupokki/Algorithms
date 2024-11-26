package boj.dp.n1309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1309
 * 동물원
 * 2*N 크기의 우리가있다. ( N <= 100000)
 * 사자를 우리에 넣으려한다.
 * 사자는 가로 세로에 인접하면 안된다.
 * 사자를 우리에 넣는 경우의 수를 계산하라.
 * https://mojing.tistory.com/entry/BOJC-%EB%B0%B1%EC%A4%80-1309%EB%B2%88-%EB%8F%99%EB%AC%BC%EC%9B%90
 */
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        int[] dp = new int[N + 1];
//
//        dp[0] = 1;
//        if(N >= 1)  dp[1] = 3; // 좌, 우, 넣지않음
//
//        for(int i = 2; i <= N; i++) {
//            //바로 dp[i - 1]+ 아무것도 안넣기
//            //dp[i - 1] * 2 -> 왼쪽 오른쪽 다넣기
//            //중복되는 것 dp[i - 1] - dp[i-2]
//            dp[i] = (dp[i - 2] + dp[i - 1] + dp[i - 1])% 9901;
//        }
//
//        System.out.println(dp[N]);
//    }
//}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][3];

        dp[0][0] = 1; // 아무것도 넣지않음
        dp[0][1] = 0; // 왼쪽에 넣음
        dp[0][2] = 0; // 오른쪽 넣음

        if( N >= 1) {
            dp[1][0] = 1; // 아무것도 넣지않음
            dp[1][1] = 1; //
            dp[1][2] = 1;
        }
        if(N >= 2) {
            dp[2][0] = dp[1][0] + dp[1][1] + dp[1][2];
            dp[2][1] = dp[1][0] + dp[1][2];
            dp[2][2] = dp[1][0] + dp[1][1];
        }

        for(int i = 3; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }
        int result = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
        System.out.println(result);
    }
}