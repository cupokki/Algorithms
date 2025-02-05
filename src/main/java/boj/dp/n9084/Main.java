package boj.dp.n9084;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 동전
 * https://www.acmicpc.net/problem/9084
 *
 * 액면가가 다른 동전 종류가 N개 주어질때, M원 을 만드는 경우의 수를 출력한다.
 * 방법의 수는 정수형 최댓값 보다 작다. 같은 동전이 여러번 주어지않는다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); //( 1 <= N <= 10)
        for (int t = 0; t < T; t++) {

            int N = Integer.parseInt(br.readLine()); // ( 1 <= N <= 20)
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] coins = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                coins[i] = Integer.parseInt(st.nextToken()); // ( 1 <= N <= 20)
            }
            int M = Integer.parseInt(br.readLine()); // ( 1 <= M <= 10000)

            //dp[i]는 i원을 만드는 모든 경우의 수
            int[] dp = new int[M + 1];

            dp[0] = 1; // 아무것도 놓지않으면

            for (int i = 1; i <= N; i++ ) {
                for (int j = 1; j <= M; j++) {
                    if (j >= coins[i]) {
                        dp[j] += dp[j - coins[i]];
                    }

                }
            }
            bw.write(dp[M] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
