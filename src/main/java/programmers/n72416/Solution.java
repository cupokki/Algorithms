package programmers.n72416;

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

    -   예제 2에서 매출액을 고려하지않고 직원을 선택하는 방법
        ceo(1번)을 제외한 팀장을 뽑는다.

    */
    public int solution(int[] sales, int[][] links) {
        int answer = 0;

        int n = sales.length;

        int right = 0;
        int left = n * 10_000;

        while (right <= left) {
            int mid = right + (left - right) / 2;

            if (isEnough(mid, sales, links)) {
                mid = answer;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    // 선택가능한 모든경우로, money이하로 만들 수 있는가?
    boolean isEnough(int money, int[] sales, int[][] links) {


        return false;
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