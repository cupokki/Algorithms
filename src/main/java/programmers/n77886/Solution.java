package programmers.n77886;

import java.util.*;

public class Solution {
    /*
    이진수 문자열x를 어떤 행동으로 최대한 사전 순 앞으로 오도록 만들고자 한다
    x의 "110"을 뽑아 임의의 위치에 다시 삽입한다.
    s의 최대 길이 백만, 원소의 길이 최대 백만.
    s의 모든 원소의 길이 합 백만 이하

    그리디?, 최대한 0을 앞으로 보내게 한다.
    110을 기준으로 나누어지는 부분 문자열 r, l이 있을때,

    앞에 나온 0을 건드려면 안된다.
    왼쪽이 110을 앞으로 보내는 경우는 왼쪽의 수가 110보다 크다면, 둘의 위치를 스왑
    오른쪽이 110보다 작은 수면 바꾼다

    문자열 길이가 너무 길어 비트연산으로 어렵고 그냥 문자열로.
    */
    public static String[] solution(String[] s) {
        int n = s.length;
        String[] answer = new String[n];


        for (int i = 0; i < n; i++) {
            StringBuilder stack = new StringBuilder();
            int cnt = 0;

            for (char c : s[i].toCharArray()) { // 인덱스 손상을 막기위한 스택형식
                stack.append(c);
                if (stack.length() >= 3 &&
                        stack.charAt(stack.length() - 3) == '1' &&
                        stack.charAt(stack.length() - 2) == '1' &&
                        stack.charAt(stack.length() - 1) == '0'
                ) {
                    cnt++;
                    stack.delete(stack.length() - 3, stack.length());
                }
            }

            int lastZero = stack.lastIndexOf("0");
            StringBuilder resultBuilder = new StringBuilder();
            String temp = "110".repeat(cnt); // 110이 먼저 올 조건이면 다 써야한다.

            if (lastZero == -1) {// 0이 없으면 맨 앞에 삽입
                resultBuilder
                        .append(temp)
                        .append(stack);
            } else {
                resultBuilder.append(stack.substring(0, lastZero + 1))
                        .append(temp)
                        .append(stack.substring(lastZero + 1));
            }
            answer[i] = resultBuilder.toString();
        }
        return answer;
    }

    static boolean is110(StringBuilder sb, int idx) {
        return sb.charAt(idx - 2) == '1' && sb.charAt(idx - 1) == '1' && sb.charAt(idx) == '0';
    }
}
