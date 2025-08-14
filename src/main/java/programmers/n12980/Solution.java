package programmers.n12980;

import java.util.*;

public class Solution {
    static int memo[];
    static int solution(int n) {
        // k칸 앞점프, k만큼 에너지
        // (현재온 거리)*2 위치로 순간이동, 소모없음
        // 처음 위치 0에서 n까지의 최소 에너지 소모량 k?

        memo = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            memo[i] = i;
        }

        return Math.min(move(n - 1) + 1, n != 0 ? move(n / 2) : Integer.MAX_VALUE);
    }

    static int move(int n) {
        if (n == 0) {
            return memo[1];
        }
        memo[n] = Math.min(move(n - 1) + 1, n != 0 ? move(n / 2) : Integer.MAX_VALUE);
        return memo[n];
    }
    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(6));
        System.out.println(solution(5000));
    }
}
