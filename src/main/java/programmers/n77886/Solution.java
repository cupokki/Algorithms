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

    r, l을 비교하여 최고 비트값이 0인

    문자열 길이가 너무 길어 비트연산으로 어렵고 그냥 문자열로.
    */
    public static String[] solution(String[] s) {
        int n = s.length;
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String x = s[i];
            int m = x.length();
            int min = Integer.parseInt(x,2);

            for (int j = 0; j < m - 3; j++) {
                if (is110(x, j)) {
//                    String right = x.substring(0, j);
//                    String left = x.substring(j + 3, m);
                }
            }
            answer[i] = x;

        }
        return answer;
    }

    static boolean is110(String x, int idx) {
        return x.charAt(idx) == '1' && x.charAt(idx + 1) == '1' && x.charAt(idx + 2) == '0';
    }
}
