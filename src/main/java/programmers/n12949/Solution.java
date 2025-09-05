package programmers.n12949;

public class Solution {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int r = arr1.length;
        int c = arr2[0].length;
        int[][] answer = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < arr1[0].length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        int[][] arr1 = {
                {1, 4}, {3, 2}, {4, 1}
        };
        int[][] arr2 = {
                {3, 3}, {3, 3}
        };
        solution(arr1, arr2);
    }
}
