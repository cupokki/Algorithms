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
    */
    public static int solution(int[] diffs, int[] times, long limit) {
        int level = 1;
        int len = diffs.length;

        long[] prefixSum = new long[len + 1];
        for (int i = 1; i <= len; i++) {
            prefixSum[i] += prefixSum[i - 1] + times[i - 1];
        }

        boolean done = true;
        while(true) {
            long t = times[0];
            done = true;
            for (int i = 1; i < len; i++) {
                int gap = diffs[i] - level;
                if (gap > 0)  {
                    t += (times[i - 1] + times[i]) * gap + times[i];
                } else {
                    t += times[i];
                }

//                if (t > limit || prefixSum[len] - prefixSum[i] > limit - t) { //
                if (t > limit || i != len - 1 && prefixSum[len] - prefixSum[i] > limit - t) { // 앞으로 최소 소요시간보다 남은 시간이 적다면
                    level++;
                    done = false;
                    break;
                }
            }
            if (done) {
                break;
            }
        }
        return level;
    }

     public static void main(String[] args) {
//         System.out.println(solution(new int[]{1, 5, 3}, new int[]{2, 4, 7}, 30));
         System.out.println(solution(new int[]{1, 4, 4, 2}, new int[]{6, 3, 8, 2}, 59));
     }
}