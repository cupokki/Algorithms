package boj.divideAndConquer.n11401;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/11401
 * 이항 계수 3
 * 자연수 N과 K가 주어졌을때 C(N,K)를 1,000,000,007로 나눈 나머지를 구하라
 * 1 ≤ N ≤ 4,000,000, 0 ≤ N ≤ K
 * 파스칼 dp로 해결하나?
 * 16 000 000 000 000 * 4인데?
 *
 * nCk =  n!/(k!(n-k)!) (K != 0, k != n)을 활용한다.
 * 
 * https://www.acmicpc.net/problem/11050  선행하여 풀것
 */
public class Main {
    static final int MOD = 1_000_000_007; // 소수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int result = 0;
        if(N == 0 || K == 0){
            System.out.println(result);
            return;
        }


    }
}
