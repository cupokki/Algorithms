package programmers.n92342;

import java.util.Arrays;

public class Solution {
    /*
    카카오배 양궁대회
    라이언은 전 대회 우승자로 현대회 참여, 상대는 어피치
    1. 어피치가 n발 다 쏘고 라이언이 n발
    2. 점수계산
        - 0부터 10까지 정수구간의 과녁
        k점을 어피치가 a발을 맞혔고, b발을 맞혔을 경우 더 많은 화살을 k점에 맞힌 선수가 k점을 가져감
        같다면 어피치 우선
        (k보다 많은x k점만 가져감, 둘다 못 얻을 수도 있음)

    3. 최종 점수가 더 높은 선수가 우승(같다면 어피치 승)

    어피치는 n발을 다 쏜 상태, 라이언이 가장 큰 점수차로 이기기 위해서 n을 어디에 맞혀야 하는가?
    라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우

    무조건 라이언이 지거나, 비긴다면 [-1];
    info는 10점부터 0점순
     */
    static int[] Info;
    static int[] result;
    static int max;
    public static int[] solution(int n, int[] info) {
        Info = info;
        result = new int[]{-1};
        max = Integer.MIN_VALUE;
        dfs(0, n, new int[11], n);

        return result;
    }

    static void dfs(int depth, int arrow, int[] score, int n) {
        if (depth == 11) {
            int ryan = 0;
            int apeach = 0;
            int sum = 0;
            for (int i = 0; i < 11; i++) {
                sum += score[i];
                if (score[i] == 0 && Info[i] == 0) continue;
                if (score[i] > Info[i]) {
                    ryan += (10 - i);
                } else {
                    apeach += (10 - i);
                }
            }
            if (sum != n) {
                return;
            }
            // 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우
            // 제약 깨질 수 있음
//            if (ryan > apeach && ryan - apeach >= max) {
            if (ryan > apeach) {
                if (ryan - apeach < max) return;
                if (ryan - apeach == max && !isABetterThenB(score, result))  return;

                max = ryan - apeach;
                result = Arrays.copyOf(score, 11);
            }

            return;
        }

        for (int i = arrow; i >= 0; i--) {
            score[depth] = i;
            dfs(depth + 1, arrow - i, score, n);
        }
    }

    private static boolean isABetterThenB(int[] a, int[] b) {
        if (a.length != b.length) return true;
        for (int i = 10; i >= 0; i--) {
            if (a[i] != b[i]) return a[i] > b[i];
        }

        return false;
    }

    public static void main(String[] args) {

        Arrays.stream(solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0})).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

}
