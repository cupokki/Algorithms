package boj.n1074;

/**
 * Z
 * https://www.acmicpc.net/problem/1074
 *
 * 좌표가 사분면의 어디인가
 */
import java.util.Scanner;

public class Main {
    static int result = 0;
    static int R, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // (2^N x 2^N)
        R = sc.nextInt();
        C = sc.nextInt();

        int size = (int) Math.pow(2, N);
        solve(0, 0, size);
        System.out.println(result);
    }

    private static void solve(int r, int c, int size) {
        // 기본 크기가 1x1인 경우
        if (size == 1 && r == R && c == C) {
            return;
        }

        int newSize = size / 2;

        if (R < r + newSize && C < c + newSize) { // 1사
            solve(r, c, newSize);
        } else if (R < r + newSize && C >= c + newSize) { // 2사
            result += newSize * newSize; // 이전 사분면의 개수 추가
            solve(r, c + newSize, newSize); // 3사
        } else if (R >= r + newSize && C < c + newSize) {
            result += 2 * newSize * newSize; // 이전 사분면의 개수 추가
            solve(r + newSize, c, newSize);
        } else { // 4사
            result += 3 * newSize * newSize; // 이전 사분면의 개수 추가
            solve(r + newSize, c + newSize, newSize);
        }
    }
}