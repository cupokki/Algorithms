package boj.dp.n2631;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2631
 * 줄세우기
 * 번호가 1부터 N인 N 명의 사람
 * 번호가 순서대로 주어질때
 * 번호를 오름차순으로 정렬하기 위해 옮기는 최소 횟수를 구하라.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        System.out.println();
    }
}
