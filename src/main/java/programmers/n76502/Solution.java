package programmers.n76502;

import java.util.Stack;

public class Solution {
    public static int solution(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (func(s, i)) result++;
        }

        return result;
    }
    static boolean func(String s, int start) {
        Stack<Character> stack = new Stack<>();

        for (int i = start; i < start + s.length(); i++){
            char c = s.charAt(i % s.length());
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (stack.isEmpty()) {
                return false;
            } else if (stack.peek() == '(' && c == ')') {
                stack.pop();
            } else if (stack.peek() == '{' && c == '}') {
                stack.pop();
            } else if (stack.peek() == '[' && c == ']') {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(solution("[](){}"));
        System.out.println(solution("}]()[{"));
        System.out.println(solution("[)(]"));
        System.out.println(solution("}}}"));
    }

}
