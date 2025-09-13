package programmers.n64065;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /*
    길이가 짧은 튜플부터 하여금 결과 리스트를 채운다.
    정렬을 하면 되겠지만 더 나은 솔루션이 있을까?
     */
    public static int[] solution(String s) {
        String[] row = s.split("},\\s*\\{");
        Queue<Integer> queue = new LinkedList<>();
        for (String str : row) {
            String numbers = str.replaceAll("[/{/}]", "");
            for (String num : numbers.split(",")) {
                int n = Integer.parseInt(num);
                if (!queue.contains(n)) {
                    queue.offer(n);
                }
            }
        }
        return queue.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    }
}
