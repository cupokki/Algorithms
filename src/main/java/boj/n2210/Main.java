package boj.n2210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.acmicpc.net/problem/2210
 * 숫자판 점프
 *
 * 5by 5 크기의 숫자판, 각 칸에 0-9 숫자
 * 임의의 위치에서 시작해서 인접 4방향으로 5회 이동
 * 각 칸에 적혀 있는 숫자를 차례로 붙이면 6자리 수를 만든다.
 * 지나간 칸을 다시 거쳐도된다.
 *
 * 6을 만드는 경우의 수를 모두 구하라
 */
public class Main {
    static int[][] arr;

    static Set<String > set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[5][5];
        for (int i = 0; i < 5; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0, "" + arr[i][j]);
            }
        }
        System.out.println(set.size());
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static void dfs(int r, int c, int depth, String str) {
        if (depth == 5){// && sum == 6) {
            set.add(str);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
            dfs(nr, nc, depth + 1, str + arr[nr][nc]);
        }
    }
}
