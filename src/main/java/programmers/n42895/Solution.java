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

    TODO: 첫항의 괄호만 고려되므로 다른 방법을 찾아야한다.
    */
    public static int solution(int N, int number) {



//        memo = new int[32000 + 1];
//        Arrays.fill(memo, Integer.MAX_VALUE / 2);

        memo = new HashMap<>();

        int answer = INF;
        int term = N;
        for(int i = 1; i <= 8; i++) {
            answer = Math.min(answer, backtrack(N, number, i, term) + i);
            if (term * 10 + N > 32000 * N) break;
            term = term * 10 + N;
        }
        return answer > 8 ? -1 : answer;
    }

    //    static int[] memo;
    static Map<Integer, Integer> memo;
    static final int INF = Integer.MAX_VALUE / 2;
    // 반환값: num을 만드는 최소 수
    static int backtrack(int N, int number, int depth, int num) {
        if (depth > 8) { // 최대 N 사용시
            return 1;
        }

        if (num == number) {
            return depth;
        }

        if (memo.containsKey(num) && memo.get(num) <= depth) {
            return INF;
        }

        memo.put(num, depth);

        int min = INF;

        min = Math.min(min, backtrack(N, number, depth + 1, num + N)); // N 더하기
        min = Math.min(min, backtrack(N, number, depth + 1, num - N)); // N 빼기 -> 더해야할 것이 N 만큼 는다.
        min = Math.min(min, backtrack(N, number, depth + 1, num * N)); // 남은 수를 N으로 곱하기
        if (num != 0)
            min = Math.min(min, backtrack(N, number, depth + 1, num / N)); // 남은 수를 N으로 나누기

        return min;
    }

     public static void main (String[] args) {
//         System.out.println(solution(5, 12)); // 4
//         System.out.println(solution(2, 11)); // 3
         System.out.println(solution(5, 31168)); // -1
     }
}