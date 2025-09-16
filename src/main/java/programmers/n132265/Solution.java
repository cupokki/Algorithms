package programmers.n132265;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static int solution(int[] topping) {
        int len = topping.length;
        int answer = 0;
        Queue<Integer> left = new LinkedList<>();
        Queue<Integer> right = new LinkedList<>(Arrays.stream(topping).boxed().collect(Collectors.toList()));
        for (int c = 0; c < len; c++) {
            left.offer(right.poll());
            int lSize = (int) left.stream().distinct().count();
            int rSize = (int) right.stream().distinct().count();
            if (lSize == rSize) {
                answer++;
            }

        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 1, 3, 1, 4, 1, 2}));
        System.out.println(solution(new int[]{1, 2, 3, 1, 4}));
    }
}
