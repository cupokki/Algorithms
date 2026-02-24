package programmers.n67258;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /*
    진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
    진열된 보석은 10만 이하 자연수
    보석은 10자이하

    투 포인터?

    방금 포함한 구간의 첫 요소가 마지막에 포함되었다면 제한다.
    이런 식의 최대구간이 항상 정답이 아니다
    내방식대로 올바른 구현이 될려면 모든게 들어있는지 상태를 유지해야한다..
     */

    public static int[] solution(String[] gems) {
        int[] answer = {1, 1};
        int min = Integer.MAX_VALUE;
        int types = (int) Arrays.stream(gems).distinct().count();
        Map<String, Integer> map = new HashMap<>();
        int tail = 0;

        for (int head = 0; head < gems.length; head++) {
            map.merge(gems[head], 1, Integer::sum);
            while (tail < head && gems[head].equals(gems[tail])) {
                map.merge(gems[tail], -1, Integer::sum);
                if (map.get(gems[tail]) == 0) map.remove(gems[tail]);
                tail++;
            }
            while (tail < head && map.get(gems[tail]) > 1) {
                map.merge(gems[tail], -1, Integer::sum);
                if (map.get(gems[tail]) == 0) map.remove(gems[tail]);
                tail++;
            }

            if (map.keySet().size() == types && min > head - tail) {
                min = head - tail;
                answer[0] = tail + 1;
                answer[1] = head + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Arrays.stream(solution(new String[]{"A", "B", "C", "B", "D", "A"}))
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}))
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(solution(new String[]{"AA", "AB", "AC", "AA", "AC"}))
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(solution(new String[]{"XYZ", "XYZ", "XYZ"}))
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"}))
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
