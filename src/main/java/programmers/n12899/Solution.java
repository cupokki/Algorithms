package programmers.n12899;

import java.util.Arrays;

public class Solution {
    // 124만을 사용하는 숫자체계. 3진법과 같으나 표기하는 수가 1, 2, 4만 사용
    // 10진수가 주어질때 124나라 숫자로 변환하라
    // 입력은 자연수로만 주어지며 5천만 이하이다.
    /*
    1, 2, 4,

    11, 12, 14, (i - 3) (i - 4) (i - 5)
    21, 22, 24, (i - 5) (i - 6) (i - 7)
    41, 42, 44, (i - 7) (i - 8) (i - 9)

    111, 112, 114 (i - 9) (i - 10) (i - 11)
    121, 122, 124 (i - 11) ...
    141, 142, 144 (i - 13) ...

    211, 212, 214, (i - 15) ...
    221, 222, 224,
    241, 242, 244,

    411, 412, 414,
    421, 422, 424,
    441, 442, 444,

    1111, 1112, 1113


    1111, 1112, 1114, (i - 9)
    1121,
     */
    public static String solution(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 4;
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 2;
        if (n >= 3) dp[3] = 4;
        int idx = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - idx] * 10 + dp[i % 3];
            if (i % 3 != 0) {
                idx++;
            }
        }

        String answer = String.valueOf(dp[n]);
        return answer;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(solution(i));
        }
//        System.out.println(solution(10));
    }
}
