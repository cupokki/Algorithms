package boj.implement.n1652.n1747;

import java.io.*;
import java.util.*;
/**
 * 소수&팰린드롬
 * https://www.acmicpc.net/problem/1747
 * N[1,10^6]이 주어질때 N 보다 크거나 같고, 소수이면서 펠린드롬인 수 중 가장 작은 수를 구하라.
 */
public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        while (!(isPelindrome(N) && isPrime(N))) {
            N++;
        }
        System.out.println(N);
    }
    static boolean isPrime(int n) {
        if (n == 1) return false; // 0이하 케이스 없다.
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    static boolean isPelindrome(int n) {
        char[] arr = Integer.toString(n).toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - 1- i]) {
                return false;
            }
        }
        return true;
    }
}