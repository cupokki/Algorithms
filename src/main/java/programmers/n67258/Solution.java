package programmers.n67258;

import java.util.Arrays;

public class Solution {
    /*
    진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
    진열된 보석은 10만 이하 자연수
    보석은 10자이하

    투 포인터?

    방금 포함한 구간의 첫 요소가 마지막에 포함되었다면 제한다.
     */
    static String[] Gems;
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        int max = Integer.MIN_VALUE;
        int tail = 0, head = 0;
        while (head < gems.length - 1 && tail <= head) {
            head++;
            while (tail != head && gems[tail] == gems[head]) {
                tail++;
            }
            while (tail <= head && gems[tail] == gems[tail + 1]) {
                tail++;
            }
            int gap = head - tail;
            if (gap > max) {
                max = gap;
                answer[0] = tail + 1;
                answer[1] = head;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
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
