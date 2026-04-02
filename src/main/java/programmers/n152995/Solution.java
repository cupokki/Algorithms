package programmers.n152995;

import java.util.*;

public class Solution {
    /*
    근태와 동료평가 테이블
    a사원이 b사원 보다 두 지표가 모두 낮은 경우 a 사원은 인센x
    그렇지아니한 사원들을 모아 점수 단순합계 석차를 매겨 인센 차등지급
    동석차가 존재하며, 동석차만큼 다음 석차는 건너 뜀

    테이블의 0번은 완호이며, 완호의 석차를 출력한다.
    인센을 못받는다면 -1을 출력한다.

    인원의 수는 최대 10만명, ^2 = 10_000_000_000

     [3,2]  <-- max:2
     [3,2]  <-- max:2
     [2,1]  <-- max:2
    *[2,2]  <-- 2 > max:2
     [1,4]  <-- 4 > max:2
    */
    public static int solution(int[][] scores) {

        int n = scores.length;

        int[] myScore = {scores[0][0], scores[0][1]}; //완호의 점수를 기록해둔 뒤

        Arrays.sort(scores, Comparator // 두 점수를 각각
                .comparing((int[] a) -> a[0]) // 내림차순
                .reversed()
                .thenComparingInt(a -> a[1]) // 오름차순
        );


        int max = Integer.MIN_VALUE; // 오름차순 정렬된 두번째 필드
        int rank = 1;
        for (int i = 0; i < n ; i++) {
            if (scores[i][1] < max) {
                if (scores[i][0] == myScore[0] && scores[i][1] == myScore[1]) {
                    return -1; // 현재가 완호이며 필드보다 작다면 대상자가 아님
                }
            } else {
                max = Math.max(max, scores[i][1]); // 필드 a가 같은 것 중, 필드 b의 최댓값, 즉 첫번째를 구함
                if (scores[i][0] + scores[i][1] > myScore[0] + myScore[1])
                    rank++; //현재 합이 나보다 크다면
            }
        }

        return rank;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}}));
    }
}