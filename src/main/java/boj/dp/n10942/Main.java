package boj.dp.n10942;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 팰린드롬?
 * https://www.acmicpc.net/problem/10942
 * 0.5초, 256메가
 *  A, B 두사람이 팰린드롬 놀이를한다.
 *  B는 N개의 자연수를 적는다. A는 M번의 질문을 한다. (1 ≤ N ≤ 2,000)
 *  각 질문은 두 정수 S와 E(1 ≤ S ≤ E ≤ N)
 *  S번째 수 부터 E번째 까지 수가 팰린드롬을 이루는지 물어본다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++ ) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] starts = new int[M + 1];
        int[] ends = new int[M + 1];

        for (int i = 1; i <= M; i++ ){
            st = new StringTokenizer(br.readLine());
            starts[i] = Integer.parseInt(st.nextToken());
            ends[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N + 1][N + 1];

        // 기저조건
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
            if (i < N && arr[i] == arr[i + 1])
            dp[i][i + 1] = true;
        }

        for (int s = N; s >= 1; s--) { // start
            for (int e = s + 2; e <= N; e++) { // 길이 3이상
//                    int len = e - s + 1;
//                    if (len >= 3 && arr[s] == arr[e])// 길이 3이상
                    dp[s][e] = dp[s + 1][e - 1] && arr[s] == arr[e];
            }
        }

        for (int i = 1; i <= M; i++) {
            bw.write(dp[starts[i]][ends[i]] ? "1\n" : "0\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
