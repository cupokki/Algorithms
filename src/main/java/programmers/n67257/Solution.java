package programmers.n67257;

import java.util.*;


public class Solution {
    public static long solution(String expression) {
        long answer = 0;
        List<String> tokens = new ArrayList<>();
        int prevPos = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                tokens.add(expression.substring(prevPos, i));
                tokens.add(String.valueOf(c));
                prevPos = i + 1;
            }
        }
        tokens.add(expression.substring(prevPos));

        answer = Math.max(answer, calculate(new String[]{"+", "-", "*"}, tokens));
        answer = Math.max(answer, calculate(new String[]{"+", "*", "-"}, tokens));
        answer = Math.max(answer, calculate(new String[]{"-", "+", "*"}, tokens));
        answer = Math.max(answer, calculate(new String[]{"-", "*", "+"}, tokens));
        answer = Math.max(answer, calculate(new String[]{"*", "+", "-"}, tokens));
        answer = Math.max(answer, calculate(new String[]{"*", "-", "+"}, tokens));

        return answer;
    }

    static long calculate(String[] order, List<String> tokens){
        List<String> args = new ArrayList<>();
        for (String s : tokens) {
            args.add(s);
        }

        for (String o : order) {
            int len = args.size() / 2; // 연산자 개수
            int idx = 1;
            for (int i = 0; i < len; i++) {
                if (!args.get(idx).equals(o)) {
                    idx += 2;
                    continue;
                }

                Long a = Long.parseLong(args.remove(idx - 1));
                String operator = args.remove(idx - 1);
                Long b = Long.parseLong(args.remove(idx - 1));

                Long result = 0L;
                if (operator.equals("+")) {
                    result = a + b;
                } else if (operator.equals("-")) {
                    result = a - b;
                } else {
                    result = a * b;
                }
                args.add(idx - 1, String.valueOf(result));
            }
        }

        return Math.abs(Long.parseLong(args.get(0)));
    }

    public static void main(String[] args) {
        System.out.println(solution("100-200*300-500+20"));
        System.out.println(solution("50*6-3*2"	));
    }
}
