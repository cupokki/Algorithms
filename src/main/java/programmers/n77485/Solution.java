package programmers.n77485;

import java.util.Arrays;

public class Solution {
    /*
    쿼리 배열의 좌표에 해당하는 영역의 테두리를 시계방향으로 90도 회전한다.
    각 쿼리로 이동한 숫자중 가장 작은 수를 결과에 추가한다.
     */
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        R = rows; C = columns;
        matrix = new int[R][C];
        int num = 1;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                matrix[r][c] = num++;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int[] pos = queries[i];
            answer[i] = rotateMatrix(
                    pos[0] - 1, pos[1] - 1,
                    pos[2] - 1, pos[3] - 1
            );
        }

        return answer;
    }
    static int[][] matrix;
    static int R, C;
    static int rotateMatrix(int sr, int sc, int er, int ec) {
        int prev = matrix[sr][sc];
        int min = prev;

        for (int c = sc + 1; c <= ec; c++) {
            int cur = matrix[sr][c];
            matrix[sr][c] = prev;
            prev = cur;
            min = Math.min(min, prev);
        }

        for (int r = sr + 1; r <= er; r++) {
            int cur = matrix[r][ec];
            matrix[r][ec] = prev;
            prev = cur;
            min = Math.min(min, prev);
        }

        for (int c = ec - 1; c >= sc; c--) {
            int cur = matrix[er][c];
            matrix[er][c] = prev;
            prev = cur;
            min = Math.min(min, prev);
        }

        for (int r = er - 1; r >= sr; r--) {
            int cur = matrix[r][sc];
            matrix[r][sc] = prev;
            prev = cur;
            min = Math.min(min, prev);
        }

        return min;
    }

    public static void main(String[] args) {
        int[][] q1 = {
                {2, 2, 5, 4},
                {3, 3, 6, 6},
                {5, 1, 6, 3}
        };
        int[][] q2 = {
                {1, 1, 2, 2},
                {1, 2, 2, 3},
                {2, 1, 3, 2},
                {2, 2, 3, 3}
        };
        int[][] q3 = {
                {1, 1, 100, 97},
        };
//        Arrays.stream(solution(6, 6, q1)).forEach(i -> System.out.print(i+" "));
        Arrays.stream(solution(3, 3, q2)).forEach(i -> System.out.print(i+" "));
//        Arrays.stream(solution(100, 97, q3)).forEach(i -> System.out.print(i+" "));

    }
}
