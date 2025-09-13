package programmers.n64065;

import java.util.*;

public class Solution {
    /*
    길이가 짧은 튜플부터 하여금 결과 리스트를 채운다.
    정렬을 하면 되겠지만 더 나은 솔루션이 있을까?
     */
    public static int[] solution(String s) {
        String[] row = s.split("},\\s*\\{");
        List<int[]> list = new ArrayList<>();
        for (String str : row) {
            String numbers = str.replaceAll("[/{/}]", "");
            list.add(Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).toArray());
        }

        list.sort((a,b) -> a.length - b.length);

        List<Integer> result = new ArrayList<>();
        for (int[] arr : list) {
            for (int n : arr) {
                if (!result.contains(n)){
                    result.add(n);
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    }
}
