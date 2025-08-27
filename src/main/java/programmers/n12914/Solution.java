package programmers.n12914;

import java.util.Arrays;

public class Solution {
    /*
    멀리뛰기
    한번에 1칸 또는 2칸을 뛰어 n까지 도달하는 방법의 수를 구하여라
     */
    static long[] memo;
    static final int MOD = 1_234_567;
    static long solution(int n) {

        memo = new long[n + 1];
        Arrays.fill(memo, -1);

        return jump(n);
    }

    static long jump(int to) {
        if (to <= 1) {
            return 1;
        }

        if (memo[to] != -1) {
            return memo[to];
        }
        memo[to] = (jump(to - 2) + jump(to - 1)) % MOD;
        return memo[to];
    }

    static public void main(String[] args) {
        System.out.println(solution(4));
        System.out.println(solution(3));
        System.out.println(solution(2000));

    }
}
