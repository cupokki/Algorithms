package programmers.n389479;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    m명 늘때마다 서버 한대씩 추가한다.

    증설한 서버는 k시간 동안 운영하고 그이후 반납합
    시간대별 이용자 수가 주어지면, 하루동안 몇번의 서버 증설이 이루어지는지 구하라.
     */
    public static int solution(int[] players, int m, int k) {
        int answer = 0;

        Queue<Integer> pq = new LinkedList<>();
        int scale = 0;
        for (int t = 0; t < players.length; t++) {
            if (players[t] >= scale * m) {
                int n = players[t] / m - scale;
                for (int i = 0; i < n; i++) {
                    answer++;
                    scale++;
                    pq.offer(t + k);
                }
            }
            while(!pq.isEmpty() && pq.peek() <= t) {
                scale--;
                pq.poll();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(
//                solution(new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5},
//                3, 5)
//        );
//
//        System.out.println(
//                solution(new int[]{0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0},
//                        5, 1)
//        );

        System.out.println(
                solution(new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        1, 1)
        );
    }
}
