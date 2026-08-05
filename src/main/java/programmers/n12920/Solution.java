package programmers.n12920;

import java.util.Arrays;

public class Solution {
    /*
        코어수는 [2,10000]
        코어당 작업 처리속도 1만이하
        최대 일의 수는 5만개 이하

        2개이상의 코어가 가능할때는, 앞쪽 코어부터 작업을 수행.
        마지막 작업을 처리하는 코어의 번호를 출력하라.
     그리디아닌가 가장 여유로운 것중에 인덱스가 앞에 있는것을 고른다.
     근데 같은 패턴이 반복될것이다. -> 그건 아니다.
     O(n * 2 len) len 10000 * 50000 = 50억...
     n을 줄이던 len 을 줄이던 해야한다.


    한번의 n으로 해결이 불가능할까?
    수로 예상 가능하지않을까?

    이분탐색?

    */

    public int solution(int n, int[] cores) {
        int answer = 0;

        int len = cores.length;

        if (n <= len) return n; // 주어진 코어내로 종료

        long l = 1;
        long r = 10000 * n;
        long t = 0;

        while(l <= r) {
            long m = (l + r) / 2;
            long cnt = len;

            for (int i = 0; i < len; i++) {
                cnt += m / cores[i];
            }

            if (cnt < n) {
                l = m + 1;
            } else {
                t = m;
                r = m - 1;
            }
        }


        return answer;
    }




    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(6, new int[]{1, 2, 3}));
        System.out.println(sol.solution(14, new int[]{1, 2, 3}));
    }
}