package programmers.n42586;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Integer> stack = new Stack<>(); // 인덱스를 써야하나?
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int top = stack.pop();
                answer[top] = i - top;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            answer[top] = prices.length - top - 1;
        }


        return answer;
    }

    public static void main(String[] args) {
        Arrays.stream(solution(new int[]{1, 2, 3, 2, 3})).forEach( i -> {
            System.out.print(i+", ");
        });
    }
}
