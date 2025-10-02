package programmers.n154538;

import java.util.Arrays;

public class Solution {

    // 3가지 연산을 사용하여 x를 y로 변환한다.
    // 1. x + n;
    // 2. x * 2;
    // 3. x * 3;
    // 변환하기 위한 연산의 최소횟수를 출력하고 x를 y로 만들 수 없다면 -1;
    public static int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];
        Arrays.fill(dp, -1);
        dp[x] = 0;
        for (int i = x + 1; i <= y; i++) {
            int a = i <= n || dp[i - n] == -1 ? Integer.MAX_VALUE : dp[i - n];
            int b = i % 2 != 0 || dp[i / 2] == -1 ? Integer.MAX_VALUE : dp[i / 2];
            int c = i % 3 != 0 || dp[i / 3] == -1 ? Integer.MAX_VALUE : dp[i / 3];
            if (a == Integer.MAX_VALUE && a == b && b == c) {
                continue;
            }
            dp[i] = Math.min(a, Math.min(b, c)) + 1;
        }

        return dp[y];
    }

    public static void main(String[] args) {
        System.out.println(solution(10, 40, 5));
        System.out.println(solution(10, 40, 30));
        System.out.println(solution(2, 5, 4));
    }

}
