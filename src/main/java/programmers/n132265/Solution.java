package programmers.n132265;

import java.util.*;

public class Solution {
    public static int solution(int[] topping) {
        int len = topping.length;
        int answer = 0;

        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        int[] rightCount = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            right.add(topping[i]);
            rightCount[i] = right.size();
        }

        for (int i = 0; i < len - 1; i++) {
            left.add(topping[i]);
            if (left.size() == rightCount[i + 1]) {
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
