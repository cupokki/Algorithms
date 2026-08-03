package programmers.n60060;

import java.util.*;

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
//     public int[] solution(String[] words, String[] queries) {
//        int[] answer = new int[queries.length];
//
//        Map<Integer, List<String>> wordsByLength = new HashMap<>();
//        Map<Integer, List<String>> reversedWordsByLength = new HashMap<>();
//
//        for (int i = 0; i < words.length; i++) {
//            wordsByLength.computeIfAbsent(words[i].length(), k -> new ArrayList<>()).add(words[i]);
//            String reversed = new StringBuilder(words[i]).reverse().toString();
//            reversedWordsByLength.computeIfAbsent(words[i].length(), k -> new ArrayList<>()).add(reversed);
//        }
//
//
//
//        wordsByLength.forEach((k,v)-> Collections.sort(v));
//        reversedWordsByLength.forEach((k,v)-> Collections.sort(v));
//

    /// /        ab abc abb, aba, acc
//        // ab?? -> ab, abc
//
//        for (int i = 0; i < queries.length; i++) {
//            String keyword = queries[i];
//            List<String> curWords;
//
//            if (keyword.charAt(0) == '?') {
//                keyword = new StringBuilder(keyword).reverse().toString();
//                curWords = reversedWordsByLength.get(keyword.length());
//            } else {
//                curWords = wordsByLength.get(keyword.length());
//            }
//
//            if (curWords == null) {
//                answer[i] = 0;
//                continue;
//            }
//
//            String start = keyword.replace("?", "a");
//            String end = keyword.replace("?", "z");
//
//            // 와일드카드와 일치하는 words 원소의 인덱스 발견
//            int startIdx = binarySearchLow(curWords, start);
//            int endIdx = binarySearchHigh(curWords, end);
//            answer[i] = endIdx - startIdx;
//        }
//
//        return answer;
//    }
//
//
//    int binarySearchLow (List<String> words, String target) {
//        int l = 0;
//        int r = words.size();
//        while (l < r) {
//            int m = (l + r) / 2;
//            if (words.get(m).compareTo(target) >= 0) {
//                r = m;
//            } else {
//                l = m + 1;
//            }
//        }
//        return l;
//    }
//
//    int binarySearchHigh (List<String> words, String target) {
//        int l = 0;
//        int r = words.size();
//        while (l < r) {
//            int m = (l + r) / 2;
//            if (words.get(m).compareTo(target) > 0) {
//                r = m;
//            } else {
//                l = m + 1;
//            }
//        }
//        return l;
//    }
//
    class Node {
        Map<Character, Node> children = new HashMap<>();
        Map<Integer, Integer> wordLengthCnt = new HashMap<>();
        // 전체 단어 길이가 별, 해당 길이를 가진 단어의 수.
    }
    class Tree {
        Node root = new Node();

        void add(String word) {
            Node cur = root;
            cur.wordLengthCnt.put(word.length(), cur.wordLengthCnt.getOrDefault(word.length(), 0) + 1);
            for (char c : word.toCharArray()) {
                cur = cur.children.computeIfAbsent(c, k -> new Node());
                cur.wordLengthCnt.put(word.length(), cur.wordLengthCnt.getOrDefault(word.length(), 0) + 1);
            }
        }

        int getCount(String query) {
            Node cur = root;

            for (char c : query.toCharArray()) {
                if (c == '?') {
                    return cur.wordLengthCnt.getOrDefault(query.length(), 0);
                } else if (!cur.children.containsKey(c)) {
                    return 0;
                } else {
                    cur = cur.children.get(c);
                }
            }
            return cur.wordLengthCnt.getOrDefault(query.length(), 0);
        }
    }
    public int[] solution(String[] words, String[] queries) {

        int[] answer = new int[queries.length];
        Tree tree = new Tree();
        Tree reversedTree = new Tree();

        for (String word : words) {
            tree.add(word);
            reversedTree.add(new StringBuilder(word).reverse().toString());
        }


        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.charAt(0) == '?') {
                answer[i] = reversedTree.getCount(new StringBuilder(query).reverse().toString());
            } else {
                answer[i] = tree.getCount(query);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(
                new String[]{"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[]{"fro??", "????o", "fr???", "fro???", "pro?"}
        )));
        // 3, 2, 4, 1, 0
    }

}
