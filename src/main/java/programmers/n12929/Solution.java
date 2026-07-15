package programmers.n12929;

public class Solution {
    /*

    n개의 괄호 쌍이 주어질떄, 만들 수 있는 유효한 괄호쌍의 개수를 출력한다.

    n: 14이하의 자연수

    직전의 결과를 한번 감싸면 된다. -> dp?

    ()

    ()(), (())
    (()()), ((())), ()()(), (())(), ()(())

    직전 값에 감싸는게 아니라 ()로 생성된 최소 단위공간만큼 케이스가 늘어난다?
    괄호에 감싸지는 j개, 그렇지않은 i - 1 - j개
    */
    public int solution(int n) {

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1; // "()"


        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[n];
    }

     public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(sol.solution(2));
     }
}