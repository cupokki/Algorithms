package boj.backtracking.n15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사다리 조작
 * https://www.acmicpc.net/problem/15684
 *
 * 가로N 세로M개의 선
 * 세로선 사이 선 H개를 놓여 있다.
 *
 * 가로선을 추가하여 i세로선이 i세로 선으로 내려오게 한다.
 * 추가할 가로선의 최소 개수를 구하라.
 *
 * 정답이 3보다 크면 -1을 출력하라
 */
public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int result = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H][N - 1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            ladder[r][c] = true;
        }

        // 모든 선 백트래킹 -> n개를 뽑는 문제가 아니다.
        // 브푸트포스? O(H * M * H * M)

        backtracking(0,0);

        if (result > 3) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }
    static void backtracking(int idx, int size) {
        if (size > 3) {
            return;
        }

        if (check()) {
            result = Math.min(result, size);
        }

        if (result == 0) {
            return;
        }

        for (int i = idx ; i < H * (N - 1); i++) {
            int r = i / (N - 1);
            int c = i % (N - 1);
            if (ladder[r][c]) continue;
            if (c + 1 < N - 1 && ladder[r][c + 1]) continue;
            if (c - 1 >= 0 &&ladder[r][c - 1]) continue;
            ladder[r][c] = true;
            backtracking(i + 1, size + 1);
            ladder[r][c] = false;
        }

    }
    static boolean check() {
        for (int i = 0; i < N ; i++) {
            int current = i;
            for (int j = 0; j < H; j++) {
                if (current < N - 1 && ladder[j][current]) {
                    current++;
                } else if (current > 0 && ladder[j][current - 1]) {
                    current--;
                }
            }
            if (current != i) {
                return false;
            }
        }
        return true;
    }
}
