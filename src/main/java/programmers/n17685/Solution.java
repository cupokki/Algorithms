package programmers.n17685;

import java.util.*;

public class Solution {
    /*
    go가 한번 입력되면 다음번엔 g만 입력해도 go를 추천해준다.
    자동완성 기능의 효율확인 -> 몇글자를 입력해야하는가.
    
    단어 수: N
    단어들의 길이 총합: L
    
    한글자씩 읽어들인다.
    map에 조회한다.

    순서대로 입력하는게 아니다.
    
    */
    public int solution(String[] words) {
        int answer = 0;

        int n = words.length;
        Arrays.sort(words);

        // 인접단어를 비교하여 계산
        for (int i = 0; i < n; i++) {
            String cur = words[i];

            int maxSameLen = 0;

            if (i > 0) {
                String prev = words[i - 1];

                // compare
                int len = Math.min(prev.length(), cur.length());
                int sameLen = len;
                for (int j = 0; j < len; j++) {
                    if (prev.charAt(j) != cur.charAt(j)) {
                        sameLen = j;
                        break;
                    }
                }
                maxSameLen = Math.max(maxSameLen, sameLen);
            }

            if (i < n - 1) {
                String next = words[i + 1];

                // compare
                int len = Math.min(next.length(), cur.length());
                int sameLen = len;
                for (int j = 0; j < len; j++) {
                    if (next.charAt(j) != cur.charAt(j)) {
                        sameLen = j;
                        break;
                    }
                }
                maxSameLen = Math.max(maxSameLen, sameLen);
            }

            answer += Math.min(maxSameLen + 1, cur.length());

        }
        return answer;
    }

     public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(sol.solution(new String[]{"go", "gone", "guild"})); // 7
         System.out.println(sol.solution(new String[]{"abc", "def", "ghi", "jkim"})); // 4
         System.out.println(sol.solution(new String[]{"word", "war", "warrior", "world"})); // 15
     }
}