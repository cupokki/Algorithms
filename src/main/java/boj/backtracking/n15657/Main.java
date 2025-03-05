package boj.backtracking.n15657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (8)
 * https://www.acmicpc.net/problem/15657
 * N개의 자연수 중, 길이 M의 수열을 만든다.
 * 같은 수를 여러번 골라도 된다.
 * 비내림차순 수열이어야한다.
 *  => 길이가 K인 수열 A가 A1 <= A2<= ... <= Ak인 수열
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
            if (size >= 1 && arr[i] < result[size - 1])
                continue;
            result[size] = arr[i];
            solve(size + 1);
        }

    }
}
