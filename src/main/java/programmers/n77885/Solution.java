package programmers.n77885;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 양의 정수 x에 대한 f(x)는 x보다 크고 x와 비트가 1개 또는 2개 다른 제일 작은 수
    // 10^15
    // 생각해보니 자릿수 차이는 커봐야 한 자리만큼 더 클 것이다.
    // 우선 1 더한 것의 2진수를 구한다.


    static Map<Long,Long> memo;
    public static long[] solution(long[] numbers) {
        memo = new HashMap<>();
        return Arrays.stream(numbers).map(Solution::func).toArray();
    }
    static long func(long num) {
        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        long temp = num + 1;

        // 비트카운트가 같은수가 아닌이산 0일 순 없음
        while(Long.bitCount(num ^ temp) > 2) {
            temp++;
        }

        memo.put(num, temp);
        return temp;
    }

    public static void main(String[] args) {
//        var a = solution(new long[]{2, 7});
        var a = solution(new long[]{7});
        System.out.println();
    }
}
