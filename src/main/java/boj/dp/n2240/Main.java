package boj.dp.n2240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2240
 * 자두나무
 *
 * 매 초마다 두개의 자두 나무중 하나에서 자두가 떨어진다.
 * 떨어지는 순간에 나무 아래 있으면 자두를 얻는다.
 * 자두는 t초간 떨어진다.
 * 사람은 최대 w회 움직인다.
 *  - 안줌직여도 된다?
 *  - 움직임에 소요되는 시간은 없다.
 * 사람의 초기 위치는 1이다.
 * 최대로 받을 수 있는 자두의 수는?
 *
 */
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int T = Integer.parseInt(st.nextToken());
//        int W = Integer.parseInt(st.nextToken());
//
//        int[] plum = new int[T + 1];
//
//        for (int i = 1; i <= T; i++) {
//            plum[i] = Integer.parseInt(br.readLine());
//        }
//
//        int[][] dp = new int[T + 1][W + 1];
//        int pos = 0;
//        for (int i = 1; i <= T; i++) {
//            for (int w = 0; w <= W; w++) {
//
//                pos = (w % 2) + 1;
//
//                if (pos == plum[i]) {
//                    dp[i][w] = dp[i - 1][w] + 1;
//                } else {
//                    dp[i][w] = dp[i - 1][w];
//                }
//
//                if(w > 0) {
//                    dp[i][w] = Math.max(dp[i][w], dp[i -1][w - 1] + (plum[i] == pos ? 1 : 0));
//                }
//            }
//        }
//
//        // 결과 출력
//        int result = 0;
//        for (int w = 0; w <= W; w++) {
//            result = Math.max(result, dp[T][w]);
//        }
//        System.out.println(result);
//    }
//}
public class Main{
    public static void main(String[] args) {

    }
}