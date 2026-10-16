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

    가장 가깝고 가져올 수 있는 광물의 양이 많은 도시로 부터
    광물의 양이 변동되므로, 우선순위큐?

    냅색문제와 유사하다. 시간이 비용이고, 물건이 gold와 silver이다.
    복잡한 점은, 광물의 잔량이 고려되어야한다?

    answer = dp(a, b, t);?
    */
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1; // time

        int n = g.length;

        int[][] dp = new int[a + 1][];

        for (int i = 0; i <= n; i++) {

        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(10, 10, new int[]{100}, new int[]{100}, new int[]{7}, new int[]{10})); // 50
        System.out.println(sol.solution(90, 500, new int[]{70, 70, 0}, new int[]{0, 0, 500}, new int[]{100, 100, 2}, new int[]{4, 8, 1})); // 499
    }

}