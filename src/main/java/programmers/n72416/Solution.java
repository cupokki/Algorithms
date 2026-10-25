package programmers.n72416;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /*
    조직도를 구성한다.

    직원 (직원번호, 해당 직원의 하루평균 매출액)
    팀의 관계는 화살표로 팀장또는 팀원
    CEO는 1번으로 고정되어있다. 나머지는 누군가에 종속된다.
    한직원은 최대 2개의 팀에 소속된다.(팀원이 하위 팀장)
    겸임팀장이나 두팀의 팀원이 될 수 없다.

    - 워크숍을 위해 모든 팀엔 최소 1명이 참석
    - 직원들의 하루 평균 매출액의 합이 최소가 되게한다.

    직원은 [2, 300_000]
    매출액 최대는 10000
    */

    public int solution(int[] sales, int[][] links) {

        int n = sales.length;

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new  ArrayList<>());
        }

        for (int[] link : links) {
            tree.get(link[0]).add(link[1]);
        }

//       += sales[마지막 - 1] + min(dp(마지막, 참석), dp(마지막, 미참석))
        // dp[i][0]은 선택x
        // dp[i][1]은 선택o
        int[][] dp = new int[n + 1][2];

        dfs(1, n, sales, dp, tree); //ceo부터 후위

        //
        return Math.min(dp[1][0], dp[1][1]);
    }

    void dfs(int depth, int n, int[] sales, int[][] dp, List<List<Integer>> tree) {
        if (tree.get(depth).isEmpty()) { //말단노드
            return;
        }

        for (int next : tree.get(depth)) {
            dfs(next, n, sales, dp, tree);

//            dp[i][0] = Math.min(dp[i][0], sales[i]);
            if (dp[next][0] < dp[next][1]) { // depth 불참

            } else { // 참석

            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(
                new int[]{14, 17, 15, 18, 19, 14, 13, 16, 28, 17},
                new int[][]{
                        {10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}

                }
        )); // 44
        System.out.println(sol.solution(
                new int[]{5, 6, 5, 3, 4},
                new int[][]{
                        {2, 3}, {1, 4}, {2, 5}, {1, 2}
                }
        )); // 2
        System.out.println(sol.solution(
                new int[]{5, 6, 5, 1, 4},
                new int[][]{
                        {2, 3}, {1, 4}, {2, 5}, {1, 2}
                }
        )); // 5
        System.out.println(sol.solution(
                new int[]{10, 10, 1, 1},
                new int[][] {
                        {3, 2}, {4, 3}, {1, 4}
                }
        )); // 2
    }
}