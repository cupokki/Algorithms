package programmers.n118667;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    한번에 프로세스에서 한큐에서 pop하고 다른 큐에 삽입한다.
    프로세스 수행으로 각 큐의 원소 합이 같아지는 최소의 수
    불가능하다면 -1을 반환한다.

    깊이가 정해지지않음 얼마나 걸릴지 모르니까 dfs는 무리
    깊이가... 알수있나? 두 입력의 길이 합만큼 수행하면 불가능한것으로
    12
    34
    ---
    123
    4
    --
    1234
    x
    ---
    234
    1
    ---
    34
    21
    ---
    4
    321
    ---
    x
    4321
    ---
    1
    432
    ---
    x
    1432
    ---
    2
    143
    ---
    23
    14
    ---
    3
    214
    ---
    4
    321


     */
    public static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> qa = new LinkedList<>();
        for (int n : queue1) {
            qa.add(n);
        }
        Queue<Integer> qb = new LinkedList<>();
        for (int n : queue2) {
            qb.add(n);
        }

        int cnt = 0;
        long aSum = qa.stream().mapToInt(i->i).sum();
        long bSum = qb.stream().mapToInt(i->i).sum();

        while (aSum != bSum) {
            if (aSum > bSum) {
                int temp = qa.poll();
                qb.offer(temp);
                aSum -= temp;
                bSum += temp;
            }
            else {
                int temp = qb.poll();
                qa.offer(temp);
                bSum -= temp;
                aSum += temp;
            }
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new int[]{3, 2, 7, 2},
                new int[]{4, 6, 5, 1}
        ));

        System.out.println(solution(
                new int[]{1, 2, 1, 2},
                new int[]{1, 10, 1, 2}
        ));

        System.out.println(solution(
                new int[]{1, 1},
                new int[]{1, 5}
        ));
    }

}
