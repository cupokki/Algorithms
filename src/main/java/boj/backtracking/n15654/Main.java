package boj.backtracking.n15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M(5)
 * https://www.acmicpc.net/problem/15654
 *
 * N개의 자연수중에 M개를 고른 수열
 */
public class Main {
    static int N;
    static int M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        int[] result = new int[N];

        solve(result, 0,  0);

        System.out.print(sb);
    }
    static void solve(int[] result, int i, int size) {
            if (size == M) {
                for (int n : result) {
                    if(n != 0) {
                        sb.append(n + " ");
                    }

                }
                sb.append("\n");
                return;
            }
        for (int idx = 0; idx < N; idx++) {
            if(contain(result, size, arr[idx]))
                continue;

            result[size] = arr[idx];
            solve(result, i + 1, size + 1);
            result[size] = 0;
        }

    }
    static boolean contain(int[] arr, int n, int target) {
        for (int i = 0; i <= n; i++) {
            if (arr[i] == target){
                return true;
            }
        }
        return false;
    }
}
