package programmers.n258709;

import java.util.Arrays;

public class Solution {
    /*
   dice의 길이 n : 10이하, n은 2의 배수
   dice[i]의 길이는 6이며 원소는 100이하 자연수

   A와 B가 주사위를 n/2씩 주사위를 나누어 던져, 각각의 합을 구한다.
   합이 큰쪽이 승리, 같다면 무승부이다.
   A가 승리할 확률이 높도록 주사위를 가져간다. 이때 골라야하는 주사위번호를 오름차순으로 정렬하여 출력

     */
    public int[] solution(int[][] dice) {
        int[] answer = {};

        int n = dice.length;

        for (int i = 0; i < n; i++) {

        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        //[[1, 2, 3, 4, 5, 6], [3, 3, 3, 3, 4, 4], [1, 3, 3, 4, 4, 4], [1, 1, 4, 4, 5, 5]]
        System.out.println(Arrays.deepToString(new int[][]{
                {1, 2, 3, 4, 5, 6},
                {3, 3, 3, 3, 4, 4},
                {1, 3, 3, 4, 4, 4},
                {1, 1, 4, 4, 5, 5}
        }));// [1, 4]

        System.out.println(Arrays.deepToString(new int[][]{
                {1, 2, 3, 4, 5, 6},
                {2, 2, 4, 4, 6, 6}
        }));// [2]

        System.out.println(Arrays.deepToString(new int[][]{
                {40, 41, 42, 43, 44, 45},
                {43, 43, 42, 42, 41, 41},
                {1, 1, 80, 80, 80, 80},
                {70, 70, 1, 1, 70, 70}
        }));// [1, 3]
    }
}
