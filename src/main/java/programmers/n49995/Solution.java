package programmers.n49995;

public class Solution {
    /*
    번호가 붙은 바구니에 과자가 있다. 바구니의 과자 수가 주어질떄
    r <= m <= l에서 , [r, m], [m + 1, l] 의 합이 같아야한다.
    한명이 가질 수 있는 과자의 수 중 가장 큰 수를 출력한다.
    불가능 하다면 0

    배열의 길이는 2000
    */
    public int solution(int[] cookie) {
        int answer = 0;

        int n = cookie.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = cookie[i] + prefixSum[i - 1];
        }


        for (int r = 0; r < n; r++) {
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{1, 1, 2, 3}));
        System.out.println(sol.solution(new int[]{1, 2, 4, 5}));
    }
}