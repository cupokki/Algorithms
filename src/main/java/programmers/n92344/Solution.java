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

    NM 순회하며, 해당 점이 k[i]의 범위 내라면 계속 아니라면, 그만 둠, 이런식 가능하지않을까 
    
    r1 < i < r2 && c1 < j < c2 && board[i][j] > sum;
    좌표값 때문에 skill 써야하는데.
    */
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;

        int[][] sums = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) {
                    answer++;
                }
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