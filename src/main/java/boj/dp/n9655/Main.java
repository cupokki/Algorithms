package boj.dp.n9655;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 돌 게임
 * https://www.acmicpc.net/problem/9655
 *
 * A, B유저 2명이 N개의 돌을 1or 3개 가져간다
 * 마지막 돌을 가져가는 사람이 이긴다.
 * A가 먼저 시작할때, 이기는 사람을 구하라.
 *
 *
 * 짝수는 무조건 창영이가 이긴다.
 *  AB
 *  ABAB
 *  AAAB
 *  ABABAB
 *  AAABBB
 *  AAABAB
 *  ABBBAB
 *
 * 홀수는 무조건 상근이가 이긴다.
 *  A
 *  ABA
 *  AAA
 *  ABABA
 *  ABAAA
 *  AAABA
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N + 1];


        System.out.println(N % 2 != 0 ? "SK" : "CY");
    }
}