package boj.backtracking.n15655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M(6)
 * https://www.acmicpc.net/problem/15655
 * N개의 자연수 중 M개의 자연수를 뽑는다. 중복은 제외한다.
 */
public class Main {
    static int N;
    static int M;
    static int[] arr;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[M];

        Arrays.sort(arr);

        solve(0, 0);

        System.out.print(sb);
    }

    static void solve(int i, int size) {
        if (size == M) {
            for (int n : result) {
                sb.append(n + " ");
            }
            sb.append("\n");
            return;
        }
        if (i == N) return;

        result[size] = arr[i];
        solve(i + 1, size + 1);
        solve(i + 1, size);
    }
}
