package programmers.n150368;

import java.util.Arrays;

public class Solution {
    /*
    프로모션 목표 우선순위
    1. 플러스 가입자 최대한 늘릴것
    2. 이모티콘 판매액을 늘릴 것

    - n명의 이용자에게 이모티콘 m개를 할인 판매
    - 할인율은 10 20 30 40% 중 하나
    - 각 사용자는 자신의 기준에 따라 일정 비율 할인 하는 이모티콘 모두 구매
    - 결과 금액 초과시에 플러스 가입(이모티콘 모두 취소)
     */
    public static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};



        return answer;
    }

    public static void main(String[] args) {
        var u1 = new int[][] {
                {40, 10000},
                {25, 10000}
        };
        var e1 = new int[] {7000, 9000};
        Arrays.stream(solution(u1, e1)).forEach(i -> System.out.print(i + " "));
        System.out.println();
        var u2 = new int[][]{
                {40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}
        };
        var e2 = new int[] {1300, 1500, 1600, 4900};
        Arrays.stream(solution(u2, e2)).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
