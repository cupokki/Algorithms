package boj.simulation.n13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13460
 * 구슬 탈출 2
 * 직사각형 보드에 빨간 구슬, 파란구슬
 * 빨강, 파랑 하나씩 넣고 빨간 구슬을 구멍을 통해 빼는 게임
 * (파란 구슬을 빠져나가면 안됨, 동시에 나가도 안됨)
 * 중력으로 판을 기울여 구슬을 움직인다.
 * 10번 이하로 움직임, 최소 몇번의 기울임(4방향)으로 빨간 구슬을 뺄 수 있나(불가능시 -1)
 * . 빈칸, # 벽, o 구멍, R,B
 */
public class Main {
    static int N, M;
    static int redR, redC, blueR, blueC;
    static char[][] board = new char[10][10];
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; i < M - 1; j++) {
//                board[i][j] =  == ? ;
                if(board[i][j] == 'R') {
                    redR = i;
                    redC = j;
                }
                if(board[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                }
            }
        }

        dfs(0, board, redR, redC, blueR, blueC);

        System.out.println(min != Integer.MAX_VALUE ? min : -1);
    }
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static void dfs(int depth, char[][] board, int rr, int rc, int br, int bc) {
        // 빨간 공이 들어가고, 파란공이 안들어감
        if(rr < 0 && br >= 0) {
            min = Math.min(depth, min);
            return;
        }
        // 최대깊이거나 파란공이 들어가면
        if(depth == 10 || br < 0) {
            return;
        }

        // 4^10
        for(int d = 0; d < 4; d++) {
            char[][] nextBoard = new char[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    nextBoard[i][j] = board[i][j];
                }
            }
            tiltBoard(d, nextBoard, rr, rc, br, bc);
            dfs(depth + 1, nextBoard, rr, rc, br, bc);
        }
    }

    static void tiltBoard(int d, char[][] board, int rr, int rc, int br, int bc) {
        // 라인을 옳길때 빨간공이 있는 라인, 파란공이 있는라인 모두 고려하고 수행한다.

        // 라인 선택
        int nrr = rr + dr[d];
        int nrc = rc + dr[d];
        while (true) {
//            if (nrr )
//            board[rr][0] = ' ';
        }


    }
}
