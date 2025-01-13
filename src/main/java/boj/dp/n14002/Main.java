package boj.dp.n14002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/14002
 * 가장 긴 증가하는 부분 수열 4
 * N (1 ≤ N ≤ 1,000)
 * (1 ≤ Ai ≤ 1,000)
 * 최장 길이와 그 내용을 출력. 여러 개라면 그중 아무거나 출력하라.
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
        int maxSize = 0;
        int[] result = new int[N + 1];  // 이전값 포인팅
        int head = 0; // 현재
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            result[i] = 0;
            for (int j = 1; j < i; j++) {
//                dp[i] = Math.max(dp[j] + arr[I], dp[i]);
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        result[i] = j;
                }
            }
//            maxSize = Math.max(maxSize, dp[i]); // 최장길이 갱신
            if (maxSize < dp[i]) {
                maxSize = dp[i];
                head = i;
            }
        }

        System.out.println(maxSize);

        Stack<Integer> reversed = new Stack<>();
        while(head != 0) {
            reversed.push(arr[head]);
            head = result[head];
        }

        while(!reversed.isEmpty()) {
            System.out.print(reversed.pop() + " ");
        }
    }
}