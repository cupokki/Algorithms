package boj.n19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/19236
 * 청소년 상어
 *
 *
 */
public class Main {
    static List<Integer>[][] space = new ArrayList[4][4]; // 공간, 번호 좌표
    static int[] fishState = new int[16 + 1]; // 좌표, 방향
    static int[] dr =
            new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc =
            new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = 0;
        int c = 0; // shark
        int dir;
        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                space[i][j].add(sc.nextInt());
                fishState[space[i][j].get(0)] = sc.nextInt() - 1; // 방향
            }
        }

        dir = space[0][0].get(0);
        sum += space[0][0].get(0);
        space[0][0].remove(0); // 음수는 빈칸


        int nr = r + dr[dir];
        int nc = c + dc[dir];

        while(true) {
            // 물고기 움직이기


            // 샤크 움직이기



        }
    }

}
