package programmers.n12941;

import java.util.*;

public class Solution {
    static int min = Integer.MAX_VALUE;
    public static int solution(int[] A, int[] B) {
        int size = A.length;
        boolean[] visited = new boolean[size];
        dfs(visited, A, B, 0, 0);
        return min;
//        Arrays.sort(A);
//        Arrays.sort(B);
//        int size = A.length;
//        int sum = 0;
//        for (int i = 0; i < size; i++) {
//            sum += A[i] * B[size - 1 - i];
//        }
//        return sum;
    }
    static void dfs(boolean[] visited, int[] A, int[] B, int depth, int sum) {
        if (depth == A.length) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < A.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(visited, A, B, i + 1 , sum + A[depth] * B[i]);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}) == 29);
        System.out.println(solution(new int[]{1, 2}, new int[]{3, 4}) == 10);
    }
}
