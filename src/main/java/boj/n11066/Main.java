package boj.n11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파일 합치기
 * https://www.acmicpc.net/problem/11066
 *
 * 백트래킹, DP
 * 제한시간, t를 보니 백트래킹으론 못풀것같고
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[N + 1];
            dp[1] = arr[1];
            dp[2] = arr[1] + arr[2];
//            dp[2] = arr[2] + dp[1] + dp[1] || arr[1] + arr[2] + arr
            for(int i = 3; i <= N; i++) {
                dp[i] = dp[i - 1] + (dp[i - 1] + arr[i]);
//                int b = 2 * (arr[i] + arr[i - 1] + dp[i - 2]);
                int prev = arr[i];
                for (int j = 1; j < i; j++) {
                    prev = (prev + arr[i - j]) * 2;
                    dp[i] = Math.min(dp[i], 2 * (dp[i - j] + prev));
                }
            }
            System.out.println(dp[N]);
        }

    }
}
