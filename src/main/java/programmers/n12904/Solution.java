package programmers.n12904;

public class Solution {/*
    문자열에서 가장 긴 펠린드롬인 부분 문자열의 길이를 출력한다.
    s의 길이 2500이하의 자연수
    */
    public static int solution(String s) {

        int answer = 0;

        int n = s.length();
        int max = 0;

        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (max > end - start) break;
                if (check(s, start, end)) {
                    max = Math.max(max, end - start + 1);
                }
            }
        }

        answer = max;
        return answer;
    }
    static boolean check(String s, int start, int end) {
        for (int i = 0; i < (end - start) / 2; i++) {
            if (s.charAt(start + i) != s.charAt(end - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("abcdcba"));
        System.out.println(solution("abacde"));
    }

}
