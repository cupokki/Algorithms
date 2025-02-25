package boj.graph.n2583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 영역 구하기
 * MN크기의 모눈종이 k칸의 직사각형을 그림, 분리된 영역의 개수와 각 영역의 넓이 합을 구하여라
 */
public class Main {
    static int M; // 세로
    static int N; // 가로
    static int K; // 그릴 직사각형
    static boolean[][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new boolean[M][N];

        // paint

        for (int i = 0; i < K; i++) {
            int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int r = pos[0]; r < pos[3]; r++) {
                for (int c = pos[2]; c < pos[4]; c++) {
                    if(!grid[r][c]) grid[r][c] = true;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int r = 0; r <= M; r++) {
            for (int c = 0; c <= N; c++) {
                if(!grid[r][c]) {
                    result.add(bfs(r, c));
                }
            }
        }

        Collections.sort(result);
        StringBuffer sb = new StringBuffer();
        sb.append(result.size() + "\n");
        for (int r : result)
            sb.append(r + " ");

        System.out.println(sb);
    }
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r,c});
        grid[r][c] = true;
        int size = 1;
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];
                if (nr >= 0 && nr <= M && nc >= 0 && nc <= N && !grid[nr][nc]) {
                    size++;
                    q.offer(new int[]{nr, nc});
                }

            }

        }
        return size;
    }
}