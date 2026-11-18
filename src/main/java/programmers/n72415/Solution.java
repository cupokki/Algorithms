package programmers.n72415;

import java.util.*;

public class Solution {
    /*
    4x4크기 공간에 8장의 캐릭터가 두장씩 무작위로 배치ㅇ되어있다.
    2개를 골랐을때 같은 그림이면 보드에서 소거된다.
    다르다면 다시 뒤집힌다.
    다 뒤집으면 게임이 끝난다

    Goal
    - 최소 키 조작횟수로 모든 카르를 제거한다.

    Given Information
    - 일부 카드앞면을 이미 알고있다.
    - '커서'가 존재
        - ctrl + 상하좌우의 가장 가까운 카드로 이동 (cost 1)
        - 기본 상하좌우
        - 엔터로 카드 뒤집기(cost 1)
    - 4x4 공간
    - 8종의 캐릭터 2쌍 씩

    */
    int min;
    int[] order;
    boolean[] visited;
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        min = Integer.MAX_VALUE;
        order = new int[8];
        visited = new boolean[8];
        dfs(board, 0, r, c);

        return answer;
    }

    void dfs (int[][] board, int depth, int r, int c) {
        if (depth == order.length)  {
            min = Math.min(getCost(board, r, c), min);
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;
                dfs(board, depth + 1, r, c);
                visited[i] = false;
            }
        }
    }

    int getCost(int[][] board, int r, int c) {
        int cost = 2; // 기본 두번의 엔터



        return cost;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        //14
        System.out.println(sol.solution(new int[][]{
                {1, 0, 0, 3},
                {2, 0, 0, 0},
                {0, 0, 0, 2},
                {3, 0, 1, 0}
        }, 1, 0));

        // 16
        System.out.println(sol.solution(new int[][]{
                {3, 0, 0, 2},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {2, 0, 0, 3}
        }, 0, 1));
    }
}
