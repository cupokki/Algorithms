package programmers.n12907;

public class Solution {
    /*
    거스름돈을 거를러줄 경우의 수를 구한다.
     */
    public static int solution(int n, int[] money) {

        int[] dp = new int[n + 1];
        int m = money.length;

        for (int i = 0; i < m; i++) {
            if (n >= money[i]) dp[money[i]] = 1;
        }

        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < money[j]) continue;
                dp[i] *= dp[i - money[j]] % 1_000_000_007;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{1, 2, 5}));
    }
}
