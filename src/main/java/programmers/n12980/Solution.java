package programmers.n12980;

import java.util.*;

public class Solution {
    static int memo[];

    static int solution(int n) {
        // k칸 앞점프, k만큼 에너지
        // (현재온 거리)*2 위치로 순간이동, 소모없음
        // 처음 위치 0에서 n까지의 최소 에너지 소모량 k?

        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 1;

        return move(n);
    }

    static int move(int n) {
        if (n == 0) {
            return memo[0];
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int temp = move(n - 1) + 1;

        if (n % 2 == 0) {
            temp = move(n / 2);
        }
        memo[n] = temp;
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(6));
        System.out.println(solution(5000));
    }
}
