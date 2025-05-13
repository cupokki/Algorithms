package boj.n2847;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 게임을 만든 동준이
 * https://www.acmicpc.net/problem/2847
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];

        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0;
        for (int i = N - 2; i >= 0; i--) {
            if(score[i + 1] >  score[i])
                continue;

            int gap = Math.abs(score[i + 1] - score[i]) + 1;
            sum += gap;
            score[i] -= gap;
        }
        System.out.println(sum);
    }
}
