package boj.dp.n9485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스티커
 * https://www.acmicpc.net/problem/9465
 *
 *  스티커 2n개를 구매. 스티커는 점수(0~100)가 적혀있다.
 *  스티커는 N열이 2행씩 배치되어 입력으로 주어진다.
 *  한 스티커를 떼면 변을 공유하는 스티커는 찢어져서 사용할 수 없다.
 *  스티커를 떼어 얻을 수 있는 점수의 최댓값
 *
 *  i번 째 스티커를 1번행을 선택하면 i+1스티커는 2번행을 선택해야한다.
 *  또는! 선택하지 않아야 최댓값이 될 수도 있다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t <T; t++) {
            int N = Integer.parseInt(br.readLine()); // n(1<= n <= 100,000)
            int[][] stickers = new int[2][N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                stickers[0][i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                stickers[1][i] = Integer.parseInt(st.nextToken());
            }

            long[][] dp = new long[2][N + 1];

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];
            // 이중첩을 할 필요x i-1, i-2 보다 더 멀리 볼 필요없음(그럼 최댓값일 수 없음)
            // i열 스티커 중
            //      1을 선택할지
            //      2를 선택할 지

            for (int i = 2; i <= N; i++) { // i열 까지 스티커를 고려했을때
                dp[0][i] = dp[0][i - 1];
                dp[1][i] = dp[1][i - 1];

                dp[0][i] = Math.max(dp[0][i], dp[1][i - 1] + stickers[0][i]);
                dp[0][i] = Math.max(dp[0][i], dp[0][i - 2] + stickers[0][i]);
                dp[1][i] = Math.max(dp[1][i], dp[0][i - 1] + stickers[1][i]);
                dp[1][i] = Math.max(dp[1][i], dp[1][i - 2] + stickers[1][i]);


            }
            System.out.println(Math.max(dp[0][N], dp[1][N]));
        }
    }
}
