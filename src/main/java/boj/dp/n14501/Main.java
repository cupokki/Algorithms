package boj.dp.n14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1]; // 소요일
        int[] P = new int[N + 1]; // 비용

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2];

        for (int i = N; i >= 0; i--) {
            if(i + T[i] <= N + 1) { // 오늘로부터 T[i]일이 지난 시점이 퇴사 전이면
                dp[i] = Math.max(dp[i + T[i]] + P[i], dp[i + 1]);
            } else { // 오늘 예약된 상담을 할 수 없다면
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[0]);
    }
}