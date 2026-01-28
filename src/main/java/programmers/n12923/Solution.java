package programmers.n12923;

import java.util.Arrays;

public class Solution {
    /*
    일자도로가 블록으로 나뉘어져 있다.
    기본적으로 블록엔 0이 적혀있고, 숫자를 채워 나가려 한다.
    블록의 번호n일때, n * 2, n * 3, n * 4 ...

    begin, end 는 0부터 10억이하의 정수
    시뮬레이션시 -> O(N^2)
     */
//    public static int[] solution(long begin, long end) {
//        int range = (int) (end - begin);
//        int[][] answer = new int[range + 1][range + 1];
//        if (range >= 1) Arrays.fill(answer[1], 1);
//        if (begin == 1 && range > 1) answer[1][0] = 0;
//
//        for (int i = 2; i <= range; i++) {
//            for (int j = 0; j <= range; j++ ) {
//                int temp = j + (int)begin;
//                if (j > 0 && temp >= 2 * i - 1 && temp % i == 0) { // i * i인 곳 부터, i 간격으로
//                    answer[i][j] = i;
//                } else {
//                    answer[i][j] = answer[i - 1][j];
//                }
//            }
//        }
//
//        return answer[range];
//    }
//    public static int[] solution(long begin, long end) {
//        int range = (int) (end - begin) + 1;
//        int[] answer = new int[range];
//
//        Arrays.fill(answer, 1);
//        if (begin == 1) answer[0] = 0;
//        int maxNum = (int)end / 2; // 교체되는 숫자는 floor(end / 2)이하
//        if (maxNum > 10_000_000) maxNum = 10_000_000;
//        int startNum = (int)Math.ceil(Math.sqrt(begin)); // 반드시 1이 포함되는 경우가 있다? -> 소수
//        for (int i = startNum; i <= maxNum; i++) {
//            for (int j = 0; j < range; j++) {
//                int idx = (int)begin + j;
//                if (idx < 2 * i) continue;
//                if (idx % i == 0) {
//                    answer[j] = i;
//                }
//            }
//        }
//
//        return answer;
//    }
    public static int[] solution(long begin, long end) {
        int range = (int) (end - begin) + 1;
        int[] answer = new int[range];

        for (int i = 0; i < range; i++) {
            // 문제의 조건을 다시 해석하면, answer[i]는 n를 제외한 가장 큰 약수
            int n = (int)begin + i;
            int factor = 1; // n이 소수면 항상 1.
            for (int j = 2; (long)j * j <= n; j++) {
                if (n % j == 0) {
                    if (n / j <= 10000000) {
                        factor = n / j;
                        break;
                    } else {
                        factor = j;
                    }
                }
            }
            answer[i] = factor;
        }

        if (begin == 1) answer[0] = 0;

        return answer;
    }

    static void print(int[] ans) {
        Arrays.stream(ans).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
    public static void main(String[] args) {
        print(solution(1, 10));
        print(solution(5, 10));
        print(solution(1_000_000_000, 1_000_000_000));
        print(solution(999_999_999, 1_000_000_000));
        print(solution(1, 4));
        print(solution(1, 1));
    }
}
