package programmers.n77885;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 양의 정수 x에 대한 f(x)는 x보다 크고 x와 비트가 1개 또는 2개 다른 제일 작은 수
    // 10^15
    // 생각해보니 자릿수 차이는 커봐야 한 자리만큼 더 클 것이다.
    // 우선 1 더한 것의 2진수를 구한다.
    /*
    0010 -> 0011 // 짝수는 무조건 1의자리 한비트 true. 즉, + 1;
    '01'11 -> '10'11
      10111 ->   11011
    1010111 -> 10110111
    홀수라면 가장 오른쪽 "01"을 "10"으로 치환한다.
     */

    static Map<Long, Long> memo;

    public static long[] solution(long[] numbers) {
        memo = new HashMap<>();
        return Arrays.stream(numbers).map(Solution::func).toArray();
    }

    static long func(long num) {
        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        // 홀수
        if (num % 2 == 0) { // == num & 1
            return num + 1;
        }
        // 짝수
        long bit = (~num) & (num + 1); // 가장 오른쪽 0인 비트
        long result = num + bit; // 가장 오른쪽 0 비트의 왼쪽을 1로
        result -= bit >> 1; // 오른쪽 0비트 오른족의 비트만큼 빼서 "10"완성
        memo.put(num, result);
        return result;
        /*
        // 0 찾기
        (~111) & (111 + 1)
            = 111...1000 & 000...1000
            = 0000...1000

         result = (0111 + 1000) - (1000 >> 1)
            = (0111 + 1000) - 0100
            = 1111 + 0100
            = 1011

         */


//        String bin = "0" + Long.toBinaryString(num);
//        int idx = bin.lastIndexOf("01");
//        bin = bin.substring(0, idx) + "10" + bin.substring(idx + 2);
//        long result = Long.parseLong(bin, 2);
//        memo.put(num, result);
//        return result;


    }

    public static void main(String[] args) {
        var a = solution(new long[]{2, 7});
//        var a = solution(new long[]{7});
        System.out.println();
    }
}
