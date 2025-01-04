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

        int min = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (min <= arr[i]) { // 증가 수열이고
                dp[i] = dp[i - 1] + arr[i];
                min = arr[i];
            } else if(dp[i - 1] > dp[i - 2] + arr[i]){ // 증가수열이 아닌데 arr[i]로 새로 시작하는게 크다면
                dp[i] = arr[i];
                min = arr[i];
            }
        }

        System.out.println(dp[N]);
    }
}
