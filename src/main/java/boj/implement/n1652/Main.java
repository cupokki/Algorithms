package boj.implement.n1652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 누울 자리를 찾아라
 * https://www.acmicpc.net/problem/1652
 *
 * 변이 N크기 정사각형 방, 연속된 두칸 이상에 양 끝 벽에 닿도록 눕는다.
 * 방에 X로 표시된 짐이 존재
 * 가로와 세로로 누울 수 있는자리를 출력하라.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] room = new boolean[N][N];
        for (int i = 0; i < N; i++ ) {
            char[] cstr = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                room[i][j] = cstr[j] == 'X';
            }
        }

        int h = 0;
        int v = 0;

        for (int i = 0; i < N; i++) {
            int size = 0;
            for (int j = 0; j < N; j++) {
                if (!room[i][j]) size++;
                else {
                    if (size >= 2)
                        h++;
                    size = 0;
                }

            }
            if (size >= 2)
                h++;
        }
        for (int i = 0; i < N; i++) {
            int size = 0;
            for (int j = 0; j < N; j++) {
                if (!room[j][i]) size++;
                else {
                    if (size >= 2)
                        v++;
                    size = 0;
                }

            }
            if (size >= 2)
                v++;
        }

        System.out.println(h + " " + v);
    }
}
