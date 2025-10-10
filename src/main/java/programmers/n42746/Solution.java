package programmers.n42746;

import java.util.Arrays;

public class Solution {
    /*
    3, 30, 34, 5, 9
    9 5 34 30 3  + 23 330 3330 3340
     => 현재 자릿수의 수가 같으면 다음으로 몇자린지 확인해야한다.
    9534330
     */
    public static String solution(int[] numbers) {
        String[] sorted = Arrays.stream(numbers).mapToObj(Integer::toString)
                .sorted((b, a) -> (a + b).compareTo(b + a))
                .toArray(String[]::new);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(sorted).forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 30}));
        System.out.println(solution(new int[]{0, 300, 3, 34, 32}));
        System.out.println(solution(new int[]{100, 1}));
        System.out.println(solution(new int[]{3, 30, 34, 5, 9}));
        System.out.println(solution(new int[]{6, 10, 2}));
    }
}
