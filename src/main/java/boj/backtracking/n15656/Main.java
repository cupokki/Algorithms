package boj.backtracking.n15656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (7)
 * https://www.acmicpc.net/problem/15656
 *
 * N개의 자연수 중, M개를 고른 수열
 * 같은 수를 여러번 골라도 된다.
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
        result = new int[M];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        solve(0);

        System.out.print(sb);
    }
    static void solve(int size) {
        if (size == M) {
            for (int n : result) {
                sb.append(n + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            result[size] = arr[i];
            solve(size + 1);
        }

    }
}
