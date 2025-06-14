package boj.dp.n1562;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1562
 * 계산수
 * 45656, 인접한 자리의 차가 모두 1이다. 이런 수를 계단수라 한다.
 * 길이 N이 주어질때, 0부터 9까지 수가 모두 등장하며 계단수인 것의 개수를 구하라.
 * 0으로 시작하면 계단수가 아니다.
 *
 * 0은 첫째 자리일때 인접한 수가 반드시 2개이어야함
 *
 * n = 1) 0
 * n = 2) 0
 * ...
 * n = 9) 0
 * n = 10) 1 0123456789
 *         9 876543210
 * n = 11) 1 0123456789 8 => 1
 * n = 12) 1 0123456789 89, ...87 => 2
 * n = 13) 1 0123456789 898, 878, 876 => 3
 * n = 14) 1 0123456789
 */
public class Main {
    static int N;
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {

        }
    }
}
