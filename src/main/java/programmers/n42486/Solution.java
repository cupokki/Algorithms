package programmers.n42486;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    // 순서대로 앞에것이 배포되어야 배포가능
    public static int[] solution(int[] progress, int[] speeds) {
        int len = progress.length;
        int deployed = 0;
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while(deployed != len) {
            for (int i = 0; i < len; i++) {
                if (progress[i] < 0) {
                    continue;
                }
                if (progress[i] < 100) progress[i] += speeds[i];
                if (progress[i] >= 100) {

                    if (i == 0) {
                        stack.push(i);
                        deployed++;
                        progress[i] = Integer.MIN_VALUE;
                    }

                    if (!stack.isEmpty() && stack.peek() == i - 1) {
                        stack.push(i);
                        deployed++;
                        progress[i] = Integer.MIN_VALUE;
                    }
                }
            }
            if (!stack.isEmpty()) {
                list.add(stack.size());
            }
        }

        int[] result = list.stream().mapToInt(n->n).toArray();
        return result;
    }

    public static void main(String[] args) {
        solution(
                new int[]{93, 30, 55},
                new int[]{1, 30, 5}
                );

        solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1, 1, 1, 1, 1, 1}
        );
    }
}

