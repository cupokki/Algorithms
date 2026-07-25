package programmers.n60060;

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
     */
    public int[] solution(String[] words, String[] queries) {
        int[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(String.valueOf(sol.solution(new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[]{"fro??", "????o", "fr???", "fro???", "pro?"})));
        // 3, 2, 4, 1, 0
    }

}
