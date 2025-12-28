package programmers.n60057;

import java.util.*;

public class Solution {
    /*
    문자열 비손실 압축
    같은 값이 연속해서 나타나는 것을 문자 개수로 줄임
    aabbaccc -> 2a2ba3c (1은 생략함)
    이 방법은 압축률이 낮음. (연속된게 없는경우)
    2개 단위로 자른다면, ababcdcd -> 2ab2cd
    => n개 단위로 압축한다.
    문자열 s를 압축할를 가장 짧게 압축한 길이를 출력하라.
    
    이분탐색 -> n개로 나눈다고 할때, n이 커지고 작아짐에 따라 해가 가까워지는 것을 보장할 수 없음.
    n그램인가? 길이가 1000문자열의 부분문자열은 총 몇개인가? Sig(1000) = 1000 * 1001 / 2 = 500500;
    
    */
    static String compressed;
    public static int solution(String s) {
        compressed = "";
        int len = s.length();
        for (int n = 1; n <= len; n++) {
            Set<String> subs = new HashSet<>();
            for (int i = 0; i < len; i++) {
                for (int j = i; j < len; j++) {
                    subs.add(s.substring(i, j));
                }
            }
        }

        int answer = compressed.length();
        return answer;
    }

    static int compress(String s) {
        return 0;
    }

    // public static void main(String[] args) {
    //     System.out.println(solution("aabbaccc")); //7
    //     System.out.println(solution("ababcdcdababcdcd")); //9
    //     System.out.println(solution("abcabcdede")); //8
    //     System.out.println(solution("abcabcabcabcdededededede")); //14
    //     System.out.println(solution("xababcdcdababcdcd")); //17
    // }
}