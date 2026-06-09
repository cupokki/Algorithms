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

    */
    public static int solution(int N, int number) {
        memo = new int[number + 1];
        Arrays.fill(memo, Integer.MAX_VALUE / 2);
        int answer = backtrack(N, 0, number);
        return answer > 32000 ? -1 : answer;
    }

    static int[] memo;

    // 반환값: num을 만드는 최소 수
    static int backtrack(int N, int depth, int num) {
        if (depth == 9) {
            return Integer.MAX_VALUE / 2; // 답이 될 수 없다.
        }

        if (memo[num] != Integer.MAX_VALUE / 2) {
            return memo[num];
        }

        int min = Integer.MAX_VALUE;

        // num * 10 + N;
        // 이건 특별한 경우만 가능하다 수가 이어진 경우만..
        if (num * 10 + N <= 32000)
            min = Math.min(min, backtrack(N, depth + 1, num * 10 + N) + 1);

        // 5 더하기
        if (num + 5 <= 32000)
            min = Math.min(min, backtrack(N, depth + 1, num + 5) + 1);

        // 5 빼기
        if (num - 5 > 0)
            min = Math.min(min, backtrack(N, depth + 1, num - 5) + 1);

        // 5 곱하기
        if (num * 5 <= 32000)
            min = Math.min(min, backtrack(N, depth + 1, num * 5) + 1);

        // 5 나누기
        if (num / 5 > 0)
            min = Math.min(min, backtrack(N, depth + 1, num / 5) + 1);

        return memo[num] = min;
    }

     public static void main (String[] args) {
         System.out.println(solution(5, 12)); // 4
         System.out.println(solution(2, 11)); //3
     }
}