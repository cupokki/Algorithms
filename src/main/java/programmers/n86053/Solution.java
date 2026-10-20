package programmers.n86053;

public class Solution {
    /*

    왕국에 하나이상의 도시
    새도시를 짓는다.
    도시 건설을 위해 금 a개와 은 b개가 필요하다.
    각 도시들이 보유한 금과 은이 주어진다.
    각 도시들은 트럭이 한대 있다.
    트럭은 건설장소와 i번 도시만 왕복한다.
    트럭의 편도 이동시간t_i, 적재량 w_i가 주어진다.

    새 도시를 건설하기 위한 최소시간을 출력한다.

    금과 은은 각각 10억개 이하의 자연수
    도시는 최대 10만개 있다.

    - 계속 광물의 양이 변동된다.
    - 광물을 가져가는 비율도 고려되어야한다.
    */
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = 0; // time

        long left = 0;
        long right = (100_000L * 2) * (2 * 1_000_000_000L);// 최대 왕복시간 * 광물 총량

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (isEnough(mid, a, b, g, s, w, t)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    //time이 가용한 만큼 전달가능한지 확인?
    boolean isEnough(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {

        long totalGold = 0;
        long totalSilver = 0;
        long totalMineral = 0;

        for (int i = 0; i < t.length; i++) {
            long roundCnt = time / (t[i] * 2L); // 왕복 횟수

            if (time % (t[i] * 2L) >= t[i])
                roundCnt++; // 나머지 편도 (복귀필요x)

            long maxTransferWeight = roundCnt * w[i]; // 최대 운송량

            totalGold += Math.min(g[i], maxTransferWeight); // 한 도시에서 가져갈 수 있는 최대의 양
            totalSilver += Math.min(s[i], maxTransferWeight);
            totalMineral += Math.min((long)g[i] + s[i], maxTransferWeight);

        }

        // 목표만큼 조달이 가능한가
        return totalGold >= a && totalSilver >= b && totalMineral >= (long) a + b;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(10, 10, new int[]{100}, new int[]{100}, new int[]{7}, new int[]{10})); // 50
        System.out.println(sol.solution(90, 500, new int[]{70, 70, 0}, new int[]{0, 0, 500}, new int[]{100, 100, 2}, new int[]{4, 8, 1})); // 499
    }

}