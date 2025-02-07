package boj.dp.n9657;

import java.util.Scanner;

/**
 * 돌 게임 3
 * https://www.acmicpc.net/problem/9657
 *
 * N개의 돌을 차례마다 1, 3, 4개를 가저갈 수 있다.
 * A,B중에 A가 먼저 돌을 가저가고 마지막에 돌을 가저가는 사람이 이긴다.
 * 모든 선택은 자신이 승리하기 위한 수를 선택해야한다고한다면
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        //상근이가 이긴다. true;
        boolean[] dp = new boolean[N + 1];

        dp[1] = true;
        if(N >= 2) dp[2] = false;
        if(N >= 3) dp[3] = true;
        if(N >= 4) dp[4] = true; //AAAA
//        dp[5] = true; // dp[5 - 1](true) || dp[5 - 3](false) || dp[5 - 4](true);

        for (int i = 5; i <= N; i++) {
            //이전 조합이 false면 이번 차례는 true
            if (!dp[i - 1] || !dp[i - 3]|| !dp[i - 4])
                dp[i] = true;
            else {
                dp[i] = false;
            }
        }


        System.out.println(dp[N]?"SK":"CY");
    }
}
