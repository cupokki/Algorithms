package boj.simulation.n12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12100
 * 2048 (Easy)
 * 4 by 4
 * 이미 보드위에 있는 블록 전체를 한방향으로 벽까지 이동시켜 합한다.
 * 이때 같은 숫자 블록이 닿는다면 합하여 끝까지 밀어넣는다.(연쇄적으로 합해지지는 않는다.)
 * 최대 5번 이동시켜 얻을 수 있는 가장 큰 블록
 */
public class Main {
    static int[][] DIRECTIONS = {
            {-1, 0},// 위
            {1, 0}, // 아래
            {0, -1},  // 왼쪽
            {0, 1}   // 오른쪽
    };
    static final int MAX_MOVE = 5;
    static int N;
    static int[][] board;
    static int maxNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxNum = Math.max(board[i][j], maxNum);
            }
        }
        dfs(MAX_MOVE, board);
        System.out.println(maxNum);
    }

    static void dfs(int depth, int[][] board) {
        if (depth == 0) {
            return;
        }

        // 4방향 검토
        for (int dir = 0; dir < 4; dir++) {
            int[][] newBoard = moveBlocks(dir, board);
            dfs(depth - 1, newBoard);
        }
    }

    static int[][] moveBlocks(int dir, int[][] currentBoard) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            newBoard[i] = Arrays.copyOf(currentBoard[i], N);
        }

        boolean[][] merged = new boolean[N][N];

        int dr = DIRECTIONS[dir][0];
        int dc = DIRECTIONS[dir][1];

        int r, c;
        for (int i = 0; i < N; i++) { // 이동방향 한줄씩 처리한다.

//            switch (dir) {
//                case 0: r = N - 1; c = i; break;   // 아래에서 위로 {-1, 0}
//                case 1: r = 0; c = i; break;   // 위에서 아래로 {1, 0}
//                case 2: r = i; c = N - 1; break;   // 오른쪽에서 왼쪽으로 {0, -1}
//                case 3: r = i; c = 0; break;   // 왼쪽에서 오른쪽으로 {0, 1}
//                default: return new int[0][0]; // 예외 없음 가능성x
//            }
            // get start index
            r = (dir == 0) ? N - 1 : (dir == 1) ? 0 : i;
            c = (dir == 2) ? N - 1 : (dir == 3) ? 0 : i;


            // merge and move
            while (r >= 0 && c >= 0 & r < N && c < N) {
                int nr = r + dr;
                int nc = c + dc;
                while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
                    if (newBoard[r][c] == 0 && newBoard[r][c] != newBoard[nr][nc]) { //move
                        newBoard[r][c] = newBoard[nr][nc];
                        newBoard[nr][nc] = 0;
                    } else if (!merged[r][c] && newBoard[r][c] != 0 && newBoard[r][c] == newBoard[nr][nc]) {// merge
                        newBoard[r][c] *= 2;
                        maxNum = Math.max(maxNum, newBoard[r][c]); // 최댓값 비교
                        newBoard[nr][nc] = 0;
                        merged[r][c] = true;
                        break;
                    } else if (newBoard[r][c] != 0 && newBoard[nr][nc] != 0) {
                        break;
                    }
                    nr += dr;
                    nc += dc;
                }
                r += dr;
                c += dc;
            }
        }

        return newBoard;
    }
}

