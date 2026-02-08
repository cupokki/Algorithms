package programmers.n72412;

import java.util.*;

public class Solution {
    /*
    지원자는 4가지 항목을 반드시 선택
    - cpp, java, python
    - be, fe
    - junior, senior
    - chicken, pizza

    코테결과 분석해 팀에 맞는 지원자가 몇명인지 알 수 있는 도구 만든다.
    info: 지원자 정보들, 5만개 이하

    쿼리가 촤대 10만회 이므로, 완전탐색은 매우 불리할 것. 인덱스화? 미리 모든 조합을 생성해야할 듯
     */
    public static int[] solution(String[] info, String[] query) {
        int size = query.length;
        int[] answer = new int[size];

        Map<String, List<Integer>> map = new HashMap<>();

        // parsing
        for (int i = 0; i < info.length; i++) {
            String[] tokens = info[i].split(" ");
            String[] langs = {tokens[0], "-"};
            String[] poses = {tokens[1], "-"};
            String[] careers = {tokens[2], "-"};
            String[] foods = {tokens[3], "-"};
            int score = Integer.parseInt(tokens[4]);

            for (String l : langs) {
                for (String p : poses) {
                    for (String c : careers) {
                        for (String f : foods) {
                            String key = l + p + c + f;
                            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < size; i++) {
            // 와일드카드 어떻게 구현할 것인가.
            String[] tokens = Arrays.stream(query[i].split(" "))
                    .filter(s -> !s.equals("and"))
                    .map(String::trim)
                    .toArray(String[]::new);
            int score = Integer.parseInt(tokens[4]);
            String key = String.join("",Arrays.copyOf(tokens, 4));
            List<Integer> list = map.get(key);
            list.sort(Comparator.comparingInt(Integer::intValue));
            int idx = 0;
            while (idx < list.size() && list.get(idx) < score) {
                idx++;
            }
            answer[i] += list.size() - idx;
        }

        return answer;
    }

    public static void main(String[] args) {
        Arrays.stream(solution(
                new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"}
        )).forEach(i -> System.out.print(i + " "));
    }
}
