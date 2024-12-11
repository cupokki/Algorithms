package boj.simulation.n12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
            }
        }
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
        boolean moved = false;
        if (dir == 0) { // 위로
            for (int i = 0; i < N; i++)
                moved = moveLine(dir, N - 1, i); // 아랫줄을 윗방향으로
        } else if (dir == 1) {
            for (int i = 0; i < N; i++)
                moved = moveLine(dir, 0, i); // 윗 줄을 아래방향으로
        } else if (dir == 2) {
            for (int i = 0; i < N; i++)
                moved = moveLine(dir, i, N - 1); // 오른쪽줄을 왼쪽으로
        } else if (dir == 3) {
            for (int i = 0; i < N; i++)
                moved = moveLine(dir, i, 0); // 왼쪽줄을 오른쪽으로
        }else {
            return;
        }
        if(!moved)
            moveBlocks(dir + 1);
    }

    // {0, 4, 2, 2} 배열을 <- 방향으로 민다면?
    // 2에서 시작하는게 아니라 0에서 시작해서 수를 당겨와야한다.
    static boolean moveLine(int dir, int r, int c) {
        int nr = r, nc = c;
        boolean moved = false;
        while (true) {
            nr += DIRECTIONS[dir][0];
            nc += DIRECTIONS[dir][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                break;
            }
            if (board[nr][nc] == 0)
                continue;

            board[r][c] += board[nr][nc]; // merge and pull
            maxNum = Math.max(maxNum, board[r][c]);
            if(board[r][c] != 0) moved = true;
            // 이미 한번 머지했거나, 끝까지 땡긴것일 경우
                r += DIRECTIONS[dir][0];
                c += DIRECTIONS[dir][1];

            board[nr][nc] = 0;
        }
        return moved;
    }
}