package programmers.n84021;

import java.util.*;

public class Solution {
    /*
    빈칸이 있는 게임보드에 테이블위에 주어진 퍼즐조각을 맞추어 넣는다.
    게임 보드의 빈칸에 퍼즐을 채운 후에 퍼즐주위로, 빈칸이 발생해서는 안된다.
    위 규칙을 따르며, 최대 몇개의 칸을 채울 수 있는지 출력하라.

    테이블 위의 퍼즐조각과 보드의 빈칸은 각각 최대 6개이다.

    조각이 큰 퍼즐부터 맞춘다.
    */
    class Puzzle {
        List<int[]> pos = new ArrayList<>();
    }
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        int n = game_board.length; // 항상 정사각형 격자

        List<Puzzle> puzzles = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 1) {
                    puzzles.add(getPuzzle(n, table, i, j));
                }
            }
        }

        Collections.sort(puzzles, Comparator.comparingInt(a -> a.pos.size()));



        return answer;
    }

    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};

    Puzzle getPuzzle(int n, int[][] table, int r, int c) {
        Puzzle puzzle = new Puzzle();
        puzzle.pos.add(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[r][c] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    puzzle.pos.add(new int[]{nr - r, nc - c});
                }
            }
        }
        return puzzle;
    }
}
