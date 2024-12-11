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
            {0, -1},// 위
            {0, 1}, // 아래
            {-1, 0},  // 왼쪽
            {1, 0}   // 오른쪽
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

    // depth는 회차
    static void dfs(int depth) {
        if (depth == 0) {
            // 모든 방향 검토
            return;
        }

        for (int d = 0; d < 4; d++) {
            boolean changed = false;
            int[][] origin = board.clone();
            switch (d) { // 당길 방향
                case 0:
                    for (int i = 0; i < N; i++) // pull line
                        changed = moveBlock(d, i, N - 1, false);
                    break;
                case 1:
                    for (int i = 0; i < N; i++)// pull line
                        changed = moveBlock(d, i, 0, false);
                    break;
                case 2:
                    for (int i = 0; i < N; i++)// pull line
                        changed = moveBlock(d, N - 1, i, false);
                    break;
                case 3:
                    for (int i = 0; i < N; i++)// pull line
                        changed = moveBlock(d, 0, i, false);
                    break;
                default:
                    break;
            }
            if(!changed)
                continue;
            // TODO 이동을 못 시키고 넘어갈 수 도 있음
            dfs(depth - 1);
            // restore
            board = origin;
        }
    }

    // 블록을 당겨오는 이미지다.
    // 재귀적 호출이 별로 좋아보이진 않군..
    static boolean moveBlock(int dir, int x, int y, boolean merged) {
        // 현 위치의 블럭을 방향대로 이동시킨다.
        int nx = x + DIRECTIONS[dir][0];
        int ny = y + DIRECTIONS[dir][1];

        while (true) {
            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                return false;

            if (board[x][y] == 0) {
                moveBlock(dir, nx, ny, false);
                return true;
            }

            if (!merged && board[x][y] == board[nx][ny]) { // 병합 후 이동
                board[nx][ny] += board[x][y];//merge
                maxNum = Math.max(maxNum, board[nx][ny]);
                board[x][y] = 0;
                moveBlock(dir, nx, ny, true); // 병합한채로 움직여라
                return true; // 병합 후 현재의 것은 없어지므로
            }
            if (board[nx][ny] != 0)
                return false;
            //move
            board[nx][ny] = board[x][y];
            board[x][y] = 0;
            moveBlock(dir, nx, ny, false);

            x = nx;
            y = ny;
            nx += DIRECTIONS[dir][0];
            ny += DIRECTIONS[dir][1];
        }
    }

}