package programmers.n1844;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static int solution(int[][] maps) {
        int min = bfs(maps);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static int bfs(int[][] maps) {
        int h = maps.length;
        int w = maps[0].length;
        Queue<int[]> posQueue = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        int min = Integer.MAX_VALUE;
        int[] init = {0, 0, 1};
        posQueue.offer(init);
        while (!posQueue.isEmpty()) {
            var current = posQueue.poll();
            for (int d = 0; d < 4; d++) {
                int[] nextPos = {dx[d] + current[0], dy[d] + current[1], current[2] + 1};
                if (nextPos[0] < 0 || nextPos[0] >= h || nextPos[1] < 0 || nextPos[1] >= w) {
                    continue;
                }
                if (maps[nextPos[0]][nextPos[1]] == 0 || visited[nextPos[0]][nextPos[1]]) {
                    continue;
                }
                visited[nextPos[0]][nextPos[1]] = true;
                posQueue.add(nextPos);
                if (nextPos[0] == h - 1 && nextPos[1] == w - 1) {
                    min = Math.min(min, nextPos[2]);
                }

            }
        }
        return min;

    }

    public static void main(String[] args) {
        int[][] map1 = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        };
        System.out.println(solution(map1));
        int[][] map2 = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,0},
                {0,0,0,0,1}
        };
        System.out.println(solution(map2));
    }
}

