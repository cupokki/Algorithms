package boj.dp.n9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/9251
 * LCS
 *
 * 최장 공통 부분 수열 문제는 두 수열이 주어졌을 떄, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열의 길이는 1000이며 알파벳대문자만 사용
 * LCS길이를 출력하라
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cStr1 = (" " + br.readLine()).toCharArray();
        char[] cStr2 = (" " + br.readLine()).toCharArray(); // idx 한칸 늘릴려고
        int n = cStr1.length;
        int m = cStr2.length;

        int[][] dp = new int[n][m]; //i번째 문자까지 고려하여 j번째 열의 최댓값

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (cStr1[i] == cStr2[j])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        System.out.println(dp[n - 1][m - 1]);
    }
}
