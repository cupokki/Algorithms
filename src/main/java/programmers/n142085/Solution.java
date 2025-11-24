package programmers.n142085;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    /*
    디펜스게임
    n명의 병사로 순서대로 등장하는 enemy을 막는다.
    - 매회 enemy[i]만큼의 병사를 소모하여 막는다.
    - k번의 "무적권"을 특정 회차에 사용해 병사소모없이 막는다.
    무적권을 적절히 사용하여 최대한 많은 라운드를 진행하라.(최대 라운드 출력)

    1. k의 무적권을 쓰냐 안쓰냐를 모든 케이스에 적용하기(dfs)
        -> enemy의 최대길이는 백만이므로x

     */
    public static int solution(int n, int k, int[] enemy) {
        int answer = 0;

        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // 역숙

        int steps = 0;
        for (; steps < enemy.length; steps++) {
            if (n == 0 && k == 0) {
                break;
            }
            if (n >= enemy[steps]) {
                pq.offer(enemy[steps]);
                sum += enemy[steps];
                n -= enemy[steps];
            } else {
                int temp = 0;;
                if (!pq.isEmpty()) {
                    temp = pq.poll();
                }
                sum -= temp;
                n += temp;
                k--;
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        System.out.println(solution(7, 3, new int[]{4, 2, 4, 5, 3, 3, 1}));
        System.out.println(solution(2, 4, new int[]{3, 3, 3, 3}));
    }
}
