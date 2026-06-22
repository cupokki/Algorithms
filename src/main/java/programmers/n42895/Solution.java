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
    static Set<Integer>[] memo;

    public static int solution(int N, int number) {
        memo = new HashSet[9];

        for (int k = 1; k <= 8; k++) {
            Set<Integer> currentSet = getValues(k, N);
            if (currentSet.contains(number)) {
                return k;
            }
        }

        return -1;
    }

    static Set<Integer> getValues(int k, int N) {
        if (memo[k] != null) {
            return memo[k];
        }

        Set<Integer> result = new HashSet<>();

        int continuousNum = 0;
        for (int i = 0; i < k; i++) {
            continuousNum = continuousNum * 10 + N;
        }
        result.add(continuousNum);

        for (int i = 1; i < k; i++) {
            Set<Integer> leftSet = getValues(i, N);
            Set<Integer> rightSet = getValues(k - i, N);

            for (int num1 : leftSet) {
                for (int num2 : rightSet) {
                    result.add(num1 + num2);
                    result.add(num1 - num2);
                    result.add(num1 * num2);
                    if (num2 != 0) {
                        result.add(num1 / num2);
                    }
                }
            }
        }

        memo[k] = result;
        return result;
    }

     public static void main (String[] args) {
//         System.out.println(solution(5, 12)); // 4
//         System.out.println(solution(2, 11)); // 3
         System.out.println(solution(5, 31168)); // -1
     }
}