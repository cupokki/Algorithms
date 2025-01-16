package boj.dp.n2302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/2302
 * 극장 좌석
 *
 * N개의 일렬좌석
 * 일반 입장권은 N, N-1 N+1 위치에 앉을 수 있다.
 * vip 입장권은 N위치에만 앉는다.
 * vip는 M명 있다.
 * 나머지 N - M 명의 일반입장권이 있다.
 * vip의 자리가 주어지고 사람들이 앉는 경우의 수를 구하라.
 *  => DP
 *
 *  1명 => 1
 *  2명 => 12            21
 *  3명 => 123, 132       213,
 *  4명 => (1234 1243)   (2134 2143)    (1324)
 *  5명 => (12345 12354)  (12435)
 *         (21345 21354)  (21435)
 *         (13245 13254)
 *  6명 => (123456 123465) (123546)
 *         (124356 12465) (213456 213465) (213546)
 *         (214356 21465) (132456 132465) (132546)
 *  vip를 고려해야한다. vip를 기준으로 문제를 나눠 계산할 수 있다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 1 <= N <= 40
        int M = Integer.parseInt(br.readLine()); // 0 <= M <= N

        List<Integer> list = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < M; i++) {
            int m = Integer.parseInt(br.readLine());
            list.add(m - prev - 1);
            prev = m;
        }
        list.add(N - prev);

        int[] dp = new int[N + 1];
        dp[0] = 1; // 모두 자기위치
        if (N >= 1) dp[1] = 1;
        if (N >= 2) dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int result = 1;
        for (int num : list) {
            result *= dp[num];
        }

        System.out.println(result);
    }
}
