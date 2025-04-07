package boj.n1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 달이 차오른다, 가자.
 * https://www.acmicpc.net/problem/1194
 *
 * .: 빈칸
 * #: 벽
 * 소문자: 열쇠
 * 대문자: 문(대응되는 소문자로 열 수 있다.)
 * 0: 민식이 초기위치
 * 1: 출구
 * 4방향으로 움직일 때, 미로를 탈출하는데 최소한의 이동 횟수
 * NM 크기의 미로[1,50]
 *
 *  bfs노드마다 키 배열 가 필요하다.
 *  state 마다 visited를 깊은 복사 -> 비트마스크화해서 3차원 배열로 표현가능
 */
public class Main {
    static int N, M;
    static char[][] maze;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        int sr = 0, sc = 0;
        for (int i = 0 ; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == '0') {
                    sr = i;
                    sc = j;
                }
            }
        }
        System.out.println(bfs(sr, sc));
    }
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static class State {
        int r;
        int c;
        int dist;
        boolean[] keys;

        State(int r, int c, int dist, boolean[] keys) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.keys = keys;
        }
    }

    static int toKeyMask(boolean[] keys) {
        int mask = 0;
        for (int i = 0; i < 6; i++) {
            if (keys[i]) mask |= (1 << i);
        }
        return mask;
    }
    static int bfs(int sr, int sc) {
        int min = Integer.MAX_VALUE;
        boolean found = false;

        boolean[][][] visited = new boolean[N][M][64];  // 6개 열쇠 → 2^6 = 64 가지 조합
        boolean[] startKeys = new boolean[6];
        visited[sr][sc][0] = true;

        Queue<State> q = new LinkedList<>();
        q.offer(new State(sr, sc, 0, startKeys));

        while (!q.isEmpty()) {
            var state = q.poll();
            int keyMask = toKeyMask(state.keys);

            for (int d = 0; d < 4; d++) {
                int nr = state.r + dr[d];
                int nc = state.c + dc[d];
                int nextDist = state.dist + 1;

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (maze[nr][nc] == '#') continue;

                // 복사 - keys
                boolean[] nKeys = new boolean[6];
                for (int i = 0; i < 6; i++) {
                    nKeys[i] = state.keys[i];
                }

                // 문인데 열쇠 없으면 통과 못함
                if (maze[nr][nc] >= 'A' && maze[nr][nc] <= 'F') {
                    if (!nKeys[maze[nr][nc] - 'A']) continue;
                }

                // 열쇠면 획득
                if (maze[nr][nc] >= 'a' && maze[nr][nc] <= 'f') {
                    nKeys[maze[nr][nc] - 'a'] = true;
                }

                int nextKeyMask = toKeyMask(nKeys);
                if (visited[nr][nc][nextKeyMask]) continue;
                visited[nr][nc][nextKeyMask] = true;

                q.offer(new State(nr, nc, nextDist, nKeys));

                if (maze[nr][nc] == '1') {
                    found = true;
                    min = Math.min(min, nextDist);
                }
            }
        }
        return found ? min : -1;

    }
}

