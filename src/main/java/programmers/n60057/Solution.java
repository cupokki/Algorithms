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
    아니 연속된 것만 압축되므로 의미없어보인다.
    우선 완전탐색으로...
    */
    public static int solution(String s) {
        int answer = s.length();
        int len = s.length();
        int max = len / 2;
        for (int i = 1; i <= max; i++) {

            List<Token> tokens = new LinkedList<>();
            int start = 0;
            for (int end = i; end < len; end += i) {
                tokens.add(new Token(1, s.substring(start, end)));
                start = end;
            }
            tokens.add(new Token(1, s.substring(start)));

            int idx = 1;
            while(idx < tokens.size()) {
                Token prev = tokens.get(idx - 1);
                Token current = tokens.get(idx);
                while(prev.str.equals(current.str)) {
                    tokens.remove(idx);
                    prev.count++;
                    if (idx >= tokens.size()) {
                        break;
                    }
                    current = tokens.get(idx);
                }
                idx++;
            }
            StringBuilder sb = new StringBuilder();
            while(!tokens.isEmpty()) {
                Token t = tokens.remove(0);
                if (t.count != 1) {
                    sb.append(t.count);
                }
                sb.append(t.str);
            }
            String compressed = sb.toString();
            answer = Math.min(answer, compressed.length());
        }

        return answer;
    }
    static class Token {
        int count;
        String str;
        Token(int count, String str) {
            this.count = count;
            this.str = str;
        }
    }

     public static void main(String[] args) {
         System.out.println(solution("aabbaccc")); //7
         System.out.println(solution("ababcdcdababcdcd")); //9
         System.out.println(solution("abcabcdede")); //8
         System.out.println(solution("abcabcabcabcdededededede")); //14
         System.out.println(solution("xababcdcdababcdcd")); //17
     }
}