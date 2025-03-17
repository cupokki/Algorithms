package boj.graph.n16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아기 상어
 * https://www.acmicpc.net/problem/16236
 * nn크기의 공간에 m마리 물고기와 한마리 아기상어
 * 한칸에 최대 한마리
 * 각 어류들은 자연수 크기,
 *  - 초기 아기상어 1
 * 아기 상어는 자신보다 큰 물고기 칸은 지나갈 수 없다.
 * 같으면 지나갈 수 있으나 먹을 수 없다.
 * 상하좌우로 이동한다.
 * 작은 물고기를 자기의 크기와 같게 먹어 크기를 1씩 늘린다.
 * 0. 먹을 물고기가 공간에 없다면 엄마상어에게 요청
 * 1. 4방향 중 먹을 수 있는 물고기가 1마리면 해당위치로
 * 2.
 */
public class Main {
    static int N;
    static int[][] fish;
    static int sharkSize = 2;
    static int hunger = 0;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fish = new int[N][N];
        int r = 0;
        int c = 0;
        int fishCnt = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 9) {
                    r = i;
                    c = j;
                } else if (num != 0){
                    fishCnt++;
                    fish[i][j] = num;
                }
            }
        }

        int t = 0;
        while(fishCnt > 0) {
            int[] state;
            if ((state = findEatableFish(r, c, sharkSize)) == null) {
                // call mommy shark
                break;
            }

            r = state[0];
            c = state[1];
            t+= state[2]; // time

            // eat
            fish[r][c] = 0;
            fishCnt--;

            // level up
            if (++hunger == sharkSize) {
                sharkSize++;
                hunger = 0;
            }
        }

        System.out.println(t);
    }
    // bfs
    static int[] findEatableFish(int r, int c, int sharkSize) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c, 0});
        boolean[][] visited = new boolean[N][N];
        visited[r][c] = true;
        List<int[]> result = new ArrayList<>();

        while(!q.isEmpty()) {
            int[] state = q.poll();
            int time = state[2] + 1;

            for (int d = 0; d < 4; d++) {
                int nr = state[0] + dr[d];
                int nc = state[1] + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || fish[nr][nc] > sharkSize)
                    continue;
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, time});
                    // is eatable
                    if (fish[nr][nc] != 0 && fish[nr][nc] < sharkSize) {
                        result.add(new int[]{nr, nc, time});
                    }
                }
            }

        }
        if (result.isEmpty())
            return null;
        result.sort((a, b) -> {
            if (a[2] == b[2]) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
            return a[2] - b[2];
        });
        result.sort((a, b) -> {
            if (a[2] == b[2]) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
            return a[2] - b[2];
        });
        return result.get(0);
    }
}