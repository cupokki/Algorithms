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
    
    */
    public int solution(String[] words) {
        int answer = 0;

        Map<String, Integer> map = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        // 아예 처음 찾는 경우를 고려하라.
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            sb = new StringBuilder();
            boolean done = false;
            for (int j = 0; j < word.length(); j++) {
                sb.append(word.charAt(j));
                int value = map.computeIfAbsent(sb.toString(), k -> 0);
                if (value == 0 && map.containsKey(word)) { // unique
                    done = true;
                }
                if (!done) {
                    answer++;
                }
                map.put(sb.toString(), value + 1);

            }
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