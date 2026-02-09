package programmers.n43105;

import javax.management.monitor.StringMonitor;

public class Solution {
    /*
    삼각형 형태로 정수가 적혀있다.
    꼭대기에서 바닥까지 수를 이어가며 바닥으로 향한다.
    바닥으로 향하는 경로의 수를 다 더한 합이 가장 큰 경우를 구하라.
     */
//    public static int solution(int[][] triangle) {
//        int n = triangle.length;
//        int[][] dp = new int[n][n];
//
//        dp[0][0] = triangle[0][0];
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = dp[i - 1][0] + triangle[i][0];
//            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
//            for (int j = 1; j < i; j++) {
//                dp[i][j] = Math.max(
//                        dp[i - 1][j - 1] + triangle[i][j],
//                        dp[i - 1][j] + triangle[i][j]
//                );
//            }
//        }
//        int answer = Arrays.stream(dp[n - 1]).max().getAsInt();
//        return answer;
//    }

    public static int solution(int[][] triangle) {
        int n = triangle.length;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(
                        triangle[i + 1][j],
                        triangle[i + 1][j + 1]
                );
            }
        }

        return triangle[0][0];
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new int[][]{
                        {7},
                        {3, 8},
                        {8, 1, 0},
                        {2, 7, 4, 4},
                        {4, 5, 2, 6, 5}
                }
        ));
    }
}
