package programmers.n12907;

public class Solution {
    /*
    거스름돈을 거를러줄 경우의 수를 구한다.
    1,1,1,1,1
    2,2,1
    5
    1,1,1,2

    처음 순회후 코인을 고려했는데, 이러면, 순열이된다.
    ex) 3을 만들때, 1,1,1/ 2,1/ 1,2 => 3가지. 조합으론 2가지.
    코인을 먼저 순회하면 순열이 아닌 조합이 된다.
     */
    public static int solution(int n, int[] money) {

        int[] dp = new int[n + 1];
        int m = money.length;

        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                if (j < money[i]) continue;
                dp[j] = (dp[j] + dp[j - money[i]]) % 1_000_000_007;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{1, 2, 5})); // 4
    }
}
