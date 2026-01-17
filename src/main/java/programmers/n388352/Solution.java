package programmers.n388352;

import java.util.Arrays;

public class Solution {
    /*
    정보 해독한다.
    1부터 n까지,  서로다른 정수 5개, 오름차순 코드를 맞춘다.
    해석 도구m번 시도 가능
     - 시도마다 서로다른 5개의 정수를 입력하면 몇가 포함되는지 반환
     - m번 수행 이후 가능한 조합의 수를 알고 싶다.

     n은 30이하
     */
    static boolean[] candidates;
    static boolean[] answer;
    static int N;
    public static int solution(int n, int[][]q, int[] ans) {
        N = n;
        candidates = new boolean[N + 1];
        answer = new boolean[N + 1];
        for (int i = 0; i < ans.length; i++) {
            answer[ans[i]] = true;
        }
        for (int[] query: q) {
            int result = func(query);
        }

        int num = 0;
        for (boolean b : candidates) {
            num += b ? 1 : 0;
        }

        return 0;
    }

    static int func(int[] query) {
//        boolean[] candidates = new boolean[N + 1];
        for (int i = 0; i < query.length; i++) {
            if (answer[query[i]])
                candidates[query[i]] = true;
        }

        int result = 0;
        for (int i = 0; i < candidates.length; i++) {
            if(candidates[i]) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var q1 =  new int[][] {
                {1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}
        };
        System.out.println(solution(10, q1, new int[]{2, 3, 4, 3, 3}));
        var q2 =  new int[][] {
                {2, 3, 9, 12, 13}, {1, 4, 6, 7, 9}, {1, 2, 8, 10, 12}, {6, 7, 11, 13, 15}, {1, 4, 10, 11, 14}
        };
        System.out.println(solution(15, q2, new int[]{2, 1, 3, 0, 1}));
    }
}
