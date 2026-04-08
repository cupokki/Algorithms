package programmers.n60059;

import java.util.*;

public class Solution {
    /*
    자물쇠와 열쇠의 홈과 돌기
    열쇠의 홈은 자물쇠의 돌기에 영향없으나. 돌기는 영향을 준다는 점
    열쇠를 회전하거나 이동하여 문을 열어라.
    열쇠와 자물쇠가 주어질때, 문을 열수 있는지 판별하는 함수를 구현한다.

    자물쇠와 열쇠는 각 3이상 20이하의 변을 가진 행렬이다.
    홈은 0, 돌기는 1

    둘을 합하여 모든 면이 채워지도록 한다.
     */
    public static boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;

        int[][][] rotatedKey = new int[4][m][m];
        rotatedKey[0] = key;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotatedKey[1][j][m - 1 - i] = key[i][j];
                rotatedKey[2][m - 1 - i][m - 1 - j] = key[i][j];
                rotatedKey[3][m - 1 - j][i] = key[i][j];
            }
        }

        for (int d = 0; d < 4; d++) {
            for (int i = -(m - 1); i < n; i++) {
                for (int j = -(m - 1); j < n; j++) {
                    if (unlock(n, m, i, j, rotatedKey[d], lock)) return true;
                }
            }
        }

        return false;
    }
    static boolean unlock(int n, int m, int kr, int kc, int[][] key, int[][] lock) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int r = i - kr;
                int c = j - kc;
                int kv = (r >= 0 && r < m && c >= 0 && c < m) ? key[r][c] : 0;
                if (lock[i][j] + kv != 1) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}},
                new int[][]{{1, 1, 1,}, {1, 1, 0}, {1, 0, 1}}
        ));
    }

}