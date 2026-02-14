package programmers.n42898;

public class Solution {
    /*
    격자에 웅덩이가 생겼다. 웅덩이를 피해 학교에 간다.
    최단 경로의 개수를 MOD로 나누어 구하라.
    집과 학교는 각각 좌상단 우하단에 위치함
    이동은 아래 또는 오른쪽으로만

    모든 이동경로가 최단경로이다.

    */
    static final int MOD = 1_000_000_007;
    public static int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        for (int[] pos : puddles){
            dp[pos[1]][pos[0]] = -1;
        }

        dp[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            if (dp[i][1] == -1) continue;
            dp[i][1] = dp[i - 1][1];
        }
        for (int i = 2; i <= m; i++) {
            if (dp[1][i] == -1) continue;
            dp[1][i] = dp[1][i - 1];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                if(dp[i][j] == -1) continue;
                int a = 0, b = 0;
                if (dp[i - 1][j] != -1){
                    a = dp[i - 1][j];
                }
                if (dp[i][j - 1] != -1) {
                    b = dp[i][j - 1];
                }
                dp[i][j] = (a + b) % MOD;
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2,2}}));
//        System.out.println(solution(4, 3, new int[][]{{2,1}}));
    }
}
