package boj.simulation.n18808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/18808
 * 스티커 붙이기
 * <p>
 * 회전 가능한 스티커를 n * m 평면에 붙인다.
 * 겹치지 않게 최대로 붙일 수 있는 넓이는?
 * 스티커 붙이기 룰
 * 1. 가능한 가장 위쪽을 선택하여 붙인다.
 * 2. 가능한 가장 왼쪽을 서낵하여 붙인다.
 * 3. 못 붙였다면 스티커를 90도 회전시켜 1,2을 재시도한다.
 * 4. 1,2,3을 0도 90도 180도 270도순으로 시도하고 실패하였다면 스티커를 폐기
 * <p>
 * 스티커를 폐기하는 조건때문에 사용한 스티커를 유지할 필요가 없다.!!!!!!!!!!!!!!!!!!
 */
public class Main {
    static int N, M, K; // 가로, 세로, 스티커 개수
    static boolean[][] board;
    static int R, C; // 스티커 행, 열 (실행간에 변경되야어야함)
    static boolean[][] sticker;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 100

        board = new boolean[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken()); // <= 10
            C = Integer.parseInt(st.nextToken()); // <= 10
            sticker = new boolean[R][C];
            //new sticker
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = st.nextToken().equals("1");
                }
            }

            // rotate는 90도 180도 270도
            for (int rotate = 0; rotate < 4; rotate++) {
                if (pasteSticker()) break;
                rotateSticker(); // TODO : 오버해드
            }
        }

        int covered = 0;
        for (boolean[] line : board) {
            for (boolean b : line) {
                if (b) covered++;
            }
        }
        System.out.println(covered);
    }

    static boolean pasteSticker() {
        List<int[]> temps = new ArrayList<>();

        for (int i = 0; i <= N - R; i++) { // 40 - 10
            for (int j = 0; j <= M - C; j++) { // 40 - 10

                // 스티커 붙여넣기
                boolean fail = false;
                for (int r = 0; r < R; r++) { // 10
                    for (int c = 0; c < C; c++) { // 10
                        if (!board[i + r][j + c] && sticker[r][c]) {
                            // 스티커를 붙여나가다가 실패를 만날 수도 있다.
                            temps.add(new int[]{i + r, j + c});
                        } else if (board[i + r][j + c] && sticker[r][c]) {
                            // rc루프 탈출하고, temps 초기화
                            temps.clear();
                            fail = true;
                            break;
                        }

                    }
                    if(fail)
                        break;
                }

                // 조건을 실패하면 어차피 빈 리스트임
                // todo 근데 여기서 어케 종료 시킬건데?
                for (int[] p : temps) {
                    board[p[0]][p[1]] = true;
                }
                if(!temps.isEmpty())
                    return true;
            }
        }
        return false;
    }

    // 90도씩 돌려준다.
    static void rotateSticker() {
        boolean[][] temp = new boolean[C][R]; // 반대로
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                temp[c][R - r - 1] = sticker[r][c];
            }
        }
        sticker = temp;
        R = temp.length;
        C = temp[0].length; // C, R  같이 갱신
    }
}
