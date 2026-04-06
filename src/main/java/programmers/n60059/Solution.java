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
    static int[] dr = {0, 1, 0, -1, 0};
    static int[] dc = {0, 0, 1, 0, -1};
    public static boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;

        int[][][] rotatedLock = new int[4][m][m];
        rotatedLock[0] = lock;

        // 이거 정방향이 아닌데 이래해도 작동하려나?
        // i, j가 다를 수있다. 이방법은 안되고 미리 돌려놓는 다른 방법을 생각해보자.

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                 rotatedLock[1][j][n - 1 - i] = lock[i][j];
                 rotatedLock[2][n - 1 - i][n - 1 - j] = lock[i][j];
                 rotatedLock[3][n - 1 - j][i] = lock[i][j];
            }
        }

        for (int dir = 0; dir < 5; dir++) { // 4방 회전
            int[][] movedKey = new int[n][n];
            for (int i = 0; i < n; i++) { // y축이동 = n * 2
                for (int j = 0; j < n; j++) { // x축이동 = m * 2
                    int nr = i + dr[dir];
                    int nc = j + dc[dir];
                    if (nr >= 0 && nc >= 0 && nr <n && nc < m)
                        movedKey[nr][nc] = key[i][j];
                }
            }
            for (int rotate = 0; rotate < 4; rotate++) {
                if (unlock(n, m, movedKey, rotatedLock[rotate])) return true;
            }
        }

        return false;
    }
    static boolean unlock(int n, int m, int[][] key, int[][] lock) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (key[i][j] + lock[i][j] != 1) return false;
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