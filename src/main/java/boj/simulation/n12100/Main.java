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
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++) {
                    maxNum = Math.max(maxNum, board[i][j]);
                }
            }
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
        for (int i = 0; i < N; i++ ) {
            newBoard[i] = Arrays.copyOf(currentBoard[i], N);
        }
        int dr = DIRECTIONS[dir][0];
        int dc = DIRECTIONS[dir][1];
        int sr, sc;
        int r, c;
        for (int i = 0; i < N; i++){ // N개의 라인
            // get start index
            switch (dir) {
                case 0 : sr = N - 1; sc = i; break;   // 아래에서 위로 {-1, 0}
                case 1 : sr = 0; sc = i; break;   // 위에서 아래로 {1, 0}
                case 2 : sr = i; sc = N - 1; break;   // 오른쪽에서 왼쪽으로 {0, -1}
                case 3 : sr = i; sc = 0; break;   // 왼쪽에서 오른쪽으로 {0, 1}
                default: return null; // 예외 없음 가능성x
            }

            //merge
            r = sr;
            c = sc;
            int nr = r + dr;
            int nc = c + dc;
            while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
                if (newBoard[r][c] == 0) { // 현위치가 0이면 인덱스를 다음 위치로
                    r += dr;
                    c += dc;
                    nr = r + dr;
                    nc = c + dc;
                }
                if(newBoard[r][c] == newBoard[nr][nc]) { // 다음 위치와 같다면 합침
                    newBoard[r][c] *= 2;
                    newBoard[nr][nc] = 0;
                    r += dr;
                    c += dc;
                    nr = r + dr;
                    nc = c + dc;
                } else if (newBoard[nr][nc] != 0 && newBoard[r][c] != newBoard[nr][nc]) {
                    r += dr;
                    c += dc;
                    nr = r + dr;
                    nc = c + dc;
                }
                nr += dr;
                nc += dc;

            }

            //move
            r = sr;
            c = sc;
            nr = r + dr;
            nc = c + dc;
            while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
                if (newBoard[r][c] != 0) {
                    nr = r += dr;
                    nc= c += dc; // 현위치 인덱스 다음으로
                }
                if (newBoard[r][c] == 0 && newBoard[nr][nc] != 0) { // 현위치가 0이라면 가장 앞에 것을 가져옴
                    newBoard[r][c] = newBoard[nr][nc];
                    newBoard[nr][nc] = 0;
                    nr = r += dr;
                    nc = c += dc; // 현위치 인덱스 다음으로
                }
                nr += dr;
                nc += dc;
            }
        }
        return newBoard;
    }
}

