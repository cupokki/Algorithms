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
    int cardCnt;
    boolean[] used;
    List<int[]>[] pos; // 모든 카드의 위치

    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        min = Integer.MAX_VALUE;
        cardCnt = -1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardCnt = Math.max(cardCnt, board[i][j]);
                }
            }
        }

        pos = new ArrayList[cardCnt + 1];
        for (int i = 0; i <= cardCnt; i++) {
            pos[i] = new ArrayList<>();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    pos[board[i][j]].add(new int[]{i, j});
                }
            }
        }
        used = new boolean[cardCnt + 1];

        dfs(board, 0, r, c, 0);

        return min;
    }

    void dfs(int[][] board, int depth, int r, int c, int cost) {
        if (depth == cardCnt) {
            min = Math.min(cost, min);
            return;
        }

        for (int i = 1; i <= cardCnt; i++) {
            if (!used[i]) {
                used[i] = true;

                int[] nextPosA = pos[i].get(0);
                int[] nextPosB = pos[i].get(1);

                // r,c에서 i1카드->i2카드로 이동하는 경우
                int costA = getCost(board, r, c, nextPosA[0], nextPosA[1]) +
                        getCost(board, nextPosA[0], nextPosA[1], nextPosB[0], nextPosB[1]) +
                        2;
                // r,c에서 i2카드->i1카드로 이동하는 경우
                int costB = getCost(board, r, c, nextPosB[0], nextPosB[1]) +
                        getCost(board, nextPosB[0], nextPosB[1], nextPosA[0], nextPosA[1]) +
                        2;


                board[nextPosA[0]][nextPosA[1]] = 0;
                board[nextPosB[0]][nextPosB[1]] = 0;
                dfs(board, depth + 1, nextPosA[0], nextPosA[1], cost + costA);
                dfs(board, depth + 1, nextPosB[0], nextPosB[1], cost + costB);
                board[nextPosA[0]][nextPosA[1]] = i;
                board[nextPosB[0]][nextPosB[1]] = i;

                used[i] = false;
            }
        }
    }

    int[] dr = new int[]{1, -1, 0, 0};
    int[] dc = new int[]{0, 0, 1, -1};

    int getCost(int[][] board, int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) return 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 0}); // cur Row, cur column, cur cost
        boolean[][] visited = new boolean[4][4];
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == er && cur[1] == ec) {
                return cur[2];
            }
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, cur[2] + 1});
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur[0];
                int nc = cur[1];

                while (true) {
                    if (nr + dr[d] < 0 || nr + dr[d] >= 4 || nc + dc[d] < 0 || nc + dc[d] >= 4) {
                        break;
                    }

                    nr += dr[d];
                    nc += dc[d];

                    if (board[nr][nc] != 0) {
                        break;
                    }
                }
                if ((nr != cur[0] || nc != cur[1]) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, cur[2] + 1});
                }
            }
        }

        return -1;
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
