package programmers.n12971;

public class Solution {
    /*
    원형으로 스티커가 연결되어있다.
    스티커를 뜯으면 양쪽은 사용 할 수 없다.
    스티커에 적힌 수의 합의 최댓값을 구하라.

    배열의 길이는 10만 이하
    각 칸에 적힌 수는 100이하의 자연수

    원형에 대한 특징? 시작과 끝에 문제


    두개의 요소를 고려한다. 첫번쨰것을 고르는가 두번째 것을 고르는가.

     */
    public static int solution(int[] sticker) {
        int n = sticker.length;
        int[][] dp = new int[2][n + 1];

        // 첫 번째 요소를 선택한다.
        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(
                    dp[0][(n + i - 2) % n] + sticker[i - 1],
                    dp[1][(n + i - 3) % n] + sticker[i - 1]
            );
        }

        // 첫 번쨰 요소를 선택하지 않는다.
        for (int i = 2; i <= n; i++) {
            dp[1][i] = Math.max(
                    dp[1][(n + i - 2) % n] + sticker[i - 1],
                    dp[1][(n + i - 3) % n] + sticker[i - 1]
            );
        }

        return Math.max(dp[0][n - 1], dp[1][n]);
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10}));
        System.out.println(solution(new int[]{1, 3, 2, 5, 4}));
    }
}
