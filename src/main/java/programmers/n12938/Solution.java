package programmers.n12938;

import java.util.Arrays;

public class Solution {
    /*
    자연수 n개로 이루어진 중복집합
    "최고의 집합은" 아래 두조건 만족
    1. 각 원소의 합이 s가 되는 수의 집합
    2. 위 조건을 만족하며, 각 원소의 곱이 최대가 되는 집합

    오름차순으로 정렬하여 반환하라

    곱이 최대가 되려면 원소가 가운데로 분포하여야한다.
    s/n?
     */
    public static int[] solution(int n, int s) {
        if (s / n == 0) {
            return new int[]{-1};
        }
        int[] answer = new int[n];
        int temp = s % n;
        int num = s / n;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = num;
            if (temp > 0) {
                answer[i] += 1;
                temp--;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Arrays.stream(solution(2, 9)).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(solution(2, 1)).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.stream(solution(2, 8)).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
