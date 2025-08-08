package programmers.n12909;

import java.util.Stack;

public class Solution {
    static boolean solution(String s) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push("(");
            } else if (stack.isEmpty()){
                return false;
            } else {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    static public void main(String[] args) {
        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
    }
}
