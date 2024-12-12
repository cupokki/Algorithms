package boj.simulation.n12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//        moveBlocks(3);

        dfs(MAX_MOVE);
        System.out.println(maxNum);
    }

    static void dfs(int depth) {
        if (depth == 0) {
            // 모든 방향 검토
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] original = board.clone();
            moveBlocks(dir);
            dfs(depth - 1);
            board = original;// restore
        }
    }

    static void moveBlocks(int dir) {

    }

    static void mergeLine(int dir, int r, int c) {
        int nr = r, nc = c;
        int dr = DIRECTIONS[dir][0];
        int dc = DIRECTIONS[dir][1];

        //merge
        // 이동방향 역순으로 블록 병합
        // 0 2 2 0 4
        nr += dr;
        nc += dc;
        while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
            // 나와 같은것 못찾으면 포인터를 현재 탐색대상으로하고 다시 반복
            if (board[nr][nc] != 0 && board[r][c] != board[nr][nc]) {
                r = nr;
                c = nc;
            } else if (board[nr][nc] != 0 && board[r][c] == board[nr][nc]) { // 나와 같은거 찾으면 거기에 내 값 더함, 그리고 그거 다음으로 포인터 이동
                board[r][c] += board[nr][nc];
                board[nr][nc] = 0;
                nr = r = nr + dr;
                nc = c = nc = dc;
            }

            nr += dr;
            nc += dc;
        }

    }

    static boolean moveLine(int dir, int r, int c) {
        boolean moved = false;
        int nr = r, nc = c;
        int dr = DIRECTIONS[dir][0];
        int dc = DIRECTIONS[dir][1];
        nr += dr;
        nc += dc;
        while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
            if(board[r][c] != 0) {
                r += dr;
                c += dc;
                nr = r + dr;
                nc = c + dc;
                continue;
            }
            if(board[nr][nc] != 0) {
                moved = true;
                board[r][c] = board[nr][nc];
                board[nr][nc] = 0;
                r += dr;
                c += dc;
            }
            nr += dr;
            nc += dc;
        }
        return moved;
    }

}