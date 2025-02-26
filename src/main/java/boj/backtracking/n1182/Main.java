package boj.backtracking.n1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1182
 * 부분 수열의 합
 *
 * N개의 정수로 이루어진 수열, 크기가 양수인 부분 수열 중에 그 수열의 원소를 다 더한 값이 S가 되는
 * 경우의 수를 구하라.
 */
public class Main {
    static int N; // [1, 20]
    static int S; // [-1000000, 1000000]
    static int count = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        if (S == 0) count--; //S == 0일때 바로, count 되어버림

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        solve(0, 0);
        
        System.out.println(count);
    }
    static void solve(int i, int sum) {
        if (i == N) {
            if (sum == S)
                count++;
            return;
        }
        solve(i + 1, sum + arr[i]); // 더하기
        solve(i + 1, sum); // 더하지 않기
    }
}
