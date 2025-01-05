package boj.dp.n11055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11055
 * 가장 큰 증가하는 부분 수열
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.println(max);
    }
}
