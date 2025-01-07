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
 * i일로 부터 Ti일 을 갱신한다.
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

        int[] dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]); // 이전 날까지의 최대 수익(오늘 상담이 잡혀 선택을 못하는 경우)
            if (i + period[i] <= N + 1) { // i + period가 N일 이하라면 (N일전까지 끝낼 수 있다면) 마지막날에 하루가 걸리므로 + 1
//                dp[i + period[i]] = dp[i] + fee[i];
                dp[i + period[i]] = Math.max(dp[i] + fee[i], dp[i + period[i]]); // i일에 상담을 한 것과 안한 것 크기 비교
            }
        }
        int maxFee = Math.max(dp[N], dp[N + 1]);
        System.out.println(maxFee);
    }

}
