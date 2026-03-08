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
     */
    public static long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;
        long[] aSeq = new long[n + 1]; // 1로 시작하는 펄스 수열 적용 (홀수가 -1)
        long[] bSeq = new long[n + 1]; // -1로 시작하는 펄스 수열 적용 (짝수가 -1)


        for (int i = 0; i < n; i++) {
            aSeq[i + 1] = bSeq[i + 1] = sequence[i];
        }
        for (int i = 1; i <= n; i ++) {
            if (i % 2 == 0) aSeq[i] *= -1; // 홀수가 -1
            if (i % 2 != 0) bSeq[i] *= -1; // 짝수가 -1
        }

        long[] aDp = new long[n + 1];
        long[] bDp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            aDp[i] = Math.max(aSeq[i] + aDp[i - 1], aSeq[i]);
            bDp[i] = Math.max(bSeq[i] + bDp[i - 1], bSeq[i]);

            answer = Math.max(Math.max(aDp[i], bDp[i]), answer);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 3, -6, 1, 3, -1, 2, 4})); // 10
    }
}
