package programmers.n86053;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    /*

    왕국에 하나이상의 도시
    새도시를 짓는다.
    도시 건설을 위해 금 a개와 은 b개가 필요하다.
    각 도시들이 보유한 금과 은이 주어진다.
    각 도시들은 트럭이 한대 있다.
    트럭은 건설장소와 i번 도시만 왕복한다.
    트럭의 편도 이동시간t_i, 적재량 w_i가 주어진다.

    새 도시를 건설하기 위한 최소시간을 출력한다.

    금과 은은 각각 10억개 이하의 자연수
    도시는 최대 10만개 있다.

    - 계속 광물의 양이 변동된다.
    - 광물을 가져가는 비율도 고려되어야한다.
    */
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = 0; // time

        int n = g.length;

        long left = 0;
        long right = 100_000L * (a + b); // 최대 시간: t_i의 최대시간 * (a + b)

        while (right <= left) {
            long mid = left + (left + right) / 2;

            long temp = func();
            if (mid < temp) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }


        return answer;
    }

    long func() {

        return 0L;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(10, 10, new int[]{100}, new int[]{100}, new int[]{7}, new int[]{10})); // 50
        System.out.println(sol.solution(90, 500, new int[]{70, 70, 0}, new int[]{0, 0, 500}, new int[]{100, 100, 2}, new int[]{4, 8, 1})); // 499
    }

}