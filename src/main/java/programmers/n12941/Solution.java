package programmers.n12941;

import java.util.*;

public class Solution {
    public static int solution(int[] A, int[] B) {
//        int size = A.length;
//        boolean[] visited = new boolean[size];
//        int[] result = new int[1];
//        result[0] = Integer.MAX_VALUE;
//        dfs(0, A, B, 0, 0, result);
//        return result[0];
        Arrays.sort(A);
        Arrays.sort(B);
        int size = A.length;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += A[i] * B[size - 1 - i];
        }
        return sum;
    }
//    static void dfs(int sum, int[] A, int[] B, int depth, int start, int[] result) {
//        int size = A.length;
//        if (depth == 3) {
//            result[0] = Math.min(result[0], sum);
//            return;
//        }
//
//        for (int i = start; i < size; i++) {
//            dfs(sum + A[depth] * B[i], A, B, i + 1 , i + 1, result);
//        }
//    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}) == 29);
        System.out.println(solution(new int[]{1, 2}, new int[]{3, 4}) == 10);
    }
}
