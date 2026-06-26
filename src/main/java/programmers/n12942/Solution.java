package programmers.n12942;

class Solution {
    /*
    만약 ab, bc행렬이 있을때
    두행렬의 곱을 위해 a * b * c번 곱셈이 수행된다.

    행렬의 연산순서를 바꾸어 최소한의 곱셉 횟수를 출력한다.
    최대한 큰수가 줄어들어야한다.
    */
    public int solution(int[][] mat) {
        int answer = 0;
        int len = mat.length;
        int[][] dp = new int[len + 1][len + 1];

        // dp[i][i] = 0;
        // dp[i - 1][i] = mat[i - 1][0] * mat[i - 1][1] * mat[i][1];
        // dp[i - 2][i] = Math.min(dp[i - 2][i - 1] + alpha, dp[i - 1][i] + alpha);

//         for (int i = 0; i < len; i++) {
//             for (int j = 0; j < len; j++) {

//             }
//         }


        return answer;
    }


    // public static void main(String[] args) {
    //     Solution sol = new Solution();
    //     System.out.println(sol.solution(new int[][]{{5, 3}, {3, 10}, {10, 6}})); // 270
    // }
}