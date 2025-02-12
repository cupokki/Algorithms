package boj.dp.n1915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 가장 큰 정사각형
 * https://www.acmicpc.net/problem/1915
 * N by M의 0,1로 구성된 배열이 있다. 이 배열에서 1로 된가장 큰 정사각형의 크기를 구하라.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 ≤ n, m ≤ 1,000
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][M + 1];
        int max = 0; // 입력으로 가능한 최솟값
        for (int i = 1; i <= N; i++) {
            char[] cstr = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                dp[i][j] = cstr[j - 1] - '0'; // 0또는 1
                if (dp[i][j] == 1) { // 현재 위치가 1이면
                    // 작은 수를 골라 1을 더한 것이 최대 변(최소가 현재것보다 크다 -> 더 큰 정사각형을 만들 수 있다.)
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max * max);
    }
}
