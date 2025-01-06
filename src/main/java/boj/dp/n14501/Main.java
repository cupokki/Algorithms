package boj.dp.n14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14501
 * 퇴사
 *
 * 01냅색? 냅색은 무게제한이 있지만 이 문제는 무게 제한이 없다.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //남은 날
        int[] period = new int[N + 1];
        int[] fee = new int[N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            period[i] = Integer.parseInt(st.nextToken());
            fee[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][];

        for (int i = 1; i <= N; i++) { // i일 까지 고려한다.
            for (int j = 1; i <= N; i++) {

                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

//                    dp[i][j] = Math.max(dp[i - 1][j], )// 위엣걸 그냥 가녀오냐?

            }
        }

        System.out.println(dp[N][N]);
    }


}
