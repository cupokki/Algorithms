package boj.implement.n13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시험 감독
 * https://www.acmicpc.net/problem/13458
 * 시험장엔 감독관(B명 감독) 꼭 1명
 * 부감독(C명 감독)은 여러명 있어도 된다. -> 반드시 한명이 필요한건가
 * 필요한 감독관의 최솟값을 구하라.
 *
 */
public class Main {
    static int N;
    static int[] A; // 시험장의 인원수
    static int B, C;;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 1이상 100만 이하 자연수
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringTokenizer st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long sum = 0;
        for (int i = 0; i < N; i++) {
            int min = 1;
            if (A[i] - B > 0)
                min += (A[i] - B + (C - 1)) / C;
            sum += min;
        }

        System.out.println(sum);
    }
}
