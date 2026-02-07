package programmers.n72412;

import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    /*
    지원자는 4가지 항목을 반드시 선택
    - cpp, java, python
    - be, fe
    - junior, senior
    - chicken, pizza

    코테결과 분석해 팀에 맞는 지원자가 몇명인지 알 수 있는 도구 만든다.
    info: 지원자 정보들, 5만개 이하

    쿼리가 촤대 10만회 이므로, 완전탐색은 매우 불리할 것. 인덱스화?
    트리맵?
     */
    record Row(
            String lang,
            String pos,
            String career,
            String soulFood,
            Integer score
    ) implements Comparable<Row>{
        @Override
        public int compareTo(Row o) {
            int res = this.score.compareTo(o.score);
            if (res == 0) this.lang.compareTo(o.lang);
            if (res == 0) res = this.pos.compareTo(o.pos);
            if (res == 0) res = this.career.compareTo(o.career);
            if (res == 0) res = this.soulFood.compareTo(o.soulFood);
            return res;
        }
    };
    public static int[] solution(String[] info, String[] query) {
        int size = query.length;
        int[] answer = new int[size];

        TreeMap<Row, Integer> tree = new TreeMap<>();

        // parsing
        for (int i = 0; i < info.length; i++) {
            String[] token = info[i].split(" ");
            tree.put(new Row(token[0], token[1], token[2], token[3], Integer.parseInt(token[4])),i);
        }

        for (int i = 0; i < size; i++) {
            // 와일드카드 어떻게 구현할 것인가.
            String[] token = query[i].split("and");
            Arrays.stream(token).map(s -> {
                s = s.trim();
                if (s.equals("-")) {
                    // 와일드 카드
                }
                return s;
            });

//            var res = tree.subMap(
//                    new Row()
//            );
//            answer[i] = res.size();
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
