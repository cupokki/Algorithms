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
 *  - 움직임에 소요되는 시간은 없다.
 *  - 안움직여도 된다?
 * 사람의 초기 위치는 1이다.
 * 최대로 받을 수 있는 자두의 수는?
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] plum = new int[T + 1][2];

        int tree1 = 0;
        int tree2 = 0;
        for (int i = 1; i <= T; i++) {
            if(br.readLine().equals("1")) {
                plum[i][0] = 1;
                tree1++ ;
            } else {
                plum[i][1] = 1;
                tree2++;
            }
        }

        // 자두 개수를 담는 dp
//        int[][] dp = new int[t + 1][2];
//        int pos = 0; // 사람의 초기 위치
//        dp[1][0] = tree1;
//        dp[1][1] = tree2;


        int[][] dp = new int[T + 1][W + 1];
        // 그림대로 빼나갈때 최대한으로 남은것을 선택한다.
        // 빼나갈때는 어떤걸 고려해야하냐?
        int[] left = new int[2];
        int[] tree = new int[]{tree1, tree2};
        for (int i = 1; i < T;i++) {

        }


        // 최대 자두 개수 출력
        System.out.println();
    }
}
