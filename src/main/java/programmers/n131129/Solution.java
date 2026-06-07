package programmers.n131129;

import java.util.Arrays;

public class Solution {
    /*
    특수룰 다트대회
    카운트다운시 무작위로 점수 정해진다.
    다트 던지면서 점수를 깎아 정확히 0으로 만드는 게임
    단, 남은 점수보다 큰 점수로를득점하면 -> 버스트(실격)

    과녁엔 1부터 20까지의 수
    싱글, 더블, 트리플은 각 1배, 2배, 3배로 점수획득.
    불과 아우터불은 카운트다운에 구분없이 50점

    토너먼트로 두선수 참가하여 교대로 던지는 라운드방식
    두 선수가 같은 라운드에 0점이면, 두 선수중 싱글 또는 불을 더 많이 던진 선수가 승리,
    그마저 같다면, 선공인 선수가 승리

    => 최소한의 다트로 0을 만들되, 싱글과 불이 최대한 많이 포함되어야한다.

    목표점수가 주어질떄, 최선의 경우에서 던진 총 다트의 수와, 싱글과 불의 합를 배열에 담아 출력
    target은 10만 이하 자연수

    50, 1-20, 2-40, 3-60
    81개

    그리디하게 선택한다면? 최대한 큰것을 선택한다.
    */
    static class Answer {
        int total;
        int singleAndBull;

        public Answer(int total, int singleAndBull) {
            this.total = total;
            this.singleAndBull = singleAndBull;
        }
    }

    public static int[] solution(int target) {
        int[] answer = new int[2];
//         memo = new int[sum][target + 1];
//         for (int i = 0; i <= target; i++) Arrays.fill(memo[i], -1);
//         backtrack(target);
        // return memo[][];


        int[][] dp = new int[target + 1][2]; // 최소 횟수와, 불과 싱글의 합 target을 만들기위한.

        // 최소한의 공으로 target을 만들되 같은 case가 많다면 불과 싱글이 최대한 많아야한다.
        dp[0][0] = 0; // 최소 0을 만들기위해 0개의 불이나, 싱글이 필요하다.
        dp[0][1] = 0;
        for (int i = 1; i <= target; i++) {

            // 더블
            for (int score = 2; score <= 40; score += 2) {
                if (i - score < 0) break;
                dp[i][0] = dp[i - score][0] + 1;
                dp[i][1] = dp[i - score][1];
            }

            // 트리플
            for (int score = 3; score <= 60; score += 3) {
                if (i - score < 0) break;
                dp[i][0] = dp[i - score][0] + 1; // 횟수 1
                dp[i][1] = dp[i - score][1];
            }

            // 불
            if (i >= 50) {
                dp[i][0] = dp[i - 50][0] + 1;
                dp[i][1] = dp[i - 50][1] + 1;
            }

            // 싱글
            for (int score = 1; score <= 20; score += 1) {
                if (i - score < 0) break;
                if (dp[i][0] > dp[i - score][0] + 1) { // 현재가 더 우선순위가 높다면?
                    if (dp[i][1] > dp[i - score][1] + 1) continue;
                    dp[i][0] = dp[i - score][0] + 1; // 횟수 1
                    dp[i][1] = dp[i - score][1] + 1; // 싱글및불 1
                }
            }
        }

        return new int[]{dp[target][0], dp[target][1]};
        //    static int[] memo;
//    static int backtrack(int remain, int sum) {
//        if (remain == 0) {
//            return 0;
//        }
//
//        if (memo[][] == -1) {
//            return memo[][];
//        }
//
//        // 어떤 과녁을 맞출지
//        int score;
//
//        return memo[][] = score;
//    }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(20)));
        System.out.println(Arrays.toString(solution(21)));
        System.out.println(Arrays.toString(solution(58)));
        System.out.println(Arrays.toString(solution(61)));
    }
}