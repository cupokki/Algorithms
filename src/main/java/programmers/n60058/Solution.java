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
        if (r == l) {
            return "";
        }

        // 둘을 균형잡힌 문자열로 분할한다.
        // u는 분리할 수 없는 균형잡힌 문자열이다. ->
        int m = r + 2;
        int[] temp = new int[2]; // 괄호 개수
        temp[p.charAt(r) - '(']++;
        temp[p.charAt(r + 1) - '(']++;
        while(m < l && temp[0] != temp[1]) { // u,v를 구하는 로직
            temp[p.charAt(m) - '(']++;
            temp[p.charAt(m + 1) - '(']++;
            m += 2;
        } // 최적화를 위해서라면 여기서 하는게 맞겠지


        if (checkBracket(p, r, m)) { // u가 완전 문자열이라면
            // 완전 문자열 일시 u부분은 넘긴다.
            return p.substring(r, m) + divide(p, m, l);
        }
        String perfect = "(" + p.substring(m, l) + ")" + invert(p, r + 1, m - 1);
        return perfect;
    }
    static boolean checkBracket(String p, int r, int l) {
        int openCnt = 0;
        for (int i = r; i < l ;i++) {
            if (p.charAt(i) == '(') {
                openCnt++;
            } else if (openCnt != 0 && p.charAt(i) == ')') {
                openCnt--;
            } else {
                return false;
            }
        }
        return openCnt == 0;
    }
    static String invert(String p, int r, int l) {
        StringBuilder sb = new StringBuilder();
        for (int i = r; i < l; i++) {
            char c = p.charAt(i);
            if (c == ')') {
                sb.append('(');
            } else {
                sb.append(')');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("(()())()"));
        System.out.println(solution(")("));
        System.out.println(solution("()))((()"));
    }
}
