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
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static boolean solution(int[][] key, int[][] lock) {
        int n = key.length;
        int m = key[0].length;

//        int[] lockFlag = new int[n];
//        int[] keyFlag = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            keyFlag[i] |= 1 << i;
//            lockFlag[i] |= 1 << i;
//        }

        for (int d = 0; d < 4; d++) { // 4방 회전
            int[][] movedKey = new int[n][m];
            for (int i = 0; i < n; i++) { // y축이동 = n * 2
                for (int j = 0; j < m; j++) { // x축이동 = m * 2
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (nr >= 0 && nc >= 0 && nr <n && nc < m)
                        movedKey[nr][nc] = key[i][j];
                }
            }
            if (unlock(n, m, movedKey, lock)) return true;
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