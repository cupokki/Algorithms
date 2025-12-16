package programmers.n134239;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /*
    콜라츠 추측이란, 모든 자연수 K에 대해 다음 작업을 반복하여 항상 1로 만들수 있다는 추측
    1. 짝수라면 2로 나눈다. or 홀수하면 3을 곱하고 1을 더한다.
    2. 결과값이 1보다 크다면 1번을 반복한다.
    이를 그래프로 표시하여 정적분한다.

     */
    public static double[] solution(int k, int[][] ranges) {
        int[] arr = woobak(k);
        int n = arr.length;

        double[] prefixSum = new double[n];

        for (int i = 1 ; i < prefixSum.length; i++) {
            double current = (arr[i - 1] + arr[i]) * 1 / 2.0;
            prefixSum[i] = prefixSum[i - 1] + current;
        }

        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = n + ranges[i][1] - 1;

            if (a > b) {
                answer[i] = -1;
            } else {
                answer[i] = prefixSum[b] - prefixSum[a];
            }
        }

        return answer;
    }
    static int[] woobak(int k) {
        List<Integer> list = new ArrayList<>();
        while(k != 1) {
            list.add(k);
            if (k % 2 == 0) {
                k = k / 2;
            } else {
                k = (k * 3) + 1;
            }
        }
        list.add(1);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Arrays.stream(solution(5, new int[][]{{0, 0}, {0, -1}, {2, -3}, {3, -3}}))
                .forEach(d -> System.out.print(d + " "));
        System.out.println();
        Arrays.stream(solution(3, new int[][]{{0,0}, {1, -2}, {3, -3}}))
                .forEach(d -> System.out.print(d + " "));
    }
}
