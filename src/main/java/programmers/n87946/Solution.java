package programmers.n87946;

import java.util.Arrays;

public class Solution {
    // k는 현재 피로도
    // 배열은 던전 최소 피로도, 소모 피로도
    // 탐험할 수 있는 최대 던전 수
    // 던전의 개수는 최대 8개
    static boolean[] visited;
    static int[][] arr;
    static int max = 0;
    public static int solution(int k, int[][] dungeons) {
        // 피로도 소모가 적으며, 최소 피로도가 높은 던전 부터

        visited = new boolean[dungeons.length];
        arr = dungeons;
        dfs(0, k, 0);

        return max;
    }
    static void dfs(int result, int k, int depth) {
        max = Math.max(max, result);

        if (depth == visited.length) {
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && k >= arr[i][0]) {
                visited[i] = true;
                dfs(result + 1, k - arr[i][1], depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(80,
                new int[][]{
                        {80, 20},
                        {50, 40},
                        {30, 10}
        }));
    }
}
