package programmers.n12911;

import java.util.Arrays;

public class Solution {
    static int solution(int n) {
        final int count = getCount(n);
        n++;
        while(getCount(n) != count) {
            n++;
        }
        return n;
    }
    static int getCount(int n) {
        int count = 0;
//        System.out.println(Integer.toBinaryString(n));
        while(n > 1) {
            if(n % 2 == 1) count++;
            n = n /2;
        }
        count += n % 2 == 1 ? 1: 0;
//        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution(78));
        System.out.println(solution(15));
    }
}
