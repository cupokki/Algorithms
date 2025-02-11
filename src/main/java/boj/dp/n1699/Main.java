package boj.dp.n1699;

import java.util.Scanner;

/**
 * 제곱수의 합
 * https://www.acmicpc.net/problem/1699
 *
 * 자연수 N은 작저나 같은 제곱수들의 합으로 나타넬 수 있다.
 * ex) 11 = 3^2 + 1^2 + 1^2 (3항)
 * 다른 방식으로 항이 더 생길 수 있지만 최소 개수를 출력한다.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i; // 기본적으로 i(1의 제곱을 을 i번 더했을 때)로 초기화
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // i번 더한것, 또는 지금까지 최솟값과 비교했을 때, j의 제곱으로 i에서 도달가능하면 dp[i- j * j]에서 1추가한 것이 dp[i]이다.
            }
        }
        System.out.println(dp[N]);
    }
}
