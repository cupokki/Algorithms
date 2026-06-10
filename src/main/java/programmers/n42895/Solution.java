package programmers.n42895;

import java.util.*;

public class Solution {
    /*
    사칙연산(나누기는 나머지를 무시한다)과 "x10 + N"연산까지 5끼리 이어 붙이기로
    5끼리만 가능하다. 괄효 계산후 붙여버리면 안됨
    최대한 적은 N을 사용하여 number를 만드는 최소값을 출력한다.

    최솟값이 8보다 크면 -1을 출력한다.

    number: 32000이하, 만들 수
    N: 9이하 자연수, 사용할 숫자

    계산중에 음수가 되는게 올바른가?
    계산중에 값이 어디까지 허용되야하는가
    => 관점은 이런 계산과정이 최소가 되게 하는것인지가 중요한데
    5로 111을 만들때, 가장 빠른게 555/5 이다.

    */
    public static int solution(int N, int number) {
        memo = new int[32000 + 1];
        Arrays.fill(memo, Integer.MAX_VALUE / 2);
        int answer = INF;
        int term = N;
        int i = 1;
        while (term < memo.length) {
            answer = Math.min(answer, backtrack(N, i, number));
            term = term * 10 + N;
        }
        return answer;
//        return answer > 32000 ? -1 : answer;
    }

    static int[] memo;
    static final int INF = Integer.MAX_VALUE / 2;
    // 반환값: num을 만드는 최소 수
    static int backtrack(int N, int depth, int num ) {
        if (num == 0) {
            return 0;
        }

        if (depth > 8) {
            return INF;
        }

        if (memo[num] != INF) {
            return memo[num];
        }

        int min = INF;

        if (num - N > 0) min = Math.min(min, backtrack(N, depth + 1, num - N) + 1); // N 더하기
        if (num + N <= memo.length) min = Math.min(min, backtrack(N, depth + 1, num + N) + 1); // N 빼기 -> 더해야할 것이 N 만큼 는다.
        if (num + N <= memo.length) min = Math.min(min, backtrack(N, depth + 1, num * N) + 1); // 남은 수를 N으로 곱하기
        if (num - N > 0) min = Math.min(min, backtrack(N, depth + 1, num / N) + 1); // 남은 수를 N으로 나누기

        return memo[num] = min;
    }

     public static void main (String[] args) {
         System.out.println(solution(5, 12)); // 4
         System.out.println(solution(2, 11)); // 3
     }
}