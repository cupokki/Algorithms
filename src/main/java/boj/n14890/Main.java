package boj.n14890;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 경사로
 * https://www.acmicpc.net/problem/14890
 *
 * NN지도, 각 칸에 높이가 적혀있다.
 *
 * 지도에서 지나갈 수 있는 길이 몇개인지 찾는다.
 * 길이란 한행, 한열 전부 (한쪽끝에서 끝)
 * 길의 높이가 다르면 경사로를 놓을 수 있다. 경사로는 높이가 1, 길이가 L이다.
 * 경사로는 밑변이 바닥에 전부 닿아햐한다.
 */
public class Main {
    static int N;
    static int L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = N + N;
        for (int i = 0; i < N; i++) {
            boolean[] slopes = new boolean[N];
            for (int j = 1; j < N; j++) {
                int gap = map[i][j - 1] - map[i][j];
                if (Math.abs(gap) > 1) {
                    cnt--;
                    break;
                }
                if (gap == 1 && isFlatAndValid(i, j,0,1)) {
                    for (int k = j ; k < j + L; k++)
                        slopes[k] = true;
                } else if (gap == 1 && !isFlatAndValid(i,j,0,1)) {
                    cnt--;
                    break;
                }
                if (gap == -1 && ((L <= j && slopes[j - L]) || !isFlatAndValid(i, j - L,0,1))) {
                    cnt--;
                    break;
                }
            }
            slopes = new boolean[N];
            for (int j = 1; j < N; j++) {
                int gap = map[j - 1][i] - map[j][i];

                if (Math.abs(gap) > 1) {
                    cnt--;
                    break;
                }
                if (gap == 1 && isFlatAndValid(j, i,1,0)) {
                    for (int k = j ; k < j + L; k++)
                        slopes[k] = true;
                } else if (gap == 1 && !isFlatAndValid(j, i, 1,0)) {
                    cnt--;
                    break;
                }
                if (gap == -1 && ((L <= j && slopes[j - L]) || !isFlatAndValid(j - L, i,1,0))) {
                    cnt--;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }


    static boolean isFlatAndValid(int sr, int sc, int dr, int dc) {
        int r = sr;
        int c = sc;
        for (int l = 0; l < L; l++) {
            // 맵이탈, 경사로 설치불가
            if (r < 0 || r >= N || c < 0 || c >= N || map[sr][sc] != map[r][c]) {
                return false;
            }
            r += dr;
            c += dc;
        }
        return true;
    }
}
