package boj.implement.n2740;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 행렬 곱셈
 * https://www.acmicpc.net/problem/2740
 *
 * NM 크기의 행렬A 와 MK크기의 행렬 B가 주어졌을때 두 행렬을 곱하는 프로그램
 */
public class Main {
    public static void main(String[] args) throws IOException {

        int N, M, K;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] matA = new int[N][M];
        for (int i = 0; i < N; i++) {
            matA[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] matB = new int[M][K];
        for (int i = 0; i < M; i++) {
            matB[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] matC = new int[N][K];
        for (int i = 0; i < N; i++ ) {
            for (int j = 0; j < K; j++) {
                for (int l =0; l < M; l++ ) {
                    matC[i][j] += matA[i][l] * matB[l][j];
                }
                System.out.print(matC[i][j] + " ");
            }
            System.out.print("\n");
        }


    }
}
