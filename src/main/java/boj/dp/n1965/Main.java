package boj.dp.n1965;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 상자넣기
 * https://www.acmicpc.net/problem/1965
 * 일렬로 상자, 상자마다 크기, 정렬된 크기 순대로 상자를 상자안에 넣어 정리
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] =1;
            for (int j = 1 ; j < i; j++) {
                if(arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        // 최장 길이 계산
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);


    }
}
