package programmers.n1832;

import com.sun.jdi.JDIPermission;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    왼쪽위에서 오른쪽 아래로 가는 가능한 경로의 수를 출력한다.
    맵은 한변이 최대 자연수 500이하

    0은 자유
    1은 막힘
    2는 직진만

    자동차는 아래, 오른쪽으로만 움직인다.

    그래프 탐색하기엔 노드가 너무 많다.

    dp인가?

     */
    static int MOD = 20170805;
    public static int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m][n];

        // 기저조건
        for (int i = 0; i < m; i++) {
            if (cityMap[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (cityMap[0][i] == 1) break;
            dp[0][i] = 1;
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] == 1) continue;

                dp[i][j] = dp[i][j] + dp[i - 1][j] + dp[i][j - 1];
                if (cityMap[i - 1][j] == 2) dp[i][j] -= 1;
                if (cityMap[i][j - 1] == 2) dp[i][j] -= 1;
                dp[i][j] %= MOD;
            }
        }

        return dp[m - 1][n - 1];
    }

//    static int[] dr = {0, 1};
//    static int[] dc = {1, 0};
//    public static int solution(int m, int n, int[][] cityMap) {
//        int answer = 0;
//
//        Queue<int[]> q = new LinkedList<>();
//        q.offer(new int[]{0, 0, 0}); // 오른쪽
//        while (!q.isEmpty()) {
//            int[] state = q.poll();
//            int r = state[0], c = state[1];
//            int from = state[2];
//
//            if (r < 0 || c < 0 || r >= m || c >= n) continue;
//
//            if (r == m - 1 && c == n - 1) {
//                answer = (answer + 1) % MOD;
//                continue;
//            }
//
//            if (cityMap[r][c] == 1) continue;
//
//
//            if (cityMap[r][c] == 2) { // 직진만 가능
//                q.offer(new int[]{r + dr[from], c + dc[from], from});
//            } else {
//                q.offer(new int[]{r + dr[0], c + dc[0], 0});
//                q.offer(new int[]{r + dr[1], c + dc[1], 1});
//            }
//        }
//
//        return answer;
//    }

    public static void main(String[] args) {
//        System.out.println(solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(solution(3, 6, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));

    }
}
