package boj.n17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17404
 * RGB 거리 2
 *
 * 1번 집의 색은 2, N과 다르다.
 * N번 집의 색은 N-1, 1번고ㅓㅏ 다르다.
 * i번째 집은 i-1, i+1과 다르다.
 * => 쉽게말해 인접한 색 다 달라야 한다.
 */
public class Main {
    static int N;
    static int[][] costs;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][3];


        for (int i = 0; i < N; i++) {
            costs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        dfs(0, 0);

        System.out.println(min);
    }

    static void dfs(int depth, int sum) {
        if (depth == N) {
            min = Math.min(min, sum);
            return;
        }

        if (sum > min) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            dfs(depth + 1, sum + costs[depth][i]);
        }
    }
}