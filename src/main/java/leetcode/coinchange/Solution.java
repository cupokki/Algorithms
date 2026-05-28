package leetcode.coinchange;

import java.util.Arrays;

public class Solution {
    /*
    액면가가 다른 동전 배열 coins
    총액 amount

    가장 적은 동전의 수로 amount를 만든다.
    만약 어떤 조합으로도 총액을 만들지못하면 -1

    동전은 무한이 있다고 가정
     */
    static final int INF = 10_001;
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] == INF ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{1}, 0));
    }
}
