package programmers.n12951;

import java.util.Arrays;

public class Main {
    /**
     * JadenCase 문자열 만들기
     * 첫 문자가 대문자이고, 나머지 문자는 소문자로 변경하여라
     * @param s
     * @return String
     */
    static String solution(String s) {
        var carr = s.toLowerCase().toCharArray();
        if (carr[0] >= 'a' && carr[0] <= 'z') {
            carr[0] += (char) ('A' -'a');
        }
        for (int i = 1; i < carr.length; i++) {
            if (carr[i - 1] == ' ' && carr[i] >= 'a' && carr[i] <= 'z') {
                carr[i] += (char) ('A' -'a');
            }
        }
        return String.valueOf(carr);
    }

    public static void main(String[] args) {
        System.out.println(solution("3people unFollowed me").equals("3people Unfollowed Me"));
        System.out.println(solution("for the last week").equals("For The Last Week"));
    }
}
