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
        int[] answer = new int[queries.length];

        Arrays.sort(words);
        // 역순정렬한 걸로 탐색?
        String[] reversedWords = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            reversedWords[i] = sb.reverse().toString();
        }
        Arrays.sort(reversedWords);

//        ab abc abb, aba, acc
        // ab?? -> ab, abc

        for (int i = 0; i < queries.length; i++) {
            String[] curWords;
            String keyword = queries[i];
            int matchCnt = 0;
            int start = keyword.length(), end = keyword.length();
            if (keyword.charAt(0) == '?') { // 앞부터 ?임
                curWords = reversedWords;
                keyword = new StringBuilder(queries[i]).reverse().toString();
            } else { // 뒤 부터 ?임
                curWords = words;
            }
            while (keyword.charAt(start - 1) == '?') {
                start--;
            }

            // 와일드카드와 일치하는 words 원소의 인덱스 발견
            int idx = binarySearch(curWords, keyword, 0, curWords.length, start, end);
            if (idx != -1) {
                // 역순으로 더있는지 확인
                for (int j = idx; j >= 0; j--) {
                    // 길이가 다르거나, 대상 구간 문자가 일치하지않으면
                    if (curWords[j].length() != keyword.length()) break;
                    if (compare(curWords[j], keyword) != 0) break;
                    matchCnt++;
                }

                // 정방향으로 더 있는지 확인
                for (int j = idx + 1; j < curWords.length; j++) {
                    // 길이가 다르거나, 대상 구간 문자가 일치하지않으면
                    if (curWords[j].length() != keyword.length()) break;
                    if (compare(curWords[j], keyword) != 0) break;
                    matchCnt++;
                }
            }

            answer[i] = matchCnt;
        }

        return answer;
    }

    int compare(String word, String keyword) {
        for (int i = 0; i < word.length(); i++) {
            if (keyword.charAt(i) == '?') break;
            if (word.charAt(i) != keyword.charAt(i)) {
                return word.charAt(i) - keyword.charAt(i);
            }
        }
        return 0;
    }


    int binarySearch (String[] words, String keyword, int l, int r, int start, int end) {
        int m = 0;
        while (l < r) {
            m = (l + r) / 2;
            // m번째 문자가 키워드 보다 큼
            int diff = compare(words[m], keyword);
            if (diff == 0) {
                return m;
            } else if (diff < 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(
                new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[]{"fro??", "????o", "fr???", "fro???", "pro?"}
        )));
        // 3, 2, 4, 1, 0
    }

}
