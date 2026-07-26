package programmers.n60060;

import java.util.Arrays;

public class Solution {
    /*
    노래가사 검색 프로그램 개발

    키워드에 와일드카드 "?"가 포함된 패턴
    모든단어 words와 키워드 queries가 주어진다.
    각 키워드별로 매치된 단어의 수를 구하라.

    - words.length: [2, 100_000]
    - 단어에 공백 없음, 영어 소문자만
    - 가사 단어 길이의 합은 100만 이하.
    - 중복은 제거하라

    words 정렬 -> 인접한것은 비슷할 확률이 높다.

    binary search해서, start문자가 같은것 탐색
    탐색후 주변검색
     */
    public int[] solution(String[] words, String[] queries) {
        int[] answer = {};

        Arrays.sort(words);

        for (String keyword : queries) {

            int start = 0, end = keyword.length();
            if (keyword.charAt(0) == '?') {
                while (keyword.charAt(start) != '?') {
                    start++;
                }
            } else {
                while (keyword.charAt(end - 1) != '?') {
                    end--;
                }
            }



        }

        return answer;
    }

    // 앞쪽 와일드카드넣으면 안된다.
    int binarySearch (String[] words, String keyword, int l, int r) {
        int m = (l + r) / 2;
        while (l < r) {

        }
        return m;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(String.valueOf(sol.solution(new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[]{"fro??", "????o", "fr???", "fro???", "pro?"})));
        // 3, 2, 4, 1, 0
    }

}
