package boj.dp.n11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 카드 구매하기
 *
 * https://www.acmicpc.net/problem/11052
 * 등급을 나타내는 색 8가지가 있다.
 * 카드팩을 구매할 때, 1개부터 N개가 들어있는 카드팩을 구매 할 수 있다.
 * 카드수가 적어도 비싼 카드팩이면 좋은 카드가 들어있을거라는 미신
 * 최대한 돈을 많이 써서 정확히 카드 N개를 구매하려한다.
 * 카드 i개 팩의 가격은 P_i원
 *
 * 비싸고 카드가 많이 든것을 골라서 N개를 맞축자
 * 비싼 카드팩을 먼저 구매하라.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] price = new int[N + 1];
        for (int i = 1; i <= N; i++ ) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) { // i번째 카드팩까지 고려할 때
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i - j] + price[j], dp[i]) ;
            }
        }

        // 최댓값 출력
        System.out.println(dp[N]);
    }
}
