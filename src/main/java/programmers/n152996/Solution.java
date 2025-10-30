package programmers.n152996;

import java.util.*;

public class Solution {
    /*
    시소는 축에서 2,3,4 거리에 좌석이 있다.
    양쪽에 두명씩타서 수평을 이루게 하는 경우를 찾는다.
    w1 * l1 = w2 * l2
    weights가 주어질때
    몇개의 케이스가 존재하는가.

    10만개의 몸무개

    n개중 2개를 뽑는 조합에서 조건을 만족하는 경우를 찾는다.
     */
    public static long solution(int[] weights) {
        Map<Integer, Integer> map = new HashMap<>();
        // 초기화
        for (int w : weights) {
            map.merge(w, 1, Integer::sum);
        }

        long cnt = 0;
//        double[] ratio = {3.0/2, 4.0/2, 4.0/3}; // 같은것 미포함
        int[][] ratio = {{3, 2}, {4, 2}, {4, 3}};
        for (int w : map.keySet()) {
            int cntW = map.get(w);
            if (cntW > 1) { // 예외발생
                cnt += (long) cntW * ((long)cntW - 1) / 2; // (C(n, 2))
            }
            for (int[] r : ratio) {
                if ((w * r[0]) % r[1] != 0) continue;
                int target = (w * r[0]) / r[1];
                if (w < target && map.containsKey(target)) { // 중복방지
                    cnt += (long) map.get(target) * (long) cntW;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{100,180,360,100,270}));
//        System.out.println(solution(new int[]{100,200,300,300}));
    }
}
