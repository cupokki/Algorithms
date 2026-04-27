package programmers.n92344;

import java.util.*;

public class Solution {
    /*
    적이 건물을 공격하고 건물은 공격받으면 내구도가 감소한다. 내구도가 0이하면 되면 파괴 상태이다.
    아군은 회복스킬로 내구도를 높인다(파괴된 건물의 내구도도 회복하여 되 살릴 수 있다.)
    공격과 회복은 항상 직사각형이다.
    
    skill : type, r1, c1, r2, c2, degree(수치)
    type == 1 : 공격
    type == 2 : 회복
    N,M은 각 100 이하 자연수.
    건물 내구도는 100이하 자연수
    
    회복은 1000이상이 가능한가?
    
    skill은 최대 25000수행되므로 시물레이션 가능한가?
    */
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;

        int[][] sums = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
            int degree = (s[0] == 1) ? -s[5] : s[5];

            sums[r1][c1] += degree;
            sums[r1][c2 + 1] -= degree;
            sums[r2 + 1][c1] -= degree;
            sums[r2 + 1][c2 + 1] += degree;
        }

        // 스위핑 (오른쪽에서 왼쪽)
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sums[i][j] += sums[i][j - 1];
            }
        }

        // 스위핑 (위에서 아래로)
        for (int j = 0; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                sums[i][j] += sums[i - 1][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sums[i][j] + board[i][j] > 0) answer++;
            }
        }

        return answer;
    }

     public static void main(String[] args) {
         System.out.println(solution(
             new int[][] {
                 {5,5,5,5,5},
                 {5,5,5,5,5},
                 {5,5,5,5,5},
                 {5,5,5,5,5}},
             new int[][] {
                 {1, 0, 0, 3, 4, 4},
                 {1, 2, 0, 2, 3, 2},
                 {2, 1, 0, 3, 1, 2},
                 {1, 0, 1, 3, 3, 1}
             }));
         System.out.println(solution(
             new int[][] {
                 {1, 2, 3},
                 {4, 5, 6},
                 {7, 8, 9}
             },
             new int[][] {
                 {1, 1, 1, 2, 2, 4},
                 {1, 0, 0, 1, 1, 2},
                 {2, 2, 0, 2, 0, 100}
             }));
     }
}