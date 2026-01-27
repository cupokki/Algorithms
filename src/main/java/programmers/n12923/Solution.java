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
    public static int[] solution(long begin, long end) {
        int range = (int) (end - begin);
        int[] answer = new int[range + 1];
        Arrays.fill(answer, 1);
        if (begin == 1) answer[0] = 0;
        int maxNum = (int)end / 2; // 교체되는 숫자는 floor(end / 2)이하
        int startNum = (int)Math.sqrt(maxNum); // 최대 숫자의 - range
//        int startNum = maxNum - range + 1;
        for (int i = startNum; i <= maxNum; i++) {
            for (int j = 0; j <= range; j++) {
                int idx = (int)begin + j;
                if (idx < 2 * i) continue;
                if (idx % i == 0) {
                    answer[j] = i;
                }
            }
        }

        return answer;
    }

    static void print(int[] ans) {
        Arrays.stream(ans).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
    public static void main(String[] args) {
        print(solution(1, 10));
        print(solution(5, 10));
        //print(solution(1_000_000_000, 1_000_000_000));
        print(solution(999_999_999, 1_000_000_000));
        print(solution(2, 4));
    }
}
