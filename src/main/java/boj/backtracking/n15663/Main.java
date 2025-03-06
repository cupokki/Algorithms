package boj.backtracking.n15663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (9)
 * https://www.acmicpc.net/problem/15663
 * N개의 자연수 중, M개를 고른 수열
 * N개의 자연수 중에는 중복된 수가 포함된다.
 * 오름차순
 */
public class Main {
    static int N;
    static int M;
    static int[] arr;
    static int[] result;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        result = new int[M];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        solve(0);

        System.out.print(sb);
    }
    static void solve(int size) {
        if (size == M) {
            for (int n : result) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && arr[i] != prev) {
                result[size] = arr[i];
                visited[i] = true;
                solve(size + 1);
                visited[i] = false;
                prev = arr[i];
            }
        }
    }
}
