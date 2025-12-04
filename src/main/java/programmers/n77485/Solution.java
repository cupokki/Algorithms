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
        int min = R * C + 1; // 가능한 최대값

        int[][] temps = {
                {sr + 1, ec, matrix[sr][ec]},
                {er, ec - 1, matrix[er][ec]},
                {er - 1, sc, matrix[er][sc]},
                {sr, sc + 1, matrix[sr][sc]},

        };
        for (int[] temp: temps) {
            min = Math.min(min,temp[2]);
        }

        for (int c = ec; c > sc; c--) {
            matrix[sr][c] = matrix[sr][c - 1];
            min = Math.min(min, matrix[sr][c]);
        }

        for (int r = er ; r > sr; r--) {
            matrix[r][ec] = matrix[r - 1][ec];
            min = Math.min(min, matrix[r][ec]);
        }

        for (int c = sc; c < ec; c++) {
            matrix[er][c] = matrix[er][c + 1];
            min = Math.min(min, matrix[er][c]);
        }

        for (int r = sr; r < er; r++) {
            matrix[r][sc] = matrix[r + 1][sc];
            min = Math.min(min, matrix[r][sc]);
        }

        for (int[] temp: temps) {
            matrix[temp[0]][temp[1]] = temp[2];
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
