package programmers.n84021;

import java.util.*;

public class Solution {
    /*
    빈칸이 있는 게임보드에 테이블위에 주어진 퍼즐조각을 맞추어 넣는다.
    게임 보드의 빈칸에 퍼즐을 채운 후에 퍼즐주위로, 빈칸이 발생해서는 안된다.
    위 규칙을 따르며, 최대 몇개의 칸을 채울 수 있는지 출력하라.

    테이블 위의 퍼즐조각과 보드의 빈칸은 각각 최대 6개이다.

    조각이 큰 퍼즐부터 맞춘다.

    ** 퍼즐하나를 맞추고 빈칸이 발생하면 안되므로 빈칸에 반드시 하나의 퍼즐만 들어간다. -> fit하게 맞아야한다.

    */
    class Puzzle {
        List<int[]> pos = new ArrayList<>();
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        int n = game_board.length; // 항상 정사각형 격자

        int[][] puzzles;
        List<int[][]> blanks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (game_board[i][j] == 0) {
                    blanks.add(getBlank(game_board, n, i, j));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 1) {
//                    puzzles.add(getPuzzle(n, table, i, j));
                }
            }
        }


//        Collections.sort(puzzles, Comparator.comparingInt(a -> a.pos.size()));

        return answer;
    }

    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};

    int[][] getBlank(int[][] game_board, int n, int r, int c) {
        int w = 0;
        int h = 0;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        List<int[]> coords  = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (!visited[nr][nc] && game_board[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    h = Math.max(h, nr);
                    w = Math.max(w, nc);
                    coords.add(new int[]{nr, nc});
                    q.offer(new int[]{nr, nc});
                }

            }
        }

        int[][] blank = new int[w][h];

        for (int[] coordinate : coords) {
            blank[coordinate[0]][coordinate[1]] = 1;
        }
        return blank;
    }

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
                if (!visited[nr][nc] && table[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    puzzle.pos.add(new int[]{nr - r, nc - c});
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return puzzle;
    }
}
