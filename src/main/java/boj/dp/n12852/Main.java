package boj.dp.n12852;

import java.util.*;

/**
 * https://www.acmicpc.net/problem/12852
 * 1로 만들기 2
 *
 * 정수 X(<=10^6)에 사용할 수 있는 연산 3가지
 *  1. x가 3으로 나누어 떨어지면 3으로 나눈다.
 *  2. x가 2로 나누어 떨어지면, 2로 나눈다.
 *  3. 1을 뺀다.
 *
 * 최소 연산으로 1을 만들라
 * 출력의 첫째줄에 최솟값, 둘쨰 줄에 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다.
 * 3시 5분
 *
 *
 */
//public class Main {
//    static int min = Integer.MAX_VALUE;
//    static int[] result = new int[1000000]; // 실제로 다 쓸 일 없음
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//
//        int[] list = new int[1000000];
//        dfs(N, list, 0 );
//
//        System.out.println(min);
//        for(int i = 0; i <= min; i++ ) {
//            System.out.print(result[i] + " ");
//        }
//        System.out.println();
//    }
//
//    static void dfs(int n, int[] list, int size) {
//        list[size++] = n;
//        if (size > min) {
//            return;
//        }
//        if (n <= 0) {
//            return;
//        }
//        if (n == 1) {
//            if (min > size - 1) {
//                min = size - 1;
//                for (int i = 0; i <= min; i++) {
//                    result[i] = list[i];
//                }
//            }
//            return;
//        }
//
//        if (n % 3 == 0) {
//            dfs(n / 3, list, size);
//        }
//        if (n % 2 == 0) {
//            dfs(n / 2, list, size);
//        }
//        dfs(n - 1, list, size);
//    }
//}
//

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        int[] dp = new int[x + 1];
        int[] result = new int[x + 1];

        for (int i = 2; i <= x ; i++) {
            dp[i] = dp[i - 1] + 1;
            result[i] = i - 1;
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                result[i] = i / 2;
            }
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                result[i] = i / 3;
            }
        }

        System.out.println(dp[x]);

        for (int i = x; i > 0;) {
            System.out.print(i + " ");
            i = result[i];
        }
    }
}