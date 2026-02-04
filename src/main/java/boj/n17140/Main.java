package boj.n17140;


import java.util.Scanner;

/**
 * 33배열 A. 인덱스는 1부터
 * 초마다 배열 연산 적용
 *  R: 모든 행에 정렬을 수행, 행 개수 >= 열의 개수인 경우 적용
 *  c: 모든 열에 대해 정렬, 행의 개수 < 열의 개수인경우 적용
 */
public class Main {
    static final int MAX = 100;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int k = sc.nextInt();
        int[][] A = new int[MAX + 1][MAX + 1];

        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= MAX; j++) {
                A[i][j] = 0;
            }
        }


        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        System.out.println(A[r][c]);
    }

    static void replace(int[][] A, int r, int c) {

    }
}
