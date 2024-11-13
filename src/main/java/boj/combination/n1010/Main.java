package boj.combination.n1010;
/*
   강을 기준으로 양쪽 강변에 사이트 들이 있다.
   서쪽에는 n개, 동쪽에는 m개의 사이트존재(n<=m)
   양쪽 사이트를 이어 강을 연결하려 한다.
   한 사이트는 한개의 다리만 연결 할 수 있다.
   n개의 다리를 짓는 경우의 수를 구하라.
   (다리는 서로 겹칠 수 없다.)
    (0 < N ≤ M < 30)
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        //result = C(m, n);
        // n! / (n-r)! * r!
        //N, M (0 < N ≤ M < 30)
        // 큰 수를 다루므로 팩토리얼로는 해결하지 못한다.
        // 파스칼의 삼각형을 이용한다.

        int[][] dp = new int[30][30];
        //dp 초기화
        for (int i = 0; i < 30; i++) {
            dp[i][0] = 1; // 왼쪽벽
            dp[i][i] = 1; // 대각
        }
        for (int i = 1; i < 30; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        for(int i = 1; i < 30; i++){
            for(int j = 1; j < i; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            bw.write(dp[m][n]+ "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
