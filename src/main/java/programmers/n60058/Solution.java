package programmers.n60058;

import java.util.Stack;

public class Solution {
    /*
    균형문자열 w를 u, v로 분할한다. 두 문자열은 균형 문자열이며, v는 빈 문자열일 수 있다.
        -> 짝수배로 u,v를 나눈다.
    u를 올바른 문자열로 만들어 answer에 잇는다.
    v에 대해 분할을 또 수행한다.
     */
    public static String solution(String p) {
        String answer = divide(p, 0, p.length());
        return answer;
    }

    static String divide(String p, int r, int l) {

        Stack<String> bracket = new Stack<>();

        String perfect = "(";
        // 둘을 균형잡힌 문자열로 분할한다.
        for (int m = r; m < l; m += 2) {
            if (true) {
                // 완전 문자열 일시 u부분은 넘긴다.
                r = m;
            } else {
                perfect = divide(p, r, m) + divide(p, m, l);
            }
        }



        return perfect;
    }

    public static void main(String[] args) {
        System.out.println(solution("(()())()"));
    }
}
