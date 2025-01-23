package boj.dp.n2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://acmicpc.net/problem/2293
 * 동전 1
 *
 * 액면가가 다른 n가지 동전이 있다. 동전을 사용하여, 그 가치의 합이 k원이 되게하는 경우의 수를 구하라.
 * 사용한 동전구성이 같아도 순서가 다르면 다른 경우이다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N + 1];
        for(int i = 1; i <= N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }



        // 최대 횟수를 구하는 문제이다.
        int[] dp = new int[K + 1];

        dp[0] = 1; // 아무것도 배치안하는것도 하나의 경우

        for (int i = 1; i <= N; i++) {// i번재 동전까지 고려했을때
            for (int k = 1; k <= K; k++){ // k원을 만드는 경우의 수
                if(k >= coins[i])
                    dp[k] += dp[k - coins[i]];
            }
        }

        System.out.println(dp[K]);


    }
}
