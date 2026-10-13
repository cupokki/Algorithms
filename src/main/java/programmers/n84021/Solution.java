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
        int size;
        int w, h;
        int[][] shape;

        Puzzle(int w, int h, int size) {
            this.w = w;
            this.h = h;
            this.size = size;
            this.shape = new int[w][h];
        }

        void rotate90DegreeClockwise() {
            int[][] rotated = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    rotated[h - j][w - i] = shape[i][j];
                }
            }

            this.shape = rotated;
            this.w = rotated[0].length;
            this.h = rotated.length;
        }
    }

    class Blank {
        int size;
        int w, h;
        int[][] shape;

        Blank(int w, int h, int size) {
            this.w = w;
            this.h = h;
            this.size = size;
            this.shape = new int[w][h];
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        int n = game_board.length; // 항상 정사각형 격자

        List<Puzzle> puzzles = new ArrayList<>();
        List<Blank> blanks = new ArrayList<>();

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
                    puzzles.add(getPuzzle(n, table, i, j));
                }
            }
        }

        for (int i = 0; i < blanks.size(); i++) {
            for (int j = 0; j < puzzles.size(); j++) {
                for (int d = 0; d < 4; d++) { // rotate
                    if (compareBlankToPuzzle(blanks.get(i), puzzles.get(j)))
                        answer++;
                    puzzles.get(j).rotate90DegreeClockwise();
                }
            }
        }

        return answer;
    }

    boolean compareBlankToPuzzle(Blank blank, Puzzle puzzle) {
        if (blank.size != puzzle.size) return false;
        if (blank.w != puzzle.w || blank.h != puzzle.h) return false;

        int w =  puzzle.w;
        int h = puzzle.h;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (blank.shape[i][j] != puzzle.shape[i][j]) return false;
            }
        }

        return true;
    }

    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};

    Blank getBlank(int[][] game_board, int n, int r, int c) {
        int r1 = n, r2 = n, c1 = 0, c2 = 0;

        boolean[][] visited = new boolean[n][n];
        visited[r][c] = true;

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
                    r1 = Math.min(r1, nr); c1 = Math.min(c1, nc);
                    r2 = Math.max(r2, nr); c2 = Math.max(c2, nc);
                    coords.add(new int[]{nr, nc});
                    q.offer(new int[]{nr, nc});
                }

            }
        }

        int w = c2 - c1;
        int h = r2 - r1;
        Blank blank = new Blank(w, h, coords.size());
        for (int[] coordinate : coords) {
            blank.shape[coordinate[0] - r1][coordinate[1] - c1] = 1;
        }
        return blank;
    }

    Puzzle getPuzzle(int n, int[][] table, int r, int c) {        int r1 = n, r2 = n, c1 = 0, c2 = 0;


        boolean[][] visited = new boolean[n][n];
        visited[r][c] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        List<int[]> coords  = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] pos = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (!visited[nr][nc] && table[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    r1 = Math.min(r1, nr); c1 = Math.min(c1, nc);
                    r2 = Math.max(r2, nr); c2 = Math.max(c2, nc);
                    coords.add(new int[]{nr - r, nc - c});
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        int w = c2 - c1;
        int h = r2 - r1;
        Puzzle puzzle = new Puzzle(w, h, coords.size());
        for (int[] coordinate : coords) {
            puzzle.shape[coordinate[0] - r1][coordinate[1] - c1] = 1;
        }
        return puzzle;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(
                new int[][] {
                        {1, 1, 0, 0, 1, 0},
                        {0, 0, 1, 0, 1, 0},
                        {0, 1, 1, 0, 0, 1},
                        {1, 1, 0, 1, 1, 1},
                        {1, 0, 0, 0, 1, 0},
                        {0, 1, 1, 1, 0, 0}
                },
                new int[][]{
                        {1, 0, 0, 1, 1, 0},
                        {1, 0, 1, 0, 1, 0},
                        {0, 1, 1, 0, 1, 1},
                        {0, 0, 1, 0, 0, 0},
                        {1, 1, 0, 1, 1, 0},
                        {0, 1, 0, 0, 0, 0}
                })); // 14
    }
}
