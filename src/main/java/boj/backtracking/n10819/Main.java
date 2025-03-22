package boj.backtracking.n10819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 차이를 최대로
 * https://www.acmicpc.net/problem/10819
 *
 * N개의 정수 배열 A
 * 배열의 순서를 적절히 바꾸어 다음 식의 최댓값을 구하라.
 * |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
 */
public class Main {
    static int N;
    static int max = 0;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[N];
        visited = new boolean[N];

        backtrack(0);

        System.out.println(max);
    }
    static boolean[] visited;
    static void backtrack(int idx) {
        if (idx == N) {
            int sum = 0;
            for (int i = 0; i < N - 1; i ++) {
                 sum += Math.abs(result[i] - result[i + 1]);
            }
            max = Math.max(sum, max);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                result[idx] = arr[i];
                visited[i] = true;
                backtrack(idx + 1);
                visited[i] = false;
            }

        }
    }
}
