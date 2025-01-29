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
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cStr;
        cStr = br.readLine().toCharArray();
        char[] cStr1 = new char[cStr.length + 1];
        for (int i = 1; i <= cStr.length; i++ ){
            cStr1[i] = cStr[i - 1];
        }
        cStr = br.readLine().toCharArray();
        char[] cStr2 = new char[cStr.length + 1];
        for (int i = 1; i <= cStr.length; i++ ){
            cStr2[i] = cStr[i - 1];
        }
        int length = cStr.length;

        int[][] dp = new int[length + 1][length + 1]; //i번재 문자까지 고려했을때 최대
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
//                if (cStr1[] == cStr2[]) {

//                }

            }
        }
        System.out.println(dp[length][length]);
    }
}
