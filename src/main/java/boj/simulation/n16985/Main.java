package boj.simulation.n16985;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/16985
 * Maaaaaaaaaze
 * 5 by 5크기의 판 5개가 주어진다., 1은 참가자가 들어갈 수 없는칸
 * 참가자는 모든 판을 회전(시계, 반시계) 시킬 수 있다.
 * 회전을 완료하고 자유롭게 판을 쌓아 큐브를 만든다.
 * 입구는 입력한 꼭짓점이고, 출구는 반대편 꼭짓점이다.
 * 최소한의 이동횟수로 탈출하라. <= 출력대상
 * 입구나 출구가 막힌경우 탈출불가로 간주 => -1 출력
 */
public class Main {
    static final int size = 5;
    static boolean[] used = new boolean[size];
    public static final int[][] START = {
            {0, 0, 0},
            {0, 0, size - 1},
            {0, size - 1, 0},
            {0, size - 1, size - 1},
    };
    public static final int[][] DEST = {
            {size - 1, size - 1, size - 1},
            {size - 1, size - 1, 0},
            {size - 1, 0, size - 1},
            {size - 1, 0, 0},
    };
//    static boolean[][][][] planes = new boolean[size][4][size][size];
    static List<boolean[][]>[] planes= new ArrayList[size];
    static boolean[][][] maze = new boolean[size][size][size]; // 탐색할 미로
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < size; i++) {
            planes[i] = new ArrayList<>();
            boolean[][] plane = new boolean[size][size];
            planes[i].add(plane);
            for (int j = 0; j < size; j++) {
                String[] tokens = br.readLine().split(" ");
                for (int k = 0; k < size; k++) {
                    plane[j][k] = tokens[k].equals("1");
                }
            }

            // 미리 돌려놓자
            for (int d = 1; d < 4; d++){
                planes[i].add(rotatePlane(planes[i].get(d - 1))); // 원형을 복사해서
                if(isEqualPlane(planes[i].get(d), planes[i].get(d -1))){
                    break;
                }
            }
        }
        br.close();
        // 한평면이 모두 0이면 불가능한 문제 <= 굳이 구현x
        backtracking(0);

        bw.write((min == Integer.MAX_VALUE ? -1 : min) + "\n");
        bw.flush();
        bw.close();
    }

    static void backtracking(int depth) {
        if (depth == size) {
            for (int i = 0; i < START.length; i++) { //
                // 순서를 받아온다.
                if (!maze[START[i][0]][START[i][1]][START[i][2]] || !maze[DEST[i][0]][DEST[i][1]][DEST[i][2]])
                    continue;
                bfs(START[i][0], START[i][1], START[i][2], DEST[i][0], DEST[i][1], DEST[i][2]);
            }
            return;
        }

        for (int i = 0; i < size; i++) { // 판의 순서 5! ...
            for (boolean[][] plane : planes[i]) { //// 현재 깊이의 판을 돌린다. 4
                if(!used[i]) {
                    maze[depth] = plane;
                    used[i] = true;
                    backtracking(depth + 1);
                    used[i] = false;
                }
            }
        }


    }


    static boolean[][] copyPlane(boolean[][] plane) {
        boolean[][] copied = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copied[i][j] = plane[i][j];
            }
        }
        return copied;
    }

    static boolean isEqualPlane(boolean[][] a, boolean[][] b) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }

    static boolean[][] rotatePlane(boolean[][] plane) {
        boolean[][] rotated = copyPlane(plane);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[j][size - 1 - i] = plane[i][j];
            }
        }
        return rotated;
    }

    static class State {
        int x;
        int y;
        int z;
        int dist;

        public State(int x, int y, int z, int dist) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.dist = dist;
        }
    }

    static void bfs(int x, int y, int z, int gx, int gy, int gz) {
        int[][] delta = { // x, y, z
                {1, 0, 0,},  // 아래
                {-1, 0, 0,}, // 위
                {0, 1, 0,},  // 남
                {0, -1, 0,}, // 북
                {0, 0, 1,},  // 동
                {0, 0, -1,}, // 서
        };
        Queue<State> q = new LinkedList<>();
        boolean[][][] visited = new boolean[size][size][size];
        q.offer(new State(x, y, z, 0));
        visited[x][y][z] = true;
        while (!q.isEmpty()) {
            State cur = q.poll();
            if (cur.dist >= min) // 목표까지 최단거리 min보다 긴것이 나오면 정답이 될 수 없다.
                continue;
            for (int[] d : delta) {
                State next = new State(cur.x + d[0], cur.y + d[1], cur.z + d[2], cur.dist + 1);
                // 갈 수 없는 방향
                if (next.x < 0 || next.y < 0 || next.z < 0 || next.x >= size || next.y >= size || next.z >= size)
                    continue;

                // 길(true)이 있고, 방문한 적이 없다면
                if (!visited[next.x][next.y][next.z] && maze[next.x][next.y][next.z]) {
                    q.offer(next);
                    visited[next.x][next.y][next.z] = true;
                    if (next.x == gx && next.y == gy && next.z == gz) {
                        // 목적지 도착
                        min = Math.min(next.dist, min);
                    }
                }
            }
        }
    }
}
