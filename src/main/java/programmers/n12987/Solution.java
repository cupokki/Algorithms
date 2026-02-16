package programmers.n12987;

import java.util.Arrays;

public class Solution {
    /*
    2 * N명의 사원을 N명씩 두팀(A/B)으로 나눠 숫자 게임을 진행
    모든 사원은 무작위 자연수를 부여받는다.
    각 사원은 딱 한경기씩 진행
    각 경기는 양팀의 사원이 나와 수를 비교하여 큰쪽이 1점추가 (무승부면 둘다 0점)
    B팀이 A팀 구성을 먼저 알게되었다. B팀의 최대 승점을 출력하라.

    길이 10만이하 자연수
    원소크기 10억이하 자연수

    그리디
     */
    public static int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (A[idx] < B[i]) {
                answer++;
                idx++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
        System.out.println(solution(new int[]{2, 2, 2, 2}, new int[]{1, 1, 1, 1}));
    }
}
