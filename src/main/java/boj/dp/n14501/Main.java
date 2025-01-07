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
 * LIS 응용 문제이다. : 최대 길이 수열을 추출하는 문제
 *  아니다. 문제가 원하는 것은 최대 요금이기 때문에 별도 비교 로직이 필요하다.
 * dp 테이블에 들어가야하는 것은 무엇인가.
 *  i일까지 고려했을때 최대수익을 기록한다.
 *
 *  dp[N]까지 고려하여 최대수익을 계산하여 출력한다.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //남은 날 N
        int[] period = new int[N + 1];          //Ti
        int[] fee = new int[N + 1];             //Pi

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            period[i] = Integer.parseInt(st.nextToken());
            fee[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) { // i일 까지 고려한다.
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.maxfee[j]
                if (dp[i][j] < period[j]) { // 남은 기간이 지금보는 것의 기간보다 작다면 비교
                    dp[i][j] = Math.max(dp[i - 1][j] + fee[j - 1], dp[i][j]);
                } else {
                    //TODO : dp[i][i - period[j]]
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        System.out.println(dp[N][N]);
    }


}
