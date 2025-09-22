package programmers.n154539;

import java.util.*;

public class Solution {
    public static int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>(); // 인덱스 스택.
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {

            // 현위치의 값이 스택인덱스의 값보다 클때까지 스택 제거
            while (!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
                int n = stack.pop();
                result[n] = numbers[i];
            }
            stack.push(i);
        }
        // 반복이 끝나고 스택이 비어있지않다면 계산하지 못한 인덱스는 -1
        while(!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }



        return result;
    }

    public static void main(String[] args) {
        solution(new int[]{2, 3, 3, 5});
        solution(new int[]{9, 1, 5, 3, 6, 2});
    }
}
