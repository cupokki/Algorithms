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

        int left = 0;
        int right = n * 10_000;
        int t = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2; // mid: t, 최종 정답

            int cnt = len; // 0초에 모든 코어 사용

            for (int core : cores){
                cnt += mid / core;
            }

            if (cnt >= n) {
                t = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int cnt = len;
        for (int core : cores) {
            cnt += (t - 1) / core;
        }

        for (int i = 0; i < len; i++) {
            if (t % cores[i] == 0) {
                cnt++;
                if (cnt == n) {
                    return i + 1;
                }
            }
        }

        return -1;
    }




    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(6, new int[]{1, 2, 3})); // 2
        System.out.println(sol.solution(14, new int[]{1, 2, 3}));
    }
}