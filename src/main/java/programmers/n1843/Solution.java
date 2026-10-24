package programmers.n1843;

public class Solution {
    /*
    뺄셈은 연산 순서에 따라 결과가 바뀔 수 있다.

    정수 배열로 입력이 주어질때, 연산 순서에 따른 결과의 최댓값을 반환한다.

    배열의 최대 길이는 201
     */
    static int[][] dp;
    public int solution(String arr[]) {
        int answer = -1;

        int n = arr.length;
        dp = new int[n][n];

        answer = dfs(0, n, 0);


        return answer;
    }

    int dfs(int depth, int n, int max) {
        if (depth == n) {
            return max;
        }

//        for () {
//            if () { // 존재한다면
//
//            }
//        }

//        return dp[depth][max];
        return 0;
    }

    public static void main(String[] args) {

    }
}
