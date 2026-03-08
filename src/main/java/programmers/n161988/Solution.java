package programmers.n161988;

public class Solution {
    /*
    어떤 수열의 연속된 부분 수열에 같은 길이의 펄스 수열을 각 원소끼리 곱하여 연속된 펄스 부분 수열을 마늗ㄴ다.
    펄스 부분수열: 1, -1, 1, -1... or -1, 1, -1, 1.. 중 하나 인 수열

    연속 펄스 부분수열의 합의 최댓값을 구하라.

    입력: 50만 이하 길이의 배열
    원소: -10만부터 10만 까지의 정수

    펄스 수열을 전체 식에 적용하여 두 식을 만들어 두고 누적합
    => 누적합 시 long 초과

    DP?

    직전의 상태만 보관하면 되므로 카데인 알고리즘을 적용할 수 있다.
     */
    public static long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;

        long aMemo = 0; // 짝수는 양, 홀수는 음
        long bMemo = 0; // 짝수는 홀, 음수는 양
        int purse = 1;
        for (int i = 0; i < n; i++) {
            int aCur = sequence[i] * purse;
            int bCur = sequence[i] * purse * -1;

            aMemo = Math.max(aMemo + aCur, aCur);
            bMemo = Math.max(bMemo + bCur, bCur);

            purse *= -1;

            answer = Math.max(answer, Math.max(aMemo, bMemo));
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 3, -6, 1, 3, -1, 2, 4})); // 10
    }
}
