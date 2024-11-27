package boj.dp.n1003;

import java.io.*;

/**
 * https://www.acmicpc.net/problem/1003
 * 피보나치 함수
 * 피보나치를 메모이제이션으로 구현하고, 0과 1이 각각 몇번 호출되는지 출력한다.x
 * 아니다 메모이제이션으로 성능을 늘리는 문제는 아니고 dp 점화식을 구하고 값만 구하면 된다.
 */
public class Main {
    static int[] memo;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            dp = new int[N + 1][2];

            dp[0] = new int[]{1, 0};
            if( N >= 1)  dp[1] = new int[]{0, 1};
            for (int i = 2; i <= N; i++) {
                dp[i][0] = dp[i- 1][0] + dp[i - 2][0];
                dp[i][1] = dp[i- 1][1] + dp[i - 2][1];
            }

            bw.write(dp[N][0] + " " + dp[N][1] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
// 문제에서 주어진 코드
//    static long fibonacci(long n) {
//        if( n == 0){
//            result[0]++;
//            return 0;
//        }
//        else if (n == 1) {
//            result[1]++;
//            return 1;
//        }
//        else {
//            return fibonacci(n - 1) + fibonacci(n - 2);
//        }
//    }
}
