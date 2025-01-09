package boj.dp.n15486;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15486
 * 퇴사 2
 *
 * 퇴사 1문제는 입력이 N <= 15
 * 퇴사 2문제는 입력이 N <= 1500000
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] period = new int[N + 1];
        int[] fee = new int[N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            period[i] = Integer.parseInt(st.nextToken());
            fee[i] = Integer.parseInt(st.nextToken());
        }


        int[] dp = new int[N + 2]; // N + 1일까지 확인한다.
        for (int i = N; i > 0; i--) {
            if (i + period[i] <= N + 1) {
                dp[i] = Math.max(dp[i + period[i]]+ fee[i], dp[i + 1]);
            } else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);
    }
}
