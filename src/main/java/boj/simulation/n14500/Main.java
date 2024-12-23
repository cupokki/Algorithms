package boj.simulation.n14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14500
 * 테트로미노
 * 폴리오미노란 정사각형을 이어 붙인 도형
 * - 겹치지 않는다.
 * - 도형은 모두 연결되어 있어야 한다.
 * - 정사각형의 변끼리 연결되어 있어야 한다.
 * 폴리오미노 4개를 이어붙인 것은 테트로미노. 5가지
 */
//public class Main {
//    static int[][] board;
//    static int N, M;
//    static int max;
//    static int[][][] tetrominos = {
//            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
//            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
//
//            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
//
//            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
//            {{0, 0}, {1, 0}, {2, 0}, {0, 1}},// 세로
//            {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
//            {{0, 1}, {1, 1}, {2, 1}, {0, 0}}, // 세로
//            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
//            {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, // 가로
//            {{1, 0}, {1, 1}, {1, 2}, {0, 0}},
//            {{1, 0}, {1, 1}, {1, 2}, {0, 2}}, // 가로
//
//            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
//            {{0, 1}, {1, 0}, {1, 1}, {2, 0}},
//            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
//            {{0, 1}, {0, 2}, {1, 0}, {1, 1}},
//
//            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
//            {{1, 0}, {1, 1}, {1, 2}, {0, 1}},
//            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
//            {{0, 1}, {1, 1}, {2, 1}, {1, 0}}
//
//    };
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken()); // <= 500
//        M = Integer.parseInt(st.nextToken()); // <= 500
//
//        board = new int[N][M];
//        for (int n = 0; n < N; n++) {
//            board[n] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        }
//
//        // 2 500 000 * 19 =
//        // 2.5M * 5 * 2 * 4 =
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                for (int t = 0; t < tetrominos.length; t++) {
//                    int sum = calculateSum(i, j, t);
//                    max = Math.max(max, sum);
//                }
//            }
//        }
//        System.out.println(max);
//    }
//
//
//
//    static int calculateSum(int r, int c, int t) {
//        int sum = 0;
//        for (int[] pos : tetrominos[t]) {
//            int nr = r + pos[0];
//            int nc = c + pos[1];
//            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
//                return -1;
//            sum += board[nr][nc];
//        }
//
//        return sum;
//    }
//}

    //dfs 해법
public class Main {
    static int N, M;
    static int[][] board = new int[500][500];
    static boolean[][] visited = new boolean[500][500];
    static int[] dr = new int[]{0, 0, 1, -1};
    static int[] dc = new int[]{1, -1, 0, 0};
    static int max;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(0, i, j, board[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    static void dfs(int depth, int r, int c, int sum){
        if(depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        // 8
        for (int i = 0 ; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                continue;
            if (!visited[nr][nc]) {
                visited[nr][nc] = true;
                dfs(depth + 1, r, c, sum + board[nr][nc]);// ㅜㅗㅏㅓ 모양은 고려하지 못하므로 제자리에서 다시한번 탐색해야함
                dfs(depth + 1, nr, nc, sum + board[nr][nc]);
                visited[nr][nc] = false;
            }

        }
    }
}
