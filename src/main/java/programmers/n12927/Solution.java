package programmers.n12927;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
    /*
    야근하면 피로도가 쌓인다.
    피로도 = 시작시점에서 남은 일의 작업량을 제곱하여 더한 값
    N시간동안 피로도를 최소화하도록 일한다.
    1시간 동한 1씩 일을 처리함

    n을 works에 적절히 분배하여 원소를 차감한다.

    greedy인가? 큰것 우선으로 줄여나간다.

    합하고, n만큼 빼서 공평하게 len개로 나누면 되는거 아닌가
    아 이러면 n/len 보다 원래 원소 값이 작은경우는 말이 안되게 되네

     */
    public static long solution(int n , int[] works) {
        long answer = 0;
        int len = works.length;

        long sum = Arrays.stream(works).sum();
        if (sum <= n) {
            return 0;
        }

        Arrays.sort(works);
        List<Integer> indexes = new ArrayList<>();
        int cur = -1;
        for (int i = 0; i < len; i++) {
            if (works[i] != cur) {
                indexes.add(i);
                cur = works[i];
            }
        }

        for (int i = indexes.size() - 1; i > 0 ; i--) {
            if (n <= 0) {
                break;
            }

            int idx = indexes.get(i);
            int gap = works[idx];
            if (idx > 0) gap = works[idx] - works[idx - 1];

            for (int j = idx; j < len; j++) {
                if (n < gap) {
                    break;
                }
                works[j] -= gap;
                n -= gap;
            }

        }

        for (int i = 0; i < len; i++) {
            if (n > 0) {
                works[i]--;
                n--;
            }
            answer += (long) works[i] * works[i];
        }
        return answer;

    }

    public static void main(String[] args) {
        System.out.println(solution(11, new int[]{1, 2, 3, 4, 5}));
        System.out.println(solution(4, new int[]{4, 3, 3}));
        System.out.println(solution(1, new int[]{2, 1, 2}));
        System.out.println(solution(3, new int[]{1, 1}));
    }
}
