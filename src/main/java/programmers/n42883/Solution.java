package programmers.n42883;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {
    /*
    주어진 숫자에서 k개의 문자를 제거하여 만들 수 있는 최대 수
    k는 number의 자릿수 미만 (최대 100만 자리 이하)
    1. 분할정복가능한가.
    부분문자열로 바꾼다고 해보자. 각 부분에서 수를 제거한다하더라도 다시 문자열을 합쳤을때 최댓값을 보장 할 수 없다.
     */
    public static String solution(String number, int k) {
        int K = k;
//        return bfs(number);

        Deque<Character> dq = new LinkedList<>();

        int idx = 0;
        while(idx < number.length()) {
             while (k > 0 && !dq.isEmpty() && dq.peekLast() < number.charAt(idx)) {
                dq.pollLast();
                k--;
            }
            if (k > 0) {
                dq.offerLast(number.charAt(idx));
                idx++;
            } else {
                break;
            }
        }
        String temp = "";
        while(!dq.isEmpty() && temp.length() < number.length() - K) {
            temp += dq.pollFirst();
        }
        temp += number.substring(idx);
        return temp.equals("") ? "0" : temp;

    }

//    static String bfs(String s) {
//        String max = "0";
//        Queue<String[]> q = new LinkedList<>(); // 이전값을 전달한다. 지운 수를 파싱한걸 전달하니 없어지지도 않을것임
//        q.offer(new String[]{s,""});
//        while (!q.isEmpty()) {
//            String[] state = q.poll();
//
//            String a; // max
//            String b; // current
//            if (max.length() < state[0].length()) {
//                a = ("0").repeat(state[0].length() - max.length()) + max;
//                b = state[0];
//            } else {
//                a = max;
//                b = ("0").repeat(max.length() - state[0].length()) + state[0];
//            }
//            if (a.compareTo(b) > 0) {
//                continue;
//            }
//
//            if (state[1].length() == K) {
//                max = b;
////                if (a.compareTo(b) < 0) {
////
////                }
//                continue;
//            }
//
//            for (int i = 0; i < state[0].length(); i++) {
//                String next = state[0].substring(0, i) + state[0].substring(i + 1, state[0].length());
//                q.offer(new String[]{next, state[1] + "0"});
//            }
//
////            long nextLen = len - state[1];
////            for(int i = 0; i < nextLen; i++) {
////                int termA = (int) (state[0] / Math.pow(10, i + 1));
////                int termB = (int) (state[0] / Math.pow(10, i));
////                int a = (int) (termA * Math.pow(10, i));
////                int b = (int) (state[0] - termB * Math.pow(10, i));
////                long next = a + b;
////                q.offer(new long[]{next, state[1] + 1});
////            }
//        }
//        return max;
//    }

    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
        System.out.println(solution("417", 1));
        System.out.println(solution("41", 1));

    }
}
