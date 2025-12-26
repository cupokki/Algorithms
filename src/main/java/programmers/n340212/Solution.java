package programmers.n340212;

import java.util.*;

class Solution {
    /*
    순서대로, n개 퍼즐 해결
    각각 난이도와 소요시간 존재
    숙련도에 따라 퍼즐 풀때 틀리는 횟수 바뀜
    내 숙련도보다 쉽거나 같다면 소요시간만큼
    
    어렵다면 diff - level번 틀림. 틀릴때마다 소요시간만큼 더하고 이전 퍼즐 소요시간이 더해진다.(난이도 무관)
    제한시간 내에 모두 풀기위한 최소 레벨을 출력하라.


    1, 2, 3, .... (n - 3), (n - 2), (n - 1), n
    */
    static int len;
    static int[] Diffs;
    static int[] Times;
    static long Limit;
    static int level;
    public static int solution(int[] diffs, int[] times, long limit) {
        len = diffs.length;
        level = Arrays.stream(diffs).max().getAsInt();
        Diffs = diffs;
        Times = times;
        Limit = limit;
        search(1, level); // diffs[0]은 항상 1임, 최소도 level도 1임

        return level;
    }
    static void search(int l, int r) {
        int m = (l + r) / 2; // current level;

        long t = Times[0]; //
        for (int i = 1; i < len; i++) {
            if (Diffs[i] > m) {
                t += (Times[i] + Times[i - 1]) * (Diffs[i] - m) + Times[i];
            } else {
                t += Times[i];
            }
        }
        // 최솟값이 보장안되는데? 찾아도 멈추지 않고 최대한 진행한다.
        if (t <= Limit) {
            level = Math.min(m, level);
        }

        if (l == m || m == r) {
            return;
        }

        if (t < Limit) { // 초과시에 레벨을 높여 다시 계산한다.
            search(l, m);
        } else {
//            level = Math.min(m, level);
            search(m + 1, r);
        }
    }

     public static void main(String[] args) {
         System.out.println(solution(new int[]{1, 5, 3}, new int[]{2, 4, 7}, 30));
         System.out.println(solution(new int[]{1, 4, 4, 2}, new int[]{6, 3, 8, 2}, 59));
         System.out.println(solution(new int[]{1, 328, 467, 209, 54}, new int[]{2, 7, 1, 4, 3}, 1723));
         System.out.println(solution(new int[]{1, 99999, 100000, 99995}, new int[]{9999, 9001, 9999, 9001}, 3456789012L));
     }
}