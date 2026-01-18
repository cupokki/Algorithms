package programmers.n388352;

public class Solution {
    /*
    정보 해독한다.
    1부터 n까지,  서로다른 정수 5개, 오름차순 코드를 맞춘다.
    해석 도구m번 시도 가능
     - 시도마다 서로다른 5개의 정수를 입력하면 몇가 포함되는지 반환
     - m번 수행 이후 가능한 조합의 수를 알고 싶다.

     n은 30이하
     ans 는 시스템의 응답
     m은 질문 횟 수, 10이하의 자연수

     C(10, 2) = 45 * C(5, 2);
     // 각 query 를 비교한다. 어떻게?

     C(30, 5)는 약 14만? 가능한가 -> 길이가 5로 제한되니까 dfs 불가능하진 않겠네
     n이하의 자연수 5개를 선택하는 dfs돌려서 q와 ans 조건에 만족하는 것의 개수를 직접 세는 방식?
     */
    static int[] answer;
    static int N, M;
    static int result;
    static int[] qMask;
    public static int solution(int n, int[][] q, int[] ans) {
        N = n;
        M = q.length;
        answer = ans;
        result = 0;

        qMask = new int[M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 5; j++) {
                qMask[i] |= 1 << q[i][j];
            }
        }

        dfs(0, 1, 0);

        return result;
    }

    static void dfs(int depth, int start, int bitmask) {
        if (depth == 5) {
            for (int i = 0; i < M; i++) {
                if (Integer.bitCount(bitmask & qMask[i]) != answer[i])
                    return;
            }
            result++;
//            for (int i = 0; i < 5; i++) {
//                System.out.print(String.format("%3d", selected[i]));
//            }
//            System.out.println();
            return;
        }

        for (int i = start; i <= N; i++) {
            dfs(depth + 1, i + 1, bitmask | (1 << i));
        }
    }


    public static void main(String[] args) {
        var q1 = new int[][]{
                {1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}
        };
        System.out.println(solution(10, q1, new int[]{2, 3, 4, 3, 3}));
        var q2 = new int[][]{
                {2, 3, 9, 12, 13}, {1, 4, 6, 7, 9}, {1, 2, 8, 10, 12}, {6, 7, 11, 13, 15}, {1, 4, 10, 11, 14}
        };
        System.out.println(solution(15, q2, new int[]{2, 1, 3, 0, 1}));
        var q3 = new int[][]{
                {1, 2, 3, 4, 5}
        };
        System.out.println(solution(10, q3, new int[]{1}));
    }
}
