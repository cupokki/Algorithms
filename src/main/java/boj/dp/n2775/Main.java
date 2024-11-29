package boj.dp.n2775;

import java.io.*;
import java.util.stream.IntStream;

/**
 * https://www.acmicpc.net/problem/2775
 * 부녀회장이 될테야
 * 0층부터 k층 까지, 각층에 1호부터 n호 까지 존재하는 아파트
 * a층 b호 집에 a-1층의 1호부터 b호까지 사람들의 수의 합만큼 살고있다.
 * 0층 i호는 i명이 산다.
 * k층 n호는 몇명이 살고 있는가.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            int N = Integer.parseInt(br.readLine());

            int[][] dp = new int[K + 1][N + 1];

            dp[0] = IntStream.range(0, N + 1).toArray(); //
            for(int i = 0; i <= K; i++)
                dp[i][1] = 1;

            for(int a = 1; a <= K; a++) {
                for(int b = 2; b <= N; b++) {
                    dp[a][b] = dp[a - 1][b] + dp[a][b - 1];
                }
            }

            bw.write(dp[K][N] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}